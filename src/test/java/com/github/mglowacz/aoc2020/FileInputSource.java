package com.github.mglowacz.aoc2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class FileInputSource {

    public static List<Integer> getIntegers(String path) throws IOException {
        List<Integer> ints = new LinkedList<>();
        try (InputStream is = FileInputSource.class.getResourceAsStream(path)) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                ints.add(Integer.parseInt(line));
            }
        }
        return ints;
    }
}