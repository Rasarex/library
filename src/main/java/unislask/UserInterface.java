package unislask;

import java.util.HashMap;

/**
 * <p>
 * Interface for {@link unislask.CommendInterpreter} and user
 * </p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public interface UserInterface {
	/**
	 * <p>
	 * error.
	 * </p>
	 *
	 * @param line  on which error occurend.
	 * @param error type of error.
	 */
	public void error(String line, CommandError error);

	/**
	 * <p>
	 * help.
	 * </p>
	 *
	 * @param flag different types of help
	 */
	public void help(int flag);

	/**
	 * <p>
	 * add.
	 * </p>
	 *
	 * @param state a global state of type {@link unislask.Data}.
	 * @param flag  flipping between book/author
	 */
	public void add(Data state, int flag);

	/**
	 * <p>
	 * listBooks.
	 * </p>
	 *
	 * @param bookListing listing of books created by {@link unislask.CommendInterpreter}.
	 */
	public void listBooks(HashMap<Integer, Book> bookListing);

	/**
	 * <p>
	 * listAuthors.
	 * </p>
	 *
	 * @param authorListing listing of authors created by
	 *                      {@link unislask.CommendInterpreter}.
	 */
	public void listAuthors(HashMap<Integer, Author> authorListing);

	/**
	 * <p>
	 * remove.
	 * </p>
	 */
	void remove();
}
