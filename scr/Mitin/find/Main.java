package Mitin.find;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;

public class Main {

    @Option(name = "-r", metaVar = "Subdirectories", usage = "Search in all subdirectories")
    private boolean subdirectories;

    @Option(name = "-d", metaVar = "Directory", usage = "Search in a directory or current")
    private String directory;

    @Argument(required = true, metaVar = "Doc", usage = "The document to be found")
    private String doc;

    public static void main(String[] args) throws IOException {
        new Main().launch(args);
    }

    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Command Line: find [-r] [-d directory] filename.txt");
            parser.printUsage(System.err);
            return;
        }

        if (subdirectories) {
            // случай, когла есть "-r -d directory".
            if (directory != null) { // java -jar JavaNum2.jar -r -d D:\2018 ПланJava2.txt
                File dir = new File(directory);
                System.out.println(doc + ": " + Find.SearchInAllDirectories(dir, doc, subdirectories));
            }
            // случай, когда есть "-r", но нет "-d".
            if (directory == null){ // java -jar JavaNum2.jar -r ПланJava2.txt
                File dirT = new File(new File(".").getAbsolutePath()); // текущая
                System.out.println(doc + ": " + Find.SearchInAllDirectories(dirT, doc, subdirectories));
            }
        } else {
            // случай, когда есть "-d", но нет "-r".
            if (directory != null) { // java -jar JavaNum2.jar -d D:\2018 ПланJava2.txt
                File dir = new File(directory);
                System.out.println(doc + ": " + Find.SearchInAllDirectories(dir, doc, subdirectories));
            }
            // случай, когда нет "-r" и "-d".
            if (directory == null) { // java -jar JavaNum2.jar ПланJava2.txt
                File dirT = new File(new File(".").getAbsolutePath()); // текущая
                System.out.println(doc + ": " + Find.SearchInAllDirectories(dirT, doc, subdirectories));
            }
        }
    }
}

