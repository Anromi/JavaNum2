package Mitin.find;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Find {

    public Set<File> SearchInAllDirectories(File directory, String doc, Boolean subdirectories) throws IOException {
        Set<File> res = new HashSet<File>();
        File[] dirFiles = directory.listFiles();
        if (dirFiles != null) {
            for (File file : dirFiles) {
                if (subdirectories) { // Search in specific directories
                    if (file.isDirectory()) {
                        res.addAll(SearchInAllDirectories(file, doc, true));
                    }
                }
                if (file.isFile()) {
                    if (doc.equals(file.getName())) {
                        res.add(new File(file.getParent()));
                    }
                }
            }
        }
        return res;
    }
}
