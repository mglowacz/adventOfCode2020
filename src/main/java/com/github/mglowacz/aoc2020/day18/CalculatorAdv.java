package com.github.mglowacz.aoc2020.day18;

public class CalculatorAdv extends Calculator {
    public CalculatorAdv(String input) {
        super(input);
        priorities.clear();
        priorities.put("(", 0);
        priorities.put("+", 1);
        priorities.put("*", 2);
        priorities.put(")", 3);
    }
}
