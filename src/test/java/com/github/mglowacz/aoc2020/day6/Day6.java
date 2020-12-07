package com.github.mglowacz.aoc2020.day6;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6 {
    @Test
    public void test() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day6/test");
        int sum = new GroupParser(strings).getGroups().stream().map(Set::size).reduce(Integer::sum).orElseThrow();
        assertEquals(11, sum);
    }

    @Test
    public void data() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day6/data");
        int sum = new GroupParser(strings).getGroups().stream().map(Set::size).reduce(Integer::sum).orElseThrow();
        assertEquals(6763, sum);
    }

    @Test
    public void test2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day6/test");
        int sum = new FixedGroupParser(strings).getGroups().stream().map(Group::getGroupUniqueAnswersCount).reduce(Integer::sum).orElseThrow();
        assertEquals(6, sum);
    }

    @Test
    public void data2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day6/data");
        int sum = new FixedGroupParser(strings).getGroups().stream().map(Group::getGroupUniqueAnswersCount).reduce(Integer::sum).orElseThrow();
        assertEquals(3512, sum);
    }

}
