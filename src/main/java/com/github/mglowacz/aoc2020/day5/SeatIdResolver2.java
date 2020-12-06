package com.github.mglowacz.aoc2020.day5;

import static java.lang.Integer.parseInt;

public class SeatIdResolver2 {
    public static int getId(String code) {
        return getRow(code) * 8 + getCol(code);
    }

    static int getRow(String code) {
        return parseInt(
                code.substring(0, 7).replace("F", "0").replace("B", "1"), 2);
    }

    static int getCol(String code) {
        return parseInt(
                code.substring(7, 10).replace("R", "1").replace("L", "0"), 2);
    }
}
