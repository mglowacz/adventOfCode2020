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
}
