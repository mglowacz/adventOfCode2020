package com.github.mglowacz.aoc2020.day5;

public class SeatIdResolver {
    public static int getId(String code) {
        return getRow(code) * 8 + getCol(code);
    }

    static int getCol(String code) {
        String colCode = code.substring(7, 10);
        int min = 0;
        int max = 7;
        for(char c : colCode.toCharArray()) {
            if (c == 'L') {
                max = min + (max - min + 1) / 2 - 1;
            } else if (c == 'R') {
                min = min + (max - min + 1) / 2;
            }
        }
        return min;
    }

    static int getRow(String code) {
        String rowCode = code.substring(0, 7);
        int min = 0;
        int max = 127;
        for(char c : rowCode.toCharArray()) {
            if (c == 'F') {
                max = min + (max - min + 1) / 2 - 1;
            } else if (c == 'B') {
                min = min + (max - min + 1) / 2;
            }
        }
        return min;
    }
}
