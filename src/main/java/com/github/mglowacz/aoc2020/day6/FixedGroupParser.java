package com.github.mglowacz.aoc2020.day6;

import java.util.*;

public class FixedGroupParser {
    private final List<String> strings;

    public FixedGroupParser(List<String> strings) {
        this.strings = strings;
    }


    List<Group> getGroups() {
        List<Group> groups = new LinkedList<>();
        Group group = new Group();

        for (String s : strings) {
            if (s.trim().isEmpty()) {
                groups.add(group);
                group = new Group();
            } else {
                Set<Character> cs = new HashSet<>();
                for(char c : s.toCharArray()) {
                    cs.add(c);
                }
                group.addAnswer(cs);
            }
        }

        groups.add(group);
        return groups;
    }
}
