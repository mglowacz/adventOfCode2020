package com.github.mglowacz.aoc2020.day2;

public class PasswordValidator implements Validator {

    @Override
    public boolean validate(String policy, String pass) {
        char c = policy.split(" ")[1].charAt(0);
        int min = Integer.parseInt(policy.split(" ")[0].split("-")[0]);
        int max = Integer.parseInt(policy.split(" ")[0].split("-")[1]);

        long charsCount = pass.chars().filter(value -> value == c).count();

        return (charsCount >= min && charsCount <= max);
    }
}
