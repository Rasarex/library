package unislask;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * BookFactory class.
 * </p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public class BookFactory {

	final static private String[] BOOK_ATTR = { "AUTHORS", "TITLE", "PUBLISHER", "DATE", "BOOKLNK" };
	private final Lock lock;
	ConcurrentHashMap<Vector<String>, Book> unresolvedBooks;
	Vector<Book> resolved;

	/**
	 * <p>
	 * resolve.
	 * </p>
	 *
	 * @param authors links parsed authors with books
	 */
	public void resolve(ConcurrentHashMap<String, Author> authors) {
		// TODO BETTER ERROR CODE FOR RESOLVING
		for (Map.Entry<Vector<String>, Book> entry : unresolvedBooks.entrySet()) {
			System.out.println(entry.getValue());
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

	/**
	 * <p>
	 * parseBookPath.
	 * </p>
	 *
	 * @param vfile parsed file into {@link Vector}.
	 */
	public void parseBookPath(Vector<String> vfile) {
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
						if (splited.length == 2) {

							String[] tauthors = splited[1].split(",");
							for (String author : tauthors) {
								authors.add(author);
							}

						}
					} else {
						if (splited.length == 2)
							book.emplace(splited[1], attr);
						else
							book.emplace("", attr);
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
