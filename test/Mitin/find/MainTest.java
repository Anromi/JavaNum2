package Mitin.find;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import static java.io.File.separator;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Set<File> res = new HashSet<File>();
    private Set<File> isEmpty = new HashSet<File>();

    private File f1 = new File("D:" + separator + "2018");
    private File f2 = new File("D:");

    @org.junit.jupiter.api.Test
    public void main() throws IOException {
        res.add(f1);
        assertEquals(Find.SearchInAllDirectories(f1, "TestUtility1.txt", true), res); // -r -d D:\2018 TestUtility1.txt
        res.remove(f1);

        res.add(f1);
        assertEquals(Find.SearchInAllDirectories(f1, "TestUtility1.txt", false), res); // -d D:\2018 TestUtility1.txt
        res.remove(f1);

        res.add(f2);
        assertEquals(Find.SearchInAllDirectories(f2, "TestUtility1.txt", false), isEmpty); // -d D: TestUtility1.txt
        res.remove(f2);

        res.add(f2);
        assertEquals(Find.SearchInAllDirectories(f2, "TestUtility1.txt", false), isEmpty); // -d D: TestUtility1.txt
        res.remove(f2);
    }
}