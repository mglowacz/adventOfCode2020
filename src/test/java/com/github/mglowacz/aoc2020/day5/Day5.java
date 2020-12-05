package com.github.mglowacz.aoc2020.day5;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5 {
    @Test
    public void test() {
        assertEquals(357, SeatIdResolver.getId("FBFBBFFRLR"));
    }

    @Test
    public void data() throws IOException {
        int maxId = 0;
        for (String seatCode : FileInputSource.getStrings("/day5/data")) {
            int id = SeatIdResolver.getId(seatCode);
            if (maxId < id) maxId = id;
        }
        assertEquals(974, maxId);
    }

    @Test
    public void part2() throws IOException {
        char[][] seats = new char[127][8];
        for (String seatCode : FileInputSource.getStrings("/day5/data")) {
            int row = SeatIdResolver.getRow(seatCode);
            int col = SeatIdResolver.getCol(seatCode);
            seats[row][col] = 'x';
        }

        int rowNo = 0;
        int colNo = 0;
        boolean b = false;
        x : for (char[] row : seats) {
            if (!b && String.valueOf(row).equals("xxxxxxxx")) {
                b = true;
                rowNo++;
                continue;
            }
            if (b && !String.valueOf(row).equals("xxxxxxxx")) {
                for (char c : row) {
                    if (c != 'x') {
                        break x;
                    }
                    colNo++;
                }
            }
            rowNo++;
        }

        assertEquals(646, rowNo * 8 + colNo);

    }

}
