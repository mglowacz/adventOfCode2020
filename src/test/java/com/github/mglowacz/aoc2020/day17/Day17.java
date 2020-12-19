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
        for (int j = 0; j < map1.length; j++)
            for (int i = 0; i < map1.length; i++)
                map[size / 2][j + size / 2 - 1][i + size / 2] = map1[j][i];


        assertEquals(112, nuberOfActivesAfterCycles(map, 6));
    }

    @Test
    public void data() throws IOException {
        int size = 30;
        char[][][] map = new char[size][size][size];

        char[][] map1 = FileInputSource.getMap("/day17/data");
        for (int j = 0; j < map1.length; j++)
            for (int i = 0; i < map1.length; i++)
                map[size / 2][j + size / 2 - 1][i + size / 2] = map1[j][i];

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
                            result[k][j][i] = (n == 2 || n == 3) ? '#' : '.';
                        } else {
                            result[k][j][i] = (n == 3) ? '#' : '.';
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


    @Test
    public void test2() throws IOException {
        int size = 20;
        char[][][][] map = new char[size][size][size][size];

        char[][] map1 = FileInputSource.getMap("/day17/test");
        for (int j = 0; j < map1.length; j++)
            for (int i = 0; i < map1.length; i++)
                map[size / 2][size / 2][j + size / 2 - 1][i + size / 2] = map1[j][i];

        assertEquals(848, nuberOfActivesAfterCycles4(map, 6));
    }

    @Test
    public void data2() throws IOException {
        int size = 30;
        char[][][][] map = new char[size][size][size][size];

        char[][] map1 = FileInputSource.getMap("/day17/data");
        for (int j = 0; j < map1.length; j++)
            for (int i = 0; i < map1.length; i++)
                map[size / 2][size / 2][j + size / 2 - 1][i + size / 2] = map1[j][i];

        assertEquals(2264, nuberOfActivesAfterCycles4(map, 6));
    }

    private int nuberOfActivesAfterCycles4(char[][][][] map, int cycles) {
        int size = map.length;
        int actives = 0;
        for (int c = 0; c < cycles; c++) {
            actives = 0;
            char[][][][] result = new char[size][size][size][size];
            for (int m = 0; m < size; m++)
                for (int k = 0; k < size; k++) {
                    for (int j = 0; j < size; j++) {
                        for (int i = 0; i < size; i++) {
                            int n = numberOfActiveNeighbors4(map, m, k, j, i);
                            if (map[m][k][j][i] == '#') {
                                result[m][k][j][i] = (n == 2 || n == 3) ? '#' : '.';
                            } else {
                                result[m][k][j][i] = (n == 3) ? '#' : '.';
                            }
                        }
                    }
                }

            for (int m = 0; m < size; m++)
                for (int k = 0; k < size; k++) {
                    for (int j = 0; j < size; j++) {
                        for (int i = 0; i < size; i++) {
                            if (result[m][k][j][i] == '#') {
                                map[m][k][j][i] = '#';
                                actives++;
                            } else {
                                map[m][k][j][i] = '.';
                            }
                        }
                    }
                }
        }
        return actives;
    }


    private int numberOfActiveNeighbors4(char[][][][] map, int m, int k, int j, int i) {
        int n = 0;

        for (int a = -1; a <= 1; a++)
            for (int b = -1; b <= 1; b++)
                for (int c = -1; c <= 1; c++)
                    for (int d = -1; d <= 1; d++)
                        if (!(a == 0 && b == 0 && c == 0 && d == 0)
                                && m + a >= 0 && m + a < map.length
                                && k + b >= 0 && k + b < map.length
                                && j + c >= 0 && j + c < map.length
                                && i + d >= 0 && i + d < map.length
                                && map[m + a][k + b][j + c][i + d] == '#') n++;
        return n;
    }

}



