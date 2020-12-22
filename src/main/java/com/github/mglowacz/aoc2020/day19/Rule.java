package com.github.mglowacz.aoc2020.day19;

import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

class Rule {
    final List<String> subrules;
    final String message;

    public Rule(List<String> subrules, String message) {
        this.subrules = new LinkedList<>(subrules);
        this.message = message.trim();
    }

    public Rule(String subrules, String message) {
        this.subrules = asList(subrules.trim().split(" "));
        this.message = message.trim();
    }
}