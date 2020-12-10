package com.github.mglowacz.aoc2020.day9;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9 {
    @Test
    public void test() throws IOException {
        List<Long> numbers = FileInputSource.getLongs("/day9/test");
        assertEquals(127, findWrongNumber(numbers, 5));
    }

    private long findWrongNumber(List<Long> numbers, int pSize) {
        long n;
        for (int k = pSize; k < numbers.size(); k++) {
            n = numbers.get(k);
            boolean s = false;
            b: for (int j = k - pSize; j < numbers.size() - 1; j++)
                for (int i = j + 1; i < numbers.size(); i++) {
                    if (numbers.get(k) == numbers.get(i) + numbers.get(j)) {
                        s = true;
                        break b;
                    }
                }
            if (!s) return n;
        }
        return -1;
    }

    @Test
    public void data() throws IOException {
        List<Long> longs = FileInputSource.getLongs("/day9/data");
        assertEquals(26796446, findWrongNumber(longs, 25));
    }

    @Test
    public void test2() throws IOException {
        List<Long> numbers = FileInputSource.getLongs("/day9/test");
        assertEquals(62, findWeakness(numbers, findWrongNumber(numbers, 5)));
    }

    private long findWeakness(List<Long> numbers, long wrongNumber) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            long sum = 0;
            for (int j = i; j < numbers.size(); j++) {
                sum+=numbers.get(j);
                if (sum == wrongNumber) {
                    long min = numbers.subList(i, j).stream().reduce(Math::min).orElseThrow();
                    long max = numbers.subList(i, j).stream().reduce(Math::max).orElseThrow();
                    return min + max;
                }
                if (sum > wrongNumber) {
                    break;
                }
            }
        }
        return -1;
    }

    @Test
    public void data2() throws IOException {
        List<Long> numbers = FileInputSource.getLongs("/day9/data");
        assertEquals(3353494, findWeakness(numbers, findWrongNumber(numbers, 25)));
    }

}
