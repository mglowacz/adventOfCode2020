package com.github.mglowacz.aoc2020.day6;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Group {
    private final List<Set<Character>> answers = new LinkedList<>();

    void addAnswer(Set<Character> answers) {
        this.answers.add(answers);
    }

    int getGroupUniqueAnswersCount() {
        Set<Character> uniques = null;
        for (Set<Character> s : answers) {
            if (uniques == null) {
                uniques = new HashSet<>();
                uniques.addAll(s);
            } else {
                Set<Character> newUniques = new HashSet<>();
                for (Character c : s) {
                    if (uniques.contains(c)) {
                        newUniques.add(c);
                    }
                }
                uniques = newUniques;
            }
        }
        return uniques.size();
    }
}
