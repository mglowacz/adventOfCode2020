package com.github.mglowacz.aoc2020.day18;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator {
    Map<String, Integer> priorities = new HashMap<>();
    LinkedList<String> f;
    Stack<String> s = new Stack<>();
    Stack<String> ops = new Stack<>();

    public Calculator(String input) {
        f = input.chars().mapToObj(c -> Character.valueOf((char) c).toString()).filter(character -> !character.trim().isEmpty()).collect(Collectors.toCollection(LinkedList::new));
        priorities.put("(", 0);
        priorities.put("+", 1);
        priorities.put("*", 1);
        priorities.put(")", 3);
    }

    public long calculate() {
        while (!f.isEmpty() || !ops.empty()) {
            if (f.isEmpty() && !ops.empty()) {
                doOperation();
            } else if (!f.isEmpty() && f.peek().matches("\\d+")) {
                s.push(f.poll());
            } else if (!f.isEmpty() && f.peek().matches("([+*()])")) {
                String nop = f.poll();
                if (!ops.empty() && priorities.get(nop) >= priorities.get(ops.peek())) {
                    doOperation();
                }
                if (nop.equals(")")) {
                    while (!ops.peek().equals("("))
                        doOperation();
                    ops.pop();
                } else {
                    ops.push(nop);
                }
            }
        }
        return Long.parseLong(s.pop());
    }

    private void doOperation() {
        if (ops.peek().equals("+")) {
            ops.pop();
            String arg0 = s.pop();
            String arg1 = s.pop();
            s.push(Long.toString(Long.parseLong(arg0) + Long.parseLong(arg1)));
        } else if (ops.peek().equals("*")) {
            ops.pop();
            String arg0 = s.pop();
            String arg1 = s.pop();
            s.push(Long.toString(Long.parseLong(arg0) * Long.parseLong(arg1)));
        }
    }
}
