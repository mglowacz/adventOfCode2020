package com.github.mglowacz.aoc2020.day15;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15 {

    @Test
    public void test() {
        assertEquals(436, play(asList(0, 3, 6), 2020));
        assertEquals(1, play(asList(1, 3, 2), 2020));
        assertEquals(10, play(asList(2, 1, 3), 2020));
        assertEquals(27, play(asList(1, 2, 3), 2020));
        assertEquals(78, play(asList(2, 3, 1), 2020));
        assertEquals(438, play(asList(3, 2, 1), 2020));
        assertEquals(1836, play(asList(3, 1, 2), 2020));
    }

    @Test
    public void data() {
        assertEquals(1696, play(asList(12, 1, 16, 3, 11, 0), 2020));
    }

    private int play(List<Integer> list, int length) {
        Map<Integer, AtomicInteger> ages = new HashMap<>(10000000);
        int last = 0;

        for (int i = 1; i <= list.size(); i++) {
            ages.put(last = list.get(i - 1), new AtomicInteger(i));
        }

        for (int i = list.size() + 1; i < length + 1; i++) {
            int n;
            AtomicInteger age = ages.get(last);
            if (age != null && age.get() != i - 1) {
                n = i - age.get() - 1;
                age.set(i - 1);
            } else {
                n = 0;
            }

            if (ages.get(n) == null) {
                ages.put(n, new AtomicInteger(i));
            }

            last = n;
        }
        return last;
    }


    @Test
    public void test2() {
        assertEquals(175594, play(asList(0, 3, 6), 30000000));
        assertEquals(2578, play(asList(1, 3, 2), 30000000));
        assertEquals(3544142, play(asList(2, 1, 3), 30000000));
        assertEquals(261214, play(asList(1, 2, 3), 30000000));
        assertEquals(6895259, play(asList(2, 3, 1), 30000000));
        assertEquals(18, play(asList(3, 2, 1), 30000000));
        assertEquals(362, play(asList(3, 1, 2), 30000000));
    }

    @Test
    public void data2() {
        assertEquals(37385, play(asList(12, 1, 16, 3, 11, 0), 30000000));
    }
}
