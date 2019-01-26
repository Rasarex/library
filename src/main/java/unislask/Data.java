package unislask;

import java.util.Vector;

class Data {
    Data() {
        books = new Vector<>();
        authors = new Vector<>();
        series = new Vector<>();
    }

    public void add(Data other) {
        this.books.addAll(other.books);
        this.series.addAll(other.series);
        this.authors.addAll(other.authors);
    }

    public Vector<Book> books;
    public Vector<Author> authors;
    public Vector<BookSeries> series;
}