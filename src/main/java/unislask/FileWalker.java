package unislask;

import java.nio.file.*;
import java.nio.file.attribute.*;

import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>FileWalker class.</p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public class FileWalker extends SimpleFileVisitor<Path> {
	AuthorFactory authorFactory;
	BookFactory bookFactory;

	FileWalker(AuthorFactory a, BookFactory b) {
		authorFactory = a;
		bookFactory = b;
	}

	/** {@inheritDoc} */
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		// System.out.println("Looking in :" + dir);
		return FileVisitResult.CONTINUE;
	}

	/** {@inheritDoc} */
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException, FileNotFoundException {
		// System.out.println("Parsing: " + file);
		String header = new String();
		BufferedReader reader = Files.newBufferedReader(file);
		Vector<String> lines = new Vector<>();
		while (reader.ready()) {
			lines.add(reader.readLine());

		}
		header = lines.get(0);
		if (header.charAt(0) == '#') {
			if (header.equals("#bookdef")) {
				bookFactory.parseBookPath(lines);
			}
			if (header.equals("#authordef")) {
				authorFactory.parseAuthor(lines, file.toAbsolutePath().toString());
			}
		}

		return FileVisitResult.CONTINUE;
	}

	/** {@inheritDoc} */
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		// System.out.println("Failed to read file: " + file);
		return FileVisitResult.CONTINUE;
	}

	/** {@inheritDoc} */
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		// System.out.println("Parsed: " + dir);
		return FileVisitResult.CONTINUE;
	}
}
