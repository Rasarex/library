package unislask;

import java.io.IOException;

/**
 * <p>
 * App class.
 * </p>
 *
 * @author rasarex
 * @version $Id: $Id
 */
public final class App {
    private App() {
    }

    /**
     * <p>
     * main.
     * </p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        try {
            Data GLOBALSTATE = new Data();
            Parser parser = new Parser();
            String root = "/home/rasarex/java/SemestrI/Zaliczenie/library/examples/";
            GLOBALSTATE = parser.parseRoot(root);
            for (Book book : GLOBALSTATE.books) {
                System.out.println(book);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
