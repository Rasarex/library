package unislask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

class Book {
	String ID;
	Vector<Author> authors;
	String title;
	String publisher;
	String date;
	File booklnk;

	public void emplace(String attr, String attrname) throws FileNotFoundException {
		switch (attrname) {
		case "TITLE":
			title = attr;
		case "PUBLISHER":
			publisher = attr;
		case "DATE":
			date = attr;
		case "BOOKLNK":
			booklnk = new File(attr);
		}
	}

	public void addAuthor(Author author__) {
		authors.add(author__);
	}
}