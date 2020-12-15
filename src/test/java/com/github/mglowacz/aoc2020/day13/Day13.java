package com.github.mglowacz.aoc2020.day13;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13 {
    @Test
    public void test() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day13/test");
        int time = Integer.parseInt(strings.get(0));
        int[] ids = Arrays.stream(strings.get(1).split(",")).filter(s -> !s.equals("x")).map(Integer::parseInt).mapToInt(i -> i).toArray();
        assertEquals(295, getMinTimeValueTimesId(time, ids));
    }

    private int getMinTimeValueTimesId(int time, int[] ids) {
        int min = Integer.MAX_VALUE;
        int minId = 0;
        for (int i : ids) {
            int t = time / i;
            if ((t + 1) * i < min) {
                min = (t + 1) * i;
                minId = i;
            }
        }
        return (min - time) * minId;
    }

    @Test
    public void data() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day13/data");
        int time = Integer.parseInt(strings.get(0));
        int[] ids = Arrays.stream(strings.get(1).split(",")).filter(s -> !s.equals("x")).map(Integer::parseInt).mapToInt(i -> i).toArray();
        assertEquals(174, getMinTimeValueTimesId(time, ids));
    }

    @Test
    public void data2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day13/data");
        int[] ids = Arrays.stream(strings.get(1).split(",")).map(s -> s.equals("x") ? "0" : s).map(Integer::parseInt).mapToInt(i -> i).toArray();
        assertEquals(780601154795940L, cong(ids, 1, ids[0], 0));
    }

    @Test
    public void test2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day13/test");
        int[] ids = Arrays.stream(strings.get(1).split(",")).map(s -> s.equals("x") ? "0" : s).map(Integer::parseInt).mapToInt(i -> i).toArray();
        assertEquals(1068781, cong(ids, 1, ids[0], 0));
        int[] ids1 = new int[]{17, 0, 13, 19};
        assertEquals(3417, cong(ids1, 1, ids1[0], 0));
        int[] ids2 = new int[]{67, 7, 59, 61};
        assertEquals(754018, cong(ids2, 1, ids2[0], 0));
        int[] ids3 = new int[]{67, 0, 7, 59, 61};
        assertEquals(779210, cong(ids3, 1, ids3[0], 0));
        int[] ids4 = new int[]{67, 7, 0, 59, 61};
        assertEquals(1261476, cong(ids4, 1, ids4[0], 0));
        int[] ids5 = new int[]{1789, 37, 47, 1889};
        assertEquals(1202161486, cong(ids5, 1, ids5[0], 0));
    }

    private long cong(int[] ids, int i, long x, long xr) {
        if (ids[i] == 0) return cong(ids, i + 1, x, xr);
        long k = 0;
        long m = ids[i] - i;
        while (m < 0) m += ids[i];
        while ((x * k + xr) % ids[i] != (m % ids[i])) {
            k++;
        }
        if (i < ids.length - 1) {
            return cong(ids, i + 1, x * ids[i], x * k + xr);
        } else {
            return x * k + xr;
        }
    }

}
