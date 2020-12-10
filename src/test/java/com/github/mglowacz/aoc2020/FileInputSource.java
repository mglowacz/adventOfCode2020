package com.github.mglowacz.aoc2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.arraycopy;

public class FileInputSource {

    public static List<Long> getLongs(String path) throws IOException {
        List<Long> longs = new LinkedList<>();
        try (InputStream is = FileInputSource.class.getResourceAsStream(path)) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                longs.add(Long.parseLong(line));
            }
        }
        return longs;
    }


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

    public static List<String> getStrings(String path) throws IOException {
        List<String> strings = new LinkedList<>();
        try (InputStream is = FileInputSource.class.getResourceAsStream(path)) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                strings.add(line);
            }
        }
        return strings;
    }

    public static char[][] getMap(String path) throws IOException {
        List<String> lines = new LinkedList<>();
        try (InputStream is = FileInputSource.class.getResourceAsStream(path)) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        char[][] view = new char[lines.size()][longestLineSize(lines)];
        for (int i = 0 ; i < lines.size() ; i++) {
            String line = lines.get(i);
            arraycopy(line.toCharArray(), 0, view[i], 0, line.toCharArray().length);
        }
        return view;

    }

    private static int longestLineSize(List<String> lines) {
        return lines.stream().map(String::length).max(Integer::compare).orElseThrow();
    }
}