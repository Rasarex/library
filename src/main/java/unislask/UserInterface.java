package unislask;

import java.util.HashMap;

interface UserInterface {
	/**
	 * <p>error.</p>
	 *
	 * @param line a {@link java.lang.String} object.
	 * @param error a {@link unislask.CommandError} object.
	 */
	void error(String line, CommandError error);

	/**
	 * <p>help.</p>
	 *
	 * @param flag a int.
	 */
	void help(int flag);

	/**
	 * <p>add.</p>
	 */
	void add();

	/**
	 * <p>listBooks.</p>
	 *
	 * @param bookListing a {@link java.util.HashMap} object.
	 */
	void listBooks(HashMap<Integer, Book> bookListing);

	/**
	 * <p>listAuthors.</p>
	 *
	 * @param authorListing a {@link java.util.HashMap} object.
	 */
	void listAuthors(HashMap<Integer, Author> authorListing);

	/**
	 * <p>remove.</p>
	 */
	void remove();
}
