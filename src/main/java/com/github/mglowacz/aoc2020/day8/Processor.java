package com.github.mglowacz.aoc2020.day8;

import java.util.LinkedList;
import java.util.List;

public class Processor {
    int index;
    int accumulator;

    private static final List<Integer> indexRegister = new LinkedList<>();

    public Processor() {
        indexRegister.clear();
    }

    public boolean run(List<String> strings) {
        while (index < strings.size()) {
            if (indexRegister.contains(this.index)) {
                return false;
            }
            indexRegister.add(this.index);
            switch (strings.get(index).split(" ")[0].trim()) {
                case "acc":
                    accumulator += Integer.parseInt(strings.get(index).split(" ")[1].trim());
                    break;
                case "jmp":
                    index += Integer.parseInt(strings.get(index).split(" ")[1].trim());
                    continue;
                case "nop":
            }
            index++;
        }
        return true;
    }

}
