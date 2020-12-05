package com.github.mglowacz.aoc2020.day4;

import java.util.LinkedList;
import java.util.List;

public class PassportParser {
    private final List<String> strings;
    PassportParser(List<String> strings) {
        this.strings = strings;
    }

    List<Passport> getPassports() {
        List<Passport> passports = new LinkedList<>();

        Passport passport = new Passport();
        for (String s : strings) {
            if (s.trim().isEmpty()) {
                passports.add(passport);
                passport = new Passport();
                continue;
            }

            for (String c : s.split(" ")) {
                passport.addField(c.split(":")[0], c.split(":")[1]);
            }
        }
        passports.add(passport);
        return passports;
    }
}
