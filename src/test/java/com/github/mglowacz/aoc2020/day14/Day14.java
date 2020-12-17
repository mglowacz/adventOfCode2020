package com.github.mglowacz.aoc2020.day14;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14 {
    @Test
    public void test() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day14/test");
        Computer computer = new Computer(strings);
        assertEquals(165, computer.getMemory().values().stream().reduce(Long::sum).orElseThrow());
    }

    @Test
    public void data() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day14/data");
        Computer computer = new Computer(strings);
        assertEquals(14954914379452L, computer.getMemory().values().stream().reduce(Long::sum).orElseThrow());
    }

    @Test
    public void test2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day14/test2");
        ComputerV2 computer = new ComputerV2(strings);
        assertEquals(208, computer.getMemory().values().stream().reduce(Long::sum).orElseThrow());
    }

    @Test
    public void data2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day14/data");
        ComputerV2 computer = new ComputerV2(strings);
        assertEquals(3415488160714L, computer.getMemory().values().stream().reduce(Long::sum).orElseThrow());
    }
}
