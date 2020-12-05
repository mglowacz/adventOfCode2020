package com.github.mglowacz.aoc2020.day4;

public class SimpleValidator {
    boolean isValid(Passport passport) {
        return passport.hasField("byr") &&
                passport.hasField("iyr") &&
                passport.hasField("eyr") &&
                passport.hasField("hgt") &&
                passport.hasField("hcl") &&
                passport.hasField("ecl") &&
                passport.hasField("pid");
        //p.containsKey("cid")
    }

}
