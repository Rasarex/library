package unislask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        try {
            Data GLOBALSTATE = new Data();
            Parser parser = new Parser();
            String root = new String("E:\\Programming_Connected\\library\\examples\\");
            System.out.println(root);
            GLOBALSTATE = parser.parseRoot(root);
            for (Book book : GLOBALSTATE.books) {
                System.out.println(book);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
