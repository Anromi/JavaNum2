package Mitin.find;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Set<File> res = new HashSet<File>();
    private Set<File> isEmpty = new HashSet<File>();

    private File f6 = new File("resources/input"); // file1.txt, f1.txt
    private File f7 = new File("resources/input/folder1"); // file2.txt
    private File f8 = new File("out/artifacts/JavaNum2_jar"); // текущая директория

    Find find = new Find();
    @Test
    public void SearchInAllDirectoriesTest() throws IOException { // Поиск во всех каталогах
        res.add(f6);
        res.add(f7); // [resources/input, resources/input/folder1]
        assertEquals(find.SearchInAllDirectories(f6, "file1.txt", true), res);// -r -d ...resources/input file1.txt
        res.clear();

        res.add(f7); // [resources/input/folder1]
        assertEquals(find.SearchInAllDirectories(f7, "file1.txt", true), res);// -r -d resources/input/folder1 file1.txt
        res.clear();
    }

    @Test
    public void SearchInCurrentDirectory() throws IOException { // поиск в текущей директории
        res.add(f6);
        res.add(f7); // [resources/input, resources/input/folder1]
        assertEquals(find.SearchInAllDirectories(f6, "file1.txt", true), res); // -r file1.txt
        res.clear();

        assertEquals(find.SearchInAllDirectories(f8, "file1.txt", true), isEmpty); // -r file1.txt
    }

    @Test
    public void SearchInSpecificDirectoriesTest() throws IOException { // Поиск в определенных каталогах
        assertEquals(find.SearchInAllDirectories(f8, "file1.txt", false), isEmpty); // file1.txt

        res.add(f6); // [resources/input]
        assertEquals(find.SearchInAllDirectories(f6 , "file1.txt", false), res); // -d resources/input file1.txt
        res.clear();

        assertEquals(find.SearchInAllDirectories(f6, "f2.txt", false), isEmpty); // -d resources/input f2.txt
    }
}