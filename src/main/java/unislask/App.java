package unislask;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

final class App {
    private App() {
    }

    public static void main(String[] args) {
        String root; // "/home/rasarex/java/SemestrI/Zaliczenie/library/examples/";
        try {
            File file = new File("settings.ini");
            Scanner scanner = new Scanner(file);
            root = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            Scanner lineScanner = new Scanner(System.in);
            System.out.println("Please add root path: ");
            root = lineScanner.nextLine();
            try {
                File file = new File("settings.ini");
                FileWriter writer = new FileWriter(file);
                writer.write(root);
                writer.close();
            } catch (IOException ex) {

            }

        }

        try {
            Data GLOBALSTATE = new Data();
            Parser parser = new Parser();

            CommendInterpreter interpreter = new CommendInterpreter(new FrontEnd("0.1.0 ALPHA", root));
            GLOBALSTATE = parser.parseRoot(root);
            Scanner cmdLineScanner = new Scanner(System.in);
            int exitcode = 0;
            while (exitcode == 0) {
                System.out.print("==>");
                String line = cmdLineScanner.nextLine();
                exitcode = interpreter.run(GLOBALSTATE, line);
            }
            cmdLineScanner.close();

        } catch (IOException e) {
            System.out.println("Failed to read data");
        }
    }
}
