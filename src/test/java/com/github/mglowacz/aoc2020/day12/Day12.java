package com.github.mglowacz.aoc2020.day12;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12 {
    @Test
    public void test() throws IOException {
        List<String> commands = FileInputSource.getStrings("/day12/test");
        Ship ship = new Ship();
        for (String command : commands) {
            ship.move(command);
        }

        assertEquals(25, ship.getManhattanDistance());
    }

    @Test
    public void data() throws IOException {
        List<String> commands = FileInputSource.getStrings("/day12/data");
        Ship ship = new Ship();
        for (String command : commands) {
            ship.move(command);
        }

        assertEquals(636, ship.getManhattanDistance());
    }

    @Test
    public void test2() throws IOException {
        List<String> commands = FileInputSource.getStrings("/day12/test");
        Ship2 ship = new Ship2();
        for (String command : commands) {
            ship.move(command);
        }

        assertEquals(286, ship.getManhattanDistance());
    }

    @Test
    public void data2() throws IOException {
        List<String> commands = FileInputSource.getStrings("/day12/data");
        Ship2 ship = new Ship2();
        for (String command : commands) {
            ship.move(command);
        }

        assertEquals(26841, ship.getManhattanDistance());
    }
}
