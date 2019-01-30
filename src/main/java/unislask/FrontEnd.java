package unislask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.mifmif.common.regex.Generex;

/**
 * <p>
 * FrontEnd class.
 * </p>
 * <b> FrontEnd</b> is an implementation of {@link unislask.UserInterface} class
 * for command line
 *
 * @author rasarex
 * @version $Id: $Id
 */
public class FrontEnd implements UserInterface {
    FrontEnd(String version, String rootPath) {
        System.out.println(
                "Welcome to the Simple Java Command Line Library version: " + version + "\n Type help for help");
        root = rootPath;
    }

    String root;

    /** {@inheritDoc} */
    @Override
    public void error(String line, CommandError error) {
        switch (error) {
        case WrongArgument:
            System.out.println("Wrong number of arugments " + line);
        case InvalidNumberOfArguments:
            System.out.println("Invalid number of arugments " + line);
        case NotSuchCommand:
            System.out.println("Command not recognized " + line);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void help(int flag) {
        switch (flag) {
        case 0:
            System.out.println("Available commands\n\tsearch [type] [option] [value]\n\tadd [type]");
            break;

        case 1:
            System.out.println("-\t search [type] [option] [value] \n\t <book>/<author> \t available types\n");
            System.out.println("-\t --by-title\t return books sorted by given title\n");
            System.out.println("-\t --by-publisher\t return books sorted by given publisher\n");
            System.out.println("-\t --by-release\t return books sorted by given date\n");
            System.out.println("-\t --by-file\t return books sorted by given file path\n");
            System.out.println("-\t none\t return current state\n");

            System.out.println("-\t --by-name\t return authors sorted by given name\n");
            System.out.println("-\t --by-other\t return authors sorted by given name, looks at all names\n");
            System.out.println("-\t --by-borndate\t return authors sorted by given borndate\n");
            System.out.println("-\t --by-id\t return authors sorted by given file ID\n");
            System.out.println("-\t none\t return current state\n");
            System.out.println("-\t --by-surname\t return authors sorted by given surname\n");
            break;
        }

    }

    /** {@inheritDoc} */
    @Override
    public void listBooks(HashMap<Integer, Book> bookListing) {
        for (Map.Entry<Integer, Book> entry : bookListing.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue() + "\n");
        }
    }

    /** {@inheritDoc} */
    @Override
    public void listAuthors(HashMap<Integer, Author> authorListing) {
        for (Map.Entry<Integer, Author> entry : authorListing.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue() + "\n");
        }
    }

    /** {@inheritDoc} */
    @Override
    public void add(Data state, int flag) {
        switch (flag) {
        case 0: {
            Book book = addBook();
            state.books.add(book);
            try {

                Generex generex = new Generex("([A-Z0-9]){12}");
                String path = generex.random();
                String filename = generex.random();
                Files.createDirectory(Paths.get(root + path));

                File bookfile = new File(root + path + "/" + filename);
                FileWriter writer = new FileWriter(bookfile);
                writer.write("#bookdef\n");
                writer.write("PUBLISHER=" + book.publisher + "\n");
                writer.write("DATE=" + book.date + "\n");
                writer.write("TITLE=" + book.title + "\n");
                writer.write("BOOKLNK=" + book.booklnk + "\n");
                writer.write("AUTHORS=");
                for (int i = 0; i < book.authors.size(); ++i) {
                    writer.write(book.authors.get(i).path);
                    if (i != book.authors.size() - 1)
                        writer.write(",");
                }
                writer.close();

            } catch (IOException e) {
                System.out.println("Failed to write data");
            }

            break;
        }
        case 1: {
            Author author = addAuthor();
            state.authors.add(author);
            try {

                Generex generex = new Generex("([A-Z0-9]){12}");
                String path = generex.random();
                String filename = generex.random();
                Files.createDirectory(Paths.get(root + path));

                File bookfile = new File(root + path + "/" + filename);
                FileWriter writer = new FileWriter(bookfile);
                writer.write("#authordef\n");
                writer.write("NAME=" + author.name + "\n");
                writer.write("BORNDATE=" + author.bornDate + "\n");
                writer.write("SURNAME=" + author.surname + "\n");
                writer.write("ID=" + author.ID + "\n");
                writer.write("OTHER=");
                for (int i = 0; i < author.otherNames.size(); ++i) {
                    writer.write(author.otherNames.get(i));
                    if (i != author.otherNames.size() - 1)
                        writer.write(",");
                }
                writer.write("\n");
                writer.close();

            } catch (IOException e) {
                System.out.println("Failed to write data");
            }
            break;
        }
        case 3: {
            Scanner lineScanner = new Scanner(System.in);
            System.out.println("Author:");
            String auth = lineScanner.nextLine();
            Author autho = null;
            boolean foundAuthor = false;
            for (Author author : state.authors) {

                if (author.ID.equals(auth)) {
                    autho = author;
                    foundAuthor = true;
                    break;
                }
            }
            if (!foundAuthor) {
                System.out.println("Not such author\n");
                return;
            }
            System.out.println("\nBook:");
            String sbook = lineScanner.nextLine();
            Book book = null;
            boolean foundBook = false;
            for (Book elem : state.books) {

                if (elem.booklnk.equals(sbook)) {
                    book = elem;
                    foundBook = true;
                    break;
                }
            }
            if (!foundBook) {
                System.out.println("Not such book\n");
                return;
            }
            book.addAuthor(autho);
            break;
        }
        }

    }

    Book addBook() {
        Book book = new Book();
        Scanner odczyt = new Scanner(System.in);
        System.out.print("Title: ");
        book.title = odczyt.nextLine();
        System.out.print("Publisher: ");
        book.publisher = odczyt.nextLine();
        System.out.print("Date: ");
        book.date = odczyt.nextLine();
        System.out.print("Path to file(serves as ID):");
        book.booklnk = odczyt.nextLine();

        return book;

    }

    Author addAuthor() {
        String otherNames;
        Author author = new Author();
        Scanner odczyt = new Scanner(System.in);

        System.out.print("ID: ");
        author.ID = odczyt.nextLine();

        System.out.print("Name: ");
        author.name = odczyt.nextLine();

        System.out.print("Surname: ");
        author.surname = odczyt.nextLine();

        System.out.print("Other names ( , as a separator): ");
        otherNames = odczyt.nextLine();
        String[] splited = otherNames.split(",");
        for (String other : splited) {
            author.otherNames.add(other);
        }
        System.out.print("Author borndate: ");
        author.bornDate = odczyt.nextLine();
        return author;
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
    }

}
