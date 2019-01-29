package unislask;

import java.util.HashMap;

/**
 * <p>FrontEnd class.</p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public class FrontEnd implements UserInterface {

    FrontEnd(int version) {
        System.out.println(
                "Welcome to the Simple Java Command Line Library version: " + version + "\n Type help for help");

    }

    /** {@inheritDoc} */
    @Override
    public void error(String line, CommandError error) {
        switch (error) {
        case WrongArgument:
            System.out.println("Wrong number of arugments " + line);
        case InvalidNumberOfArguments:
            System.out.println("Invalid number of arugments" + line);
        case NotSuchCommand:
            System.out.println("Command not recognized" + line);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void help(int flag) {
        switch (flag) {
        case 0:
            System.out.println("Available commands\n\tsearch [type] [option] [value]\n\tadd [type]");

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
        }

    }

    /** {@inheritDoc} */
    @Override
    public void listBooks(HashMap<Integer, Book> bookListing) {
    }

    /** {@inheritDoc} */
    @Override
    public void listAuthors(HashMap<Integer, Author> authorListing) {
    }

    /** {@inheritDoc} */
    @Override
    public void add() {
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
    }

}
