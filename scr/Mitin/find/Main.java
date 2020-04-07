package Mitin.find;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

// java -jar JavaNum2.jar -r -d D:\2018 ПланJava2.txt
public class Main {

    @Option(name = "-r", metaVar = "Subdirectories", usage = "Search in all subdirectories")
    private boolean subdirectories;

    @Option(name = "-d", metaVar = "Directory", usage = "Search in a directory or current")
    private String directory;

    @Argument(required = true, metaVar = "TxtDoc", usage = "The document to be found")
    private String doc;

    public static void main(String[] args) throws IOException{
        new Main().launch(args);
    }

    private void launch(String[] args) throws IOException{
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Command Line: find [-r] [-d directory] filename.txt");
            parser.printUsage(System.err);
            return;
        }

        PrintStream pr = new PrintStream(System.out);

        if (doc.matches(".+\\.txt")) {
            File dir = new File(directory);
            if (subdirectories) {
                // случай, когла есть "-r -d directory". Искать во всех директориях. +
                if (directory != null) {
                    pr.println(Find.SearchInAllDirectories(dir, doc, subdirectories));
                }
                // случай, когда есть "-r", но нет "-d". Искать во всех поддиректориях?. => ошибка||возращает ничего????
                if (directory == null) {
                    // ?
                }
            } else {
                // случай, когда есть "-d", но нет "-r". Искать в директории(конкретной) или в текущей(?). +
                if (directory != null) {
                    pr.println(Find.SearchInAllDirectories(dir, doc, subdirectories));
                }
                // случай, когда нет "-r" и "-d". ???????. => ошибка||возращает ничего???
                if (directory == null) {
                    // ?
                }
            }
        } else {
            System.err.println("Неверный формат данных.");
            parser.printUsage(System.err);
        }
    }
}

