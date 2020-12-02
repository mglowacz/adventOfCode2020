package com.github.mglowacz.aoc2020.day1;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {
    @Test
    public void test1() throws IOException {
        List<Integer> integers = FileInputSource.getIntegers("/day1/test1");
        int q = findQuotient2(integers);
        assertEquals(514579, q);
    }

    @Test
    public void data1() throws IOException {
        List<Integer> integers = FileInputSource.getIntegers("/day1/data");
        int q = findQuotient2(integers);
        assertEquals(646779, q);
    }

    private int findQuotient2(List<Integer> integers) {
        for (Integer i : integers) {
            for (Integer j : integers) {
                if (i + j == 2020) {
                    return i * j;
                }
            }
        }
        throw new RuntimeException("No such pair");
    }

    @Test
    public void test2() throws IOException {
        List<Integer> integers = FileInputSource.getIntegers("/day1/test1");
        int q = findQuotient3(integers);
        assertEquals(241861950, q);
    }

    @Test
    public void data2() throws IOException {
        List<Integer> integers = FileInputSource.getIntegers("/day1/data");
        int q = findQuotient3(integers);
        assertEquals(246191688, q);
    }

    private int findQuotient3(List<Integer> integers) {
        for (Integer i : integers) {
            for (Integer j : integers) {
                for (Integer k : integers) {
                    if (i + j + k == 2020) {
                        return i * j * k;
                    }
                }
            }
        }
        throw new RuntimeException("No such pair");
    }

}
