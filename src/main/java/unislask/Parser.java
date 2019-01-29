package unislask;

import java.io.*;
import java.nio.file.*;
import java.util.Map;

/**
 * <p>Parser class.</p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public class Parser {

	Data parseRoot(String rootPath) throws IOException {
		Path path = Paths.get(rootPath);
		afactory = new AuthorFactory();
		bfactory = new BookFactory();
		FileWalker walker = new FileWalker(afactory, bfactory);
		Files.walkFileTree(path, walker);
		Data read = new Data();
		for (Map.Entry<String, Author> entry : walker.authorFactory.authors.entrySet()) {

			read.authors.add(entry.getValue());

		}
		bfactory.resolve(walker.authorFactory.authors);
		read.books = bfactory.resolved;

		return read;
	}

	BookFactory bfactory;
	AuthorFactory afactory;
}
