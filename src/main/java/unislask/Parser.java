package unislask;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.Vector;

class Parser {
	public static class PrintFiles extends SimpleFileVisitor<Path> {
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			System.out.println("pre visit dir:" + dir);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			System.out.println("visit file: " + file);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			System.out.println("visit file failed: " + file);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			System.out.println("post visit directory: " + dir);
			return FileVisitResult.CONTINUE;
		}
	}

	static final String[] OPTIONS = { "bookspathslink" };
	final static private String[] BOOK_ATTR = { "AUTHOR", "TITLE", "PUBLISHER", "DATE", "BOOKLNK" };

	Data parseRoot(String rootPath) throws IOException {
		Path path = Paths.get(rootPath);

		Files.walkFileTree(path, new PrintFiles());
		return new Data();
	}

	Data parse(String path) {
		File root = new File(path);
		Data rtn = new Data();
		Vector<Book> vbooks = new Vector<>();
		Vector<BookSeries> vseries = new Vector<>();
		String[] subFiles = root.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return !(new File(dir, name).isDirectory());
			}
		});
		HashMap<String, String> settings = null;
		for (String str : subFiles) {
			if (str.compareTo("settings.ini") == 0) {
				try {
					settings = parseSetting(new File("settings.ini"));
				} catch (FileNotFoundException e) {

				}
				for (String option : OPTIONS) {
					boolean exist = false;
					for (Map.Entry<String, String> entry : settings.entrySet()) {
						if ((entry.getValue().compareTo(option) == 0)) {
							exist = true;
						}
					}
					if (!exist)
						return new Data();
				}
				Vector<String> bookspaths = null;
				String books = settings.get("bookspathslink");
				try {
					bookspaths = factory.toPaths(books);
				} catch (FileNotFoundException e) {
					System.out.println("Path to file with bookspath is incorrect" + books);
					return new Data();
				}

				for (String bookpath : bookspaths) {
					try {
						vbooks.add(parseBookPath(bookpath));
					} catch (FileNotFoundException e) {
						System.out.println("File does not exist or file has incorrect format: " + bookpath);
					}
				}

				if (bookspaths.size() > 1) {
					BookSeries series = new BookSeries();
					series.books = vbooks;
					vseries.add(series);
				}

			}

		}

		rtn.books = vbooks;
		rtn.authors = factory.getVectorized();
		rtn.series = vseries;
		return rtn;
	}

	HashMap<String, String> parseSetting(File settingsf) throws FileNotFoundException {
		Scanner scanner = new Scanner(settingsf);
		HashMap<String, String> settings = new HashMap<>();
		while (scanner.hasNext()) {
			String tmp = scanner.nextLine();
			String[] r = tmp.split("=");
			settings.put(r[0], r[1]);
		}
		scanner.close();
		return settings;
	}

	Book parseBookPath(String path) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(path));
		Book book = new Book();
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] splited = line.split("=");
			if (splited.length != 2) {
				scanner.close();
				throw new FileNotFoundException();
			}
			boolean correct = false;
			for (String attr : BOOK_ATTR) {
				if (splited[0].compareTo(attr) == 0) {
					correct = true;
					if (splited[0].compareTo("AUTHOR") == 0) {
						Vector<String> authors = factory.toPaths(splited[1]);

						for (String author : authors) {
							book.addAuthor(factory.parseAuthor(author));
						}

					} else if (splited[1].matches("[A-F0-9]{8}") && splited[1].equals("ID")) {
						book.emplace(splited[1], attr);
					} else {
						book.emplace(splited[1], attr);
					}
					break;
				}
			}
			if (!correct) {
				scanner.close();
				throw new FileNotFoundException();
			}

		}
		scanner.close();
		return book;

	}

	AuthorFactory factory;
}