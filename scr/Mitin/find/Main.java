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
    private List<String> listDoc = new ArrayList<String>(); // ?

    private List<String> list = new ArrayList<String>();
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

        try {
            // избегаю дублирования
            for (String doc : listDoc) {
                if (!list.contains(doc))
                    list.add(doc);
            }
            File dir = new File(directory);
            if (subdirectories) {
                // случай, когла есть "-r -d directory".
                if (directory != null) {                           // java -jar JavaNum2.jar -r -d D:\2018 ПланJava2.txt
                    for (String doc : list)
                        pr.println(doc + ": " + Find.SearchInAllDirectories(dir, doc, subdirectories));
                }
                // случай, когда есть "-r", но нет "-d".
                if (directory == null) {                                      // java -jar JavaNum2.jar -r ПланJava2.txt
                    //pr.println("Вы не ввели директорию.");
                    //pr.println("укажите [-r] [-d directory] - для поиска в директории и поддиректории.");
                    //pr.println("или");
                    //pr.println("укажите [-d directory] - для поиска в директории.");
                }
            } else {
                // случай, когда есть "-d", но нет "-r".
                if (directory != null) {                              // java -jar JavaNum2.jar -d D:\2018 ПланJava2.txt
                    for (String doc : list)
                        pr.println(doc + ": " + Find.SearchInAllDirectories(dir, doc, subdirectories));
                }
                // случай, когда нет "-r" и "-d".
                if (directory == null) {                                         // java -jar JavaNum2.jar ПланJava2.txt
                    //pr.println("укажите [-r] [-d directory] - для поиска в директории и поддиректории.");
                    //pr.println("или");
                    //pr.println("укажите [-d directory] - для поиска в директории.");
                }
            }
        } catch (NullPointerException e){
            System.err.println("Неверный формат данных.");
            System.err.println("Command Line: find [-r] [-d directory] filename.txt");
            parser.printUsage(System.err);
        }
    }
}

