package com.github.mglowacz.aoc2020.day2;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2 {
    @Test
    public void test1() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day2/test1");
        assertEquals(2, new ValidPasswordCounter(new PasswordValidator()).validPasswordsLinesCount(strings));
    }

    @Test
    public void data() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day2/data");
        assertEquals(434, new ValidPasswordCounter(new PasswordValidator()).validPasswordsLinesCount(strings));
    }

    @Test
    public void test2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day2/test1");
        assertEquals(1, new ValidPasswordCounter(new OTCPValidator()).validPasswordsLinesCount(strings));
    }

    @Test
    public void data2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day2/data");
        assertEquals(509, new ValidPasswordCounter(new OTCPValidator()).validPasswordsLinesCount(strings));
    }
}
