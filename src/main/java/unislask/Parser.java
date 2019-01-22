package unislask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

class Parser {
	static final String[] OPTIONS = { "authorpath", "bookspath", "author" };
	final static private String[] BOOK_ATTR = { "ID", "AUTHOR", "TITLE", "PUBLISHER", "DATE", "BOOKLNK" };

	Data parse(String path) {
		File root = new File(path);

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

				String books = settings.get("bookspath");
				if (!books.matches("({[a-zA-Z][a-zA-Z0-9]*}|{[a-zA-Z][a-zA-Z0-9]*,)+"))
					return new Data();

				Vector<String> bookspaths = new Vector<>();
				for (int i = 1; i < books.length() - 1; ++i) {
					StringBuilder bookpath = new StringBuilder(16);

					if (books.charAt(i) == ',') {
						String book = bookpath.toString();
						bookspaths.add(book);
						bookspaths.clear();
					} else {
						bookpath.append(books.charAt(i));
					}
				}
				Vector<Book> vbooks = new Vector<>();
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
				}

			}

			String[] subDirectory = root.list(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					return new File(dir, name).isDirectory();
				}
			});
		}
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

	Author parseAuthor(String path) throws FileNotFoundException {
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
					if (splited[1].matches("[A-F0-9]{8}"))
						book.emplace(splited[1], attr);
					break;
				}
			}
			if (!correct) {
				scanner.close();
				throw new FileNotFoundException();
			}

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
						book.addAuthor(parseAuthor(splited[0]));
					} else if (splited[1].matches("[A-F0-9]{8}"))
						book.emplace(splited[1], attr);
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

}