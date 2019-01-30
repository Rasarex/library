package unislask;

import java.util.Vector;

/**
 * <p>
 * Data class.
 * </p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public class Data {
    Data() {
        books = new Vector<>();
        authors = new Vector<>();

    }

    /**
     * <p>
     * add.
     * </p>
     *
     * @param other a {@link unislask.Data} object.
     */
    public void add(Data other) {
        this.books.addAll(other.books);

        this.authors.addAll(other.authors);
    }

    public Vector<Book> books;
    public Vector<Author> authors;

}
