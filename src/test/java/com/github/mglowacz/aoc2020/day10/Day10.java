package com.github.mglowacz.aoc2020.day10;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10 {
    @Test
    public void test() throws IOException {
        List<Integer> adapters = FileInputSource.getIntegers("/day10/test");
        assertEquals(5 * 7, getChainNumber(adapters));
    }

    private int getChainNumber(List<Integer> adapters) {
        adapters.add(0);
        Collections.sort(adapters);

        int diff1 = 0;
        int diff3 = 1;

        for (int i = 1; i < adapters.size(); i++) {
            switch (adapters.get(i) - adapters.get(i - 1)) {
                case 1:
                    diff1++;
                    break;
                case 3:
                    diff3++;
                    break;
            }
        }
        return diff1 * diff3;
    }

    @Test
    public void test2() throws IOException {
        List<Integer> adapters = FileInputSource.getIntegers("/day10/test2");
        assertEquals(22 * 10, getChainNumber(adapters));
    }

    @Test
    public void data() throws IOException {
        List<Integer> adapters = FileInputSource.getIntegers("/day10/data");
        assertEquals(2400, getChainNumber(adapters));
    }

    @Test
    public void test3() throws IOException {
        List<Integer> adapters = FileInputSource.getIntegers("/day10/test2");
        assertEquals(19208, getConnectionCount(adapters));
    }

    private int getConnectionCount(List<Integer> adapters) {
        int max = adapters.stream().reduce(Math::max).orElseThrow();
        adapters.add(max + 3);
        Collections.sort(adapters);

        Deque<Integer> q = new LinkedList<>(Collections.singletonList(0));
        int s = 0;
        while (!q.isEmpty()) {
            int v = q.pop();
            if (v == max) {
                s++;
                if (s % 10000 == 0) System.out.println(s);
            } else {
                adapters.stream().filter(i -> i <= v + 3 && i > v).forEach(q::add);
            }
        }
        return s;
    }

    @Test
    public void test3_2() throws IOException {
        List<Integer> adapters = FileInputSource.getIntegers("/day10/test");
        adapters.add(0);
        Collections.sort(adapters);
        adapters.add(adapters.get(adapters.size() - 1) + 3);
        assertEquals(8, getFor(adapters));
    }

    long getFor(List<Integer> adapters) {
        if (adapters.size() == 2) return 1;
        long n = 0;
        int cnt = 1;
        while (adapters.get(cnt + 1) - adapters.get(cnt) < 3) cnt++;

        if (cnt == 1) {
            n = 1;
        } else {
            int max = adapters.get(cnt);
            Deque<Integer> q = new LinkedList<>(Collections.singletonList(adapters.get(0)));
            while (!q.isEmpty()) {
                int v = q.pop();
                if (v == max) {
                    n++;
                    continue;
                }
                adapters.stream().filter(ii -> ii <= max && ii < v + 4 && ii > v).forEach(q::add);
            }
        }
        return n * getFor(adapters.subList(cnt, adapters.size()));
    }

    @Test
    public void test3_3() throws IOException {
        List<Integer> adapters = FileInputSource.getIntegers("/day10/test2");
        adapters.add(0);
        Collections.sort(adapters);
        adapters.add(adapters.get(adapters.size() - 1) + 3);
        assertEquals(19208, getFor(adapters));
    }


    @Test
    public void data2() throws IOException {
        List<Integer> adapters = FileInputSource.getIntegers("/day10/data");
        adapters.add(0);
        Collections.sort(adapters);
        adapters.add(adapters.get(adapters.size() - 1) + 3);
        assertEquals(338510590509056L, getFor(adapters));
    }
}
