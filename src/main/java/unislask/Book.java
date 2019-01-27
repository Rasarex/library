package unislask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

class Book {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(256);
		builder.append("\nAuthors:");
		for (Author author : authors) {

			builder.append(author.toString());
		}
		builder.append("\nTitle:");
		builder.append(title);
		builder.append("\nPublisher:");
		builder.append(publisher);
		builder.append("\nRelease date:");
		builder.append(date);
		builder.append("\nPath:");
		builder.append(booklnk.getAbsolutePath());
		return builder.toString();
	}
}