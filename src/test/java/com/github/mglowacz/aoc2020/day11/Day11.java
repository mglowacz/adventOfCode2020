package com.github.mglowacz.aoc2020.day11;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11 {
    @Test
    public void test() throws IOException {
        char[][] map = FileInputSource.getMap("/day11/test");
        int n = -1;
        int last = -2;
        while (n != last) {
            seatsRound(map);
            last = n;
            n = numberOfSeatsTaken(map);
        }
        assertEquals(37, n);
    }

    @Test
    public void data() throws IOException {
        char[][] map = FileInputSource.getMap("/day11/data");
        int n = -1;
        int last = -2;
        while (n != last) {
            seatsRound(map);
            last = n;
            n = numberOfSeatsTaken(map);
        }
        assertEquals(2448, n);
    }

    private void seatsRound(char[][] map) {
        char[][] m = new char[map.length][map[0].length];
        for (int j = 0; j < map.length; j++)
            for (int i = 0; i < map[0].length; i++) {
                char c = map[j][i];
                if (c == 'L'
                        && !isTaken(map, i - 1, j - 1)
                        && !isTaken(map, i - 1, j)
                        && !isTaken(map, i - 1, j + 1)
                        && !isTaken(map, i, j - 1)
                        && !isTaken(map, i, j + 1)
                        && !isTaken(map, i + 1, j - 1)
                        && !isTaken(map, i + 1, j)
                        && !isTaken(map, i + 1, j + 1)
                ) {
                    m[j][i] = '#';
                } else if (c == '#') {
                    int cnt = 0;
                    if (isTaken(map, i - 1, j - 1)) cnt++;
                    if (isTaken(map, i - 1, j)) cnt++;
                    if (isTaken(map, i - 1, j + 1)) cnt++;
                    if (isTaken(map, i, j - 1)) cnt++;
                    if (isTaken(map, i, j + 1)) cnt++;
                    if (isTaken(map, i + 1, j - 1)) cnt++;
                    if (isTaken(map, i + 1, j)) cnt++;
                    if (isTaken(map, i + 1, j + 1)) cnt++;
                    if (cnt >= 4) {
                        m[j][i] = 'L';
                    }
                }
            }
        for (int j = 0; j < m.length; j++)
            for (int i = 0; i < m[0].length; i++) {
                if (m[j][i] == 'L' || m[j][i] == '#') {
                    map[j][i] = m[j][i];
                }
            }
    }

    private boolean isTaken(char[][] map, int i, int j) {
        if (i < 0 || i >= map[0].length || j < 0 || j >= map.length)
            return false;
        return map[j][i] == '#';
    }

    private int numberOfSeatsTaken(char[][] map) {
        int cnt = 0;
        for (char[] row : map)
            for (char c : row) {
                if (c == '#') {
                    cnt++;
                }
            }
        return cnt;
    }

    @Test
    public void test2() throws IOException {
        char[][] map = FileInputSource.getMap("/day11/test");
        int n = -1;
        int last = -2;
        while (n != last) {
            seatsRound2(map);
            last = n;
            n = numberOfSeatsTaken(map);
        }
        assertEquals(26, n);
    }

    @Test
    public void data2() throws IOException {
        char[][] map = FileInputSource.getMap("/day11/data");
        int n = -1;
        int last = -2;
        while (n != last) {
            seatsRound2(map);
            last = n;
            n = numberOfSeatsTaken(map);
        }
        assertEquals(2234, n);
    }

    private void seatsRound2(char[][] map) {
        char[][] m = new char[map.length][map[0].length];
        for (int j = 0; j < map.length; j++)
            for (int i = 0; i < map[0].length; i++) {
                char c = map[j][i];
                if (c == 'L'
                        && !seeTaken(map, i, j, -1, -1)
                        && !seeTaken(map, i, j, -1, 0)
                        && !seeTaken(map, i, j, -1, +1)
                        && !seeTaken(map, i, j, 0, -1)
                        && !seeTaken(map, i, j, 0, +1)
                        && !seeTaken(map, i, j, +1, -1)
                        && !seeTaken(map, i, j, +1, 0)
                        && !seeTaken(map, i, j, +1, +1)
                ) {
                    m[j][i] = '#';
                } else if (c == '#') {
                    int cnt = 0;
                    if (seeTaken(map, i, j, -1, -1)) cnt++;
                    if (seeTaken(map, i, j, -1, 0)) cnt++;
                    if (seeTaken(map, i, j, -1, +1)) cnt++;
                    if (seeTaken(map, i, j, 0, -1)) cnt++;
                    if (seeTaken(map, i, j, 0, +1)) cnt++;
                    if (seeTaken(map, i, j, +1, -1)) cnt++;
                    if (seeTaken(map, i, j, +1, 0)) cnt++;
                    if (seeTaken(map, i, j, +1, +1)) cnt++;
                    if (cnt >= 5) {
                        m[j][i] = 'L';
                    }
                }
            }
        for (int j = 0; j < m.length; j++)
            for (int i = 0; i < m[0].length; i++) {
                if (m[j][i] == 'L' || m[j][i] == '#') {
                    map[j][i] = m[j][i];
                }
            }
    }

    private boolean seeTaken(char[][] map, int i, int j, int di, int dj) {
        i += di;
        j += dj;
        while (i >= 0 && i < map[0].length && j >= 0 && j < map.length) {
            if (map[j][i] == '#')
                return true;
            if (map[j][i] == 'L')
                return false;
            i += di;
            j += dj;
        }
        return false;
    }
}
