package com.github.mglowacz.aoc2020.day19;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day19 {
    @Test
    public void test() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day19/test");
        assertEquals(2, new RuleMatcher(strings).matchesRuleCount("0"));
    }

    @Test
    public void test2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day19/test2");
        assertEquals(2, new RuleMatcher(strings).matchesRuleCount("0"));
    }

    @Test
    public void data() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day19/data");
        assertEquals(102, new RuleMatcher(strings).matchesRuleCount("0"));
    }

    @Test
    public void test3() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day19/test3");
        assertEquals(3, new RuleMatcher(strings).matchesRuleCount("0"));
    }

    @Test
    public void test4() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day19/test3");
        RuleMatcher ruleMatcher = new RuleMatcher(strings);
        ruleMatcher.updateRule("8: 42 | 42 8");
        ruleMatcher.updateRule("11: 42 31 | 42 11 31");
        assertEquals(12, ruleMatcher.matchesRuleCount("0"));
    }


    @Test
    public void data2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day19/data");
        RuleMatcher ruleMatcher = new RuleMatcher(strings);
        ruleMatcher.updateRule("8: 42 | 42 8");
        ruleMatcher.updateRule("11: 42 31 | 42 11 31");
        assertEquals(318, ruleMatcher.matchesRuleCount("0"));
    }
}
