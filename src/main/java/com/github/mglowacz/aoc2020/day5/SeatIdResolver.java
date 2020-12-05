package com.github.mglowacz.aoc2020.day5;

public class SeatIdResolver {
    public static int getId(String code) {
        String rowCode = code.substring(0, 7);
        String colCode = code.substring(7, 10);
        //0 - 127

//        F means to take the lower half, keeping rows 0 through 63.
//        B means to take the upper half, keeping rows 32 through 63.
//        F means to take the lower half, keeping rows 32 through 47.
//        B means to take the upper half, keeping rows 40 through 47.
//        B keeps rows 44 through 47.
//        F keeps rows 44 through 45.
//        The final F keeps the lower of the two, row 44.

        int min = 0;
        int max = 127;
        for(char c : rowCode.toCharArray()) {
            if (c == 'F') {
                max = min + (max - min + 1) / 2 - 1;
            } else if (c == 'B') {
                min = min + (max - min + 1) / 2;
            }
        }
        int row = min;

        min = 0;
        max = 7;
        for(char c : colCode.toCharArray()) {
            if (c == 'L') {
                max = min + (max - min + 1) / 2 - 1;
            } else if (c == 'R') {
                min = min + (max - min + 1) / 2;
            }
        }
        int col = min;
        return row * 8 + col;
    }
}
