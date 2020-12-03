package com.github.mglowacz.aoc2020.day3;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3 {
    @Test
    public void test() throws IOException {
        char[][] map = FileInputSource.getMap("/day3/test1");
        assertEquals(7, TreeCounter.count(map, 3, 1));
    }

    @Test
    public void data1() throws IOException {
        char[][] map = FileInputSource.getMap("/day3/data");
        assertEquals(244, TreeCounter.count(map, 3, 1));
    }

    @Test
    public void test2() throws IOException {
        char[][] map = FileInputSource.getMap("/day3/test1");
        assertEquals(2, TreeCounter.count(map, 1, 1));
        assertEquals(7, TreeCounter.count(map, 3, 1));
        assertEquals(3, TreeCounter.count(map, 5, 1));
        assertEquals(4, TreeCounter.count(map, 7, 1));
        assertEquals(2, TreeCounter.count(map, 1, 2));

        long m = TreeCounter.count(map, 1, 1) *
                TreeCounter.count(map, 3, 1) *
                TreeCounter.count(map, 5, 1) *
                TreeCounter.count(map, 7, 1) *
                TreeCounter.count(map, 1, 2);

        assertEquals(336, m);
    }

    @Test
    public void data2() throws IOException {
        char[][] map = FileInputSource.getMap("/day3/data");
        long m = TreeCounter.count(map, 1, 1) *
                TreeCounter.count(map, 3, 1) *
                TreeCounter.count(map, 5, 1) *
                TreeCounter.count(map, 7, 1) *
                TreeCounter.count(map, 1, 2);
        assertEquals(9406609920L, m);
    }
}
