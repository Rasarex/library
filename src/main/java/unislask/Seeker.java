package unislask;

import java.util.Vector;

/**
 * <p>
 * Seeker class.
 * </p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public class Seeker {
	public Vector<Book> seekByBookAttr(Data currentData, String attrName, String attr) {
		Vector<Book> rtn = new Vector<>();

		switch (attr.toUpperCase()) {
		case "TITLE":
			for (Book book : currentData.books) {
				if (book.title.contains(attr))
					rtn.add(book);
			}
		case "PUBLISHER":
			for (Book book : currentData.books) {
				if (book.publisher.contains(attr))
					rtn.add(book);
			}

		case "DATE":
			for (Book book : currentData.books) {
				if (book.date.contains(attr))
					rtn.add(book);
			}

		case "BOOKLNK":
			for (Book book : currentData.books) {
				if (book.booklnk.contains(attr))
					rtn.add(book);
			}
		case "NONE":
			return currentData.books;
		default:
			System.out.print("There is not such attribute as " + attrName);
		}
		return rtn;

	}

	public Vector<Author> seekByAuthorAttr(Data currentData, String attrName, String attr) {
		Vector<Author> rtn = new Vector<>();
		switch (attr.toUpperCase()) {
		case "NAME":
			for (Author author : currentData.authors) {
				if (author.name.contains(attr))
					rtn.add(author);
			}
		case "SURNAME":
			for (Author author : currentData.authors) {
				if (author.surname.contains(attr))
					rtn.add(author);
			}

		case "ID":
			for (Author author : currentData.authors) {
				if (author.ID.contains(attr))
					rtn.add(author);
			}

		case "BORNDATE":
			for (Author author : currentData.authors) {
				if (author.bornDate.contains(attr))
					rtn.add(author);
			}
		case "OTHER":
			for (Author author : currentData.authors) {
				if (author.name.contains(attr))
					rtn.add(author);
				else
					for (String name : author.otherNames) {
						if (name.contains(attr)) {
							rtn.add(author);
							break;
						}
					}
			}
		case "NONE":
			return currentData.authors;
		default:
			System.out.print("There is not such attribute as " + attrName);
		}
		return rtn;
	}
}
