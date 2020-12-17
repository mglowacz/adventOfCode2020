package com.github.mglowacz.aoc2020.day14;

import java.math.BigInteger;
import java.util.*;

public class ComputerV2 {
    private final Map<Long, Long> memory = new HashMap<>();
    private String mask = "";

    public ComputerV2(List<String> program) {
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
                    long val = Long.parseLong(value);
                    for(long address : calcMemoryAddresses(mem)) {
                        memory.put(address, val);
                    }
                    break;
            }
        }
    }

    private long[] calcMemoryAddresses(int mem) {
        List<Long> addresses = new LinkedList<>();
        BigInteger value = BigInteger.valueOf(mem);

        StringBuilder m = new StringBuilder("000000000000000000000000000000000000");
        for (int i = 0 ; i < m.length() ; i++) {
            if (value.testBit(i)) {
                m.setCharAt(m.length() - i - 1, '1');
            }
        }

        for(int i = 0 ; i < mask.length() ; i++) {
            if (mask.charAt(i) == '0');
            else if (mask.charAt(i) == '1')
                m.setCharAt(i, '1');
            else if (mask.charAt(i) == 'X')
                m.setCharAt(i, 'X');

        }
        long xs = m.toString().chars().filter(value1 -> value1 == 'X').count();
        for (int i = 0 ; i < Math.pow(2, xs) ; i++) {
            String m1 = m.toString();
            for (int x = 0; x < xs; x++) {
                m1 = m1.replaceFirst("X", BigInteger.valueOf(i).testBit(x) ? "1" : "0");
            }
            addresses.add(new BigInteger(m1).longValue());
        }
        return addresses.stream().mapToLong(i -> i).toArray();
    }

    public Map<Long, Long> getMemory() {
        return memory;
    }
}
