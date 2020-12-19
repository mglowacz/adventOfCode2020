package com.github.mglowacz.aoc2020.day17;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day17 {
    @Test
    public void test() throws IOException {
        int size = 20;
        char[][][] map = new char[size][size][size];

        char[][] map1 = FileInputSource.getMap("/day17/test");
        for (int j = 0; j < map1.length; j++) {
            System.arraycopy(map1[j], 0, map[size / 2][j + size / 2 - 1], size / 2 - 1, map1.length);
        }

        assertEquals(112, nuberOfActivesAfterCycles(map, 6));
    }

    @Test
    public void data() throws IOException {
        int size = 30;
        char[][][] map = new char[size][size][size];

        char[][] map1 = FileInputSource.getMap("/day17/data");
        for (int j = 0; j < map1.length; j++) {
            System.arraycopy(map1[j], 0, map[size / 2][j + size / 2 - 1], size / 2 - 1, map1.length);
        }

        assertEquals(391, nuberOfActivesAfterCycles(map, 6));
    }

    private int nuberOfActivesAfterCycles(char[][][] map, int cycles) {
        int size = map.length;
        int actives = 0;
        for (int c = 0; c < cycles; c++) {
            actives = 0;
            char[][][] result = new char[size][size][size];
            for (int k = 0; k < size; k++) {
                for (int j = 0; j < size; j++) {
                    for (int i = 0; i < size; i++) {
                        int n = numberOfActiveNeighbors(map, k, j, i);
                        if (map[k][j][i] == '#') {
                            if (n == 2 || n == 3) {
                                result[k][j][i] = '#';
                            } else {
                                result[k][j][i] = '.';
                            }
                        } else {
                            if (n == 3) {
                                result[k][j][i] = '#';
                            } else {
                                result[k][j][i] = '.';
                            }
                        }
                    }
                }
            }

            for (int k = 0; k < size; k++) {
                for (int j = 0; j < size; j++) {
                    for (int i = 0; i < size; i++) {
                        if (result[k][j][i] == '#') {
                            map[k][j][i] = '#';
                            actives++;
                        } else {
                            map[k][j][i] = '.';
                        }
                    }
                }
            }
        }
        return actives;
    }

    private int numberOfActiveNeighbors(char[][][] map, int k, int j, int i) {
        int n = 0;

        for (int a = -1; a <= 1; a++)
            for (int b = -1; b <= 1; b++)
                for (int c = -1; c <= 1; c++) {

                    if (!(a == 0 && b == 0 && c == 0)
                            && k + a >= 0 && k + a < map.length
                            && j + b >= 0 && j + b < map.length
                            && i + c >= 0 && i + c < map.length
                            && map[k + a][j + b][i + c] == '#') n++;
                }

        return n;
    }
}
