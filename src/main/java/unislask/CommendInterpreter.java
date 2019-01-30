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
	/**
	 * <p>
	 * Constructor for CommendInterpreter.
	 * </p>
	 *
	 * @param interface__ a {@link unislask.FrontEnd} object.
	 */
	public CommendInterpreter(FrontEnd interface__) {
		UI = interface__;
		seeker = new Seeker();
	}

	/**
	 * <p>
	 * run.
	 * </p>
	 *
	 * @param global_state a {@link unislask.Data} object.
	 * @param line         line to be interpreted
	 * @return error code.
	 */
	public int run(Data global_state, String line) {

		String[] splited = line.split(" ");

		switch (splited[0]) {
		case "search": {
			handleSearch(splited, line, global_state);
			return 0;
		}
		case "add": {
			if (splited.length == 4) {
				if (splited[1].equals("author") && splited[2].equals("to") && splited[3].equals("book")) {
					UI.add(global_state, 3);
					return 0;
				}
			}
			if (splited.length != 2) {
				UI.error(line, CommandError.InvalidNumberOfArguments);
			} else {
				switch (splited[1].toUpperCase()) {
				case "BOOK": {
					UI.add(global_state, 0);
					return 0;
				}
				case "AUTHOR": {
					UI.add(global_state, 1);
					return 0;
				}
				}
			}
			return 0;
		}
		case "exit": {
			if (splited.length == 1)
				return -1;
			else
				return 0;
		}
		case "help": {
			UI.help(0);
			return 0;
		}
		default: {
			UI.error(line, CommandError.NotSuchCommand);
			return 0;
		}
		}

	}

	void handleSearch(String[] splited, String line, Data global_state) {
		if (splited.length == 2) {
			if (splited[1].equals("-h"))
				UI.help(1);
			return;
		}
		if (splited.length < 3) {
			UI.error(line, CommandError.InvalidNumberOfArguments);
			return;
		}
		if (splited[1].equals("book")) {
			if (splited.length == 3) {
				if (splited[2].equals("none")) {
					currentBookListing = listBooks(global_state, "NONE", "");
					UI.listBooks(currentBookListing);
					return;
				}
			}
			if (splited.length != 4) {
				UI.error(line, CommandError.InvalidNumberOfArguments);
				return;
			}
			switch (splited[2]) {
			case "--by-title": {
				currentBookListing = listBooks(global_state, "TITLE", splited[3]);
				UI.listBooks(currentBookListing);
				break;
			}
			case "--by-publisher": {
				currentBookListing = listBooks(global_state, "PUBLISHER", splited[3]);
				UI.listBooks(currentBookListing);
				break;
			}
			case "--by-release": {
				currentBookListing = listBooks(global_state, "DATE", splited[3]);
				UI.listBooks(currentBookListing);
				break;
			}
			case "--by-file": {
				currentBookListing = listBooks(global_state, "BOOKLNK", splited[3]);
				UI.listBooks(currentBookListing);
				break;
			}
			}
		} else if (splited[1].equals("author")) {
			if (splited.length == 3) {
				if (splited[2].equals("none")) {
					currentAuthorListing = listAuthors(global_state, "NONE", "");
					UI.listAuthors(currentAuthorListing);
					return;
				}
			}
			if (splited.length != 4) {
				UI.error(line, CommandError.InvalidNumberOfArguments);
				return;
			}
			switch (splited[2]) {
			case "--by-name": {
				currentAuthorListing = listAuthors(global_state, "NAME", splited[3]);
				UI.listAuthors(currentAuthorListing);
				break;
			}
			case "--by-surname": {
				currentAuthorListing = listAuthors(global_state, "SURNAME", splited[3]);
				UI.listAuthors(currentAuthorListing);
				break;
			}
			case "--by-other": {
				currentAuthorListing = listAuthors(global_state, "OTHER", splited[3]);
				UI.listAuthors(currentAuthorListing);
				break;
			}
			case "--by-borndate": {
				currentAuthorListing = listAuthors(global_state, "BORNDATE", splited[3]);
				UI.listAuthors(currentAuthorListing);
			}

			case "--by-id": {
				currentAuthorListing = listAuthors(global_state, "ID", splited[3]);
				UI.listAuthors(currentAuthorListing);
				break;
			}
			}
		}
	}

	/**
	 * <p>
	 * listBooks.
	 * </p>
	 *
	 * @param currentData of type{@link unislask.Data}
	 * @param attr        state value.
	 * @param attrName    state name.
	 * @return listed books.
	 */
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

	/**
	 * <p>
	 * listAuthors.
	 * </p>
	 *
	 * @param currentData of type{@link unislask.Data}
	 * @param attr        state value.
	 * @param attrName    state name.
	 * @return listed authors.
	 */
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
