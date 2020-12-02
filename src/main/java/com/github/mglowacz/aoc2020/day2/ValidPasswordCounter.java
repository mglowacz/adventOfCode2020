package com.github.mglowacz.aoc2020.day2;

import java.util.List;

public class ValidPasswordCounter {

    private final Validator validator;

    public ValidPasswordCounter(Validator validator) {
        this.validator = validator;
    }

    public int validPasswordsLinesCount(List<String> strings) {
        int count = 0;
        for (String line : strings) {
            String[] l = line.split(": ");
            String policy = l[0];
            String pass = l[1];

            if (validator.validate(policy, pass)) {
                count++;
            }
        }
        return count;
    }

}
