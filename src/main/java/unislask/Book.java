package unislask;

import java.io.File;
import java.io.FileNotFoundException;

class Book {
	String ID;
	Author author;
	String title;
	String publisher;
	String date;
	File booklnk;

	public void emplace(String attr, String attrname) throws FileNotFoundException {
		switch (attrname) {
		case "ID":
			ID = attr;
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
		author = author__;
	}
}