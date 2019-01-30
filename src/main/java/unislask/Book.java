package unislask;

import java.util.Vector;

/**
 * <p>
 * Book class.
 * </p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public class Book {
	Vector<Author> authors;
	String title;
	String publisher;
	String date;
	String booklnk;

	Book() {
		authors = new Vector<>();
		title = new String();
		publisher = new String();
		date = new String();
		booklnk = new String();
	}

	/**
	 * <p>
	 * emplace.
	 * </p>
	 *
	 * @param attr     state value.
	 * @param attrname state name.
	 */
	public void emplace(String attr, String attrname) {
		switch (attrname) {
		case "TITLE":
			title = attr;
		case "PUBLISHER":
			publisher = attr;
		case "DATE":
			date = attr;
		case "BOOKLNK":
			booklnk = attr;
		}
	}

	/**
	 * <p>
	 * addAuthor.
	 * </p>
	 *
	 * @param author__ a {@link unislask.Author} object.
	 */
	public void addAuthor(Author author__) {
		authors.add(author__);
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(256);
		builder.append("\nAuthors:");
		for (Author author : authors) {

			builder.append(author.ID);
		}
		builder.append("\nTitle:");
		builder.append(title);
		builder.append("\nPublisher:");
		builder.append(publisher);
		builder.append("\nRelease date:");
		builder.append(date);
		builder.append("\nPath:");
		builder.append(booklnk);
		return builder.toString();
	}
}
