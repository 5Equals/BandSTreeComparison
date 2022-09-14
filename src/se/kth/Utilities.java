/**
 * Tools to solve the task.
 */

package se.kth;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Utilities {
    private Utilities() {}

    /**
     * Be able to read a text information from a txt file.
     * @param file the file name that want to be read.
     * @return the string containing the text.
     */
    static String readText(String file) {
        Path textPath = Paths.get(file);
        try {
            String text = Files.readString(textPath);
            return text;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
