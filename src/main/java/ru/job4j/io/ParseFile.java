package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;
    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent() {
        return content(a -> true);
    }

    public synchronized String getContentWithoutUnicode() {
        return content(a -> a < 0x80);
    }

    public String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = input.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
