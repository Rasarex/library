package unislask;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>BookFactory class.</p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public class BookFactory {

	final static private String[] BOOK_ATTR = { "AUTHORS", "TITLE", "PUBLISHER", "DATE", "BOOKLNK" };
	private final Lock lock;
	ConcurrentHashMap<Vector<String>, Book> unresolvedBooks;
	Vector<Book> resolved;

	void resolve(ConcurrentHashMap<String, Author> authors) {
		// TODO BETTER ERROR CODE FOR RESOLVING
		for (Map.Entry<Vector<String>, Book> entry : unresolvedBooks.entrySet()) {
			Book book = entry.getValue();

			for (String sauthor : entry.getKey()) {
				Author author = authors.get(sauthor);
				if (author != null) {
					book.addAuthor(author);
				}
			}
			resolved.add(book);

		}
	}

	BookFactory() {
		lock = new ReentrantLock(true);
		unresolvedBooks = new ConcurrentHashMap<>();
		resolved = new Vector<>();
	}

	void parseBookPath(Vector<String> vfile) {
		lock.lock();
		Vector<String> authors = new Vector<>();
		Book book = new Book();
		for (String line : vfile) {
			if (line.charAt(0) == '#')
				continue;
			String[] splited = line.split("=");
			boolean correct = false;
			for (String attr : BOOK_ATTR) {
				if (splited[0].equals(attr)) {
					correct = true;
					if (splited[0].equals("AUTHORS")) {
						String[] tauthors = splited[1].split(",");
						for (String author : tauthors) {
							authors.add(author);
						}
						System.out.println(authors);
					} else {
						book.emplace(splited[1], attr);
					}
				}
			}
			if (correct) {
				unresolvedBooks.put(authors, book);
			}
		}

		lock.unlock();
	}

}
