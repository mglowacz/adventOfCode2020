package com.github.mglowacz.aoc2020.day6;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GroupParser {
    private final List<String> strings;

    public GroupParser(List<String> strings) {
        this.strings = strings;
    }

    List<Set<String>> getGroups() {
        List<Set<String>> groups = new LinkedList<>();
        Set<String> group = new HashSet<>();
        for (String s : strings) {
            if (s.trim().isEmpty()) {
                groups.add(group);
                group = new HashSet<>();
            } else {
                for (char c : s.toCharArray()) {
                    group.add(String.valueOf(c));
                }
            }
        }
        groups.add(group);
        return groups;
    }
}
