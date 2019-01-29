package unislask;

import java.util.HashMap;
import java.util.Vector;

/**
 * <p>
 * CommendInterpreter class.
 * </p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public class CommendInterpreter {
	public CommendInterpreter(FrontEnd interface__) {
		UI = interface__;
		seeker = new Seeker();
	}

	public void run(Data global_state, String line) {

		String[] splited = line.split(" ");

		switch (splited[0]) {
		case "search":
			handleSearch(splited, line, global_state);

		case "add":
			UI.add();
		}
	}

	public void handleSearch(String[] splited, String line, Data global_state) {
		if (splited.length < 3) {
			UI.error(line, CommandError.InvalidNumberOfArguments);
			return;
		}
		if (splited[1].equals("book")) {
			if (splited[2].equals("-h")) {
				UI.help(1);
				return;
			}
			if (splited.length == 5) {
				UI.error(line, CommandError.InvalidNumberOfArguments);
				return;
			}
			switch (splited[3]) {
			case "--by-title":
				currentBookListing = listBooks(global_state, "TITLE", splited[4]);
			case "--by-publisher":
				currentBookListing = listBooks(global_state, "PUBLISHER", splited[4]);
			case "--by-release":
				currentBookListing = listBooks(global_state, "DATE", splited[4]);
			case "--by-file":
				currentBookListing = listBooks(global_state, "BOOKLNK", splited[4]);
			case "none":
				currentBookListing = listBooks(global_state, "NONE", splited[4]);
			}
		} else if (splited[1].equals("author")) {
			if (splited[2].equals("-h")) {
				UI.help(1);
				return;
			}
			if (splited.length == 5) {
				UI.error(line, CommandError.InvalidNumberOfArguments);
				return;
			}
			switch (splited[3]) {
			case "--by-name":
				currentAuthorListing = listAuthors(global_state, "NAME", splited[4]);
			case "--by-surname":
				currentAuthorListing = listAuthors(global_state, "SURNAME", splited[4]);
			case "--by-other":
				currentAuthorListing = listAuthors(global_state, "OTHER", splited[4]);
			case "--by-borndate":
				currentAuthorListing = listAuthors(global_state, "BORNDATE", splited[4]);
			case "none":
				currentAuthorListing = listAuthors(global_state, "NONE", splited[4]);
			case "--by-id":
				currentAuthorListing = listAuthors(global_state, "ID", splited[4]);
			}
		}
	}

	public HashMap<Integer, Book> listBooks(Data currentData, String attrName, String attr) {
		Vector<Book> books = seeker.seekByBookAttr(currentData, attrName, attr);
		HashMap<Integer, Book> options = null;
		if (books.size() == 0) {
			System.out.println("No result");
			return options;
		}
		options = new HashMap<>();
		for (Book book : books) {
			int i = 0;
			options.put(i, book);
			++i;
		}
		return options;
	}

	public HashMap<Integer, Author> listAuthors(Data currentData, String attrName, String attr) {
		Vector<Author> Authors = seeker.seekByAuthorAttr(currentData, attrName, attr);
		HashMap<Integer, Author> options = null;
		if (Authors.size() == 0) {
			System.out.println("No result");
			return options;
		}
		options = new HashMap<>();
		for (Author author : Authors) {
			int i = 0;
			options.put(i, author);
			++i;
		}
		return options;
	}

	public HashMap<Integer, Book> currentBookListing;
	public HashMap<Integer, Author> currentAuthorListing;
	Seeker seeker;
	FrontEnd UI;
}
