package com.github.mglowacz.aoc2020.day14;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Computer {
    private final Map<Integer, Long> memory = new HashMap<>();
    private String mask = "";

    public Computer(List<String> program) {
        for (String str : program) {
            String[] split = str.split("=");
            String param = split[0].trim();
            String value = split[1].trim();
            switch (param.split("\\[")[0]) {
                case "mask":
                    mask = value;
                    break;
                case "mem":
                    int mem = Integer.parseInt(param.split("(\\[|\\])")[1]);
                    int val = Integer.parseInt(value);
                    memory.put(mem, calcValue(val));
                    break;
            }
        }
    }

    private long calcValue(int val) {
        BigInteger value = BigInteger.valueOf(val);
        for(int i = mask.length() - 1 ; i >=0 ; i--) {
            if (mask.charAt(i) == '0')
                value = value.clearBit(mask.length() - i - 1);
            else if (mask.charAt(i) == '1')
                value = value.setBit(mask.length() - i - 1);

        }
        return value.longValue();
    }

    public Map<Integer, Long> getMemory() {
        return memory;
    }
}
