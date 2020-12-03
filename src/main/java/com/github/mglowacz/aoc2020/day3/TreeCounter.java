package com.github.mglowacz.aoc2020.day3;

public class TreeCounter {

    static long count(char[][] map, int x, int y) {
        int row = 0;
        int trees = 0;
        int col = 0;


        while(row < map.length) {
            if (map[row][col] == '#')
                trees++;
            row = row + y;
            col = (col + x) % map[0].length;
        }
        return trees;
    }
}
