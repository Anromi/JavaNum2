package Mitin.find;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import static java.io.File.separator;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Set<File> res = new HashSet<File>();
    private Set<File> isEmpty = new HashSet<File>();

    private File f1 = new File("D:" + separator + "2018"); // TestUtility1.txt, ПланJava2.txt
    private File f2 = new File("D:"); // ТЕЛЕСКОП.pptx, ПланJava2.txt
    private File f3 = new File("C://Users//Administrator//IdeaProjects//JavaNum2//input"); // file1.txt, f1.txt
    private File f4 = new File("C://Users//Administrator//IdeaProjects//JavaNum2//input//folder1"); // file2.txt
    private File f5 = new File("C://Users//Administrator//IdeaProjects//JavaNum2//input//folder1//folder1(1)"); // пустая папка
    private File f6 = new File("C://Users//Administrator//IdeaProjects//JavaNum2//input//folder2"); // f2.txt

    @Test
    public void SearchInSpecificDirectoriesTest() throws IOException {
        res.add(f3);
        res.add(f4); // [C://Users//Administrator//IdeaProjects//JavaNum2//input,
        // C://Users//Administrator//IdeaProjects//JavaNum2//input//folder1]
        assertEquals(Find.SearchInAllDirectories(f3, "file1.txt", true), res);
        // -r -d C://Users//Administrator//IdeaProjects//JavaNum2//input file1.txt
        res.clear();

        res.add(f4); // [C://Users//Administrator//IdeaProjects//JavaNum2//input//folder1]
        assertEquals(Find.SearchInAllDirectories(f4, "file1.txt", true), res);
        // -r -d C://Users//Administrator//IdeaProjects//JavaNum2//input//folder1 file1.txt
        res.clear();
    }
    @Test
    public void SearchInAllDirectoriesTest() throws IOException {
        res.add(f3); // [C://Users//Administrator//IdeaProjects//JavaNum2//input]
        assertEquals(Find.SearchInAllDirectories(f3 , "file1.txt", false), res); // -d D:\2018 TestUtility1.txt
        res.clear();

        res.add(f2); // []
        assertEquals(Find.SearchInAllDirectories(f3, "f2.txt", false), isEmpty); // -d C://Users//Administrator//IdeaProjects//JavaNum2//input f2.txt
        res.clear();
    }
}