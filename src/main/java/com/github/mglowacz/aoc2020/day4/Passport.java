package com.github.mglowacz.aoc2020.day4;

import java.util.HashMap;
import java.util.Map;

public class Passport {
    Map<String, String> fields = new HashMap<>();

    void addField(String key, String value) {
        fields.put(key, value);
    }

    boolean hasField(String field) {
        return fields.containsKey(field);
    }

    public String get(String field) {
        return fields.get(field);
    }
}
