package com.github.mglowacz.aoc2020.day4;

import java.util.regex.Pattern;

/**
 *
 byr (Birth Year) - four digits; at least 1920 and at most 2002.
 iyr (Issue Year) - four digits; at least 2010 and at most 2020.
 eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
 hgt (Height) - a number followed by either cm or in:
 If cm, the number must be at least 150 and at most 193.
 If in, the number must be at least 59 and at most 76.
 hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
 ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
 pid (Passport ID) - a nine-digit number, including leading zeroes.
 cid (Country ID) - ignored, missing or not.

 */
public class ExtendedValidator extends SimpleValidator {
    @Override
    boolean isValid(Passport passport) {
        if (!super.isValid(passport)) return false;
        int byr = Integer.parseInt(passport.get("byr"));
        if (byr < 1920 || byr > 2002) return false;

        int iyr = Integer.parseInt(passport.get("iyr"));
        if (iyr < 2010 || iyr > 2020) return false;

        int eyr = Integer.parseInt(passport.get("eyr"));
        if (eyr < 2020 || eyr > 2030) return false;

        String hgt = passport.get("hgt");
        if(!hgt.endsWith("cm") && !hgt.endsWith("in")) return false;
        int i = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
        if (hgt.endsWith("cm")) {
            if (i < 150 || i > 193) return false;
        }
        if (hgt.endsWith("in")) {
            if (i < 59 || i > 76) return false;
        }

        String hcl = passport.get("hcl");
        if (!hcl.startsWith("#")) return false;
        if(!Pattern.matches("[a-f0-9]{6}", hcl.substring(1))) return false;

        String ecl = passport.get("ecl");
        if (!ecl.equals("amb") && !ecl.equals("blu") && !ecl.equals("brn") && !ecl.equals("gry") && !ecl.equals("grn") && !ecl.equals("hzl") && !ecl.equals("oth"))
            return false;

        String pid = passport.get("pid");
        if(!Pattern.matches("[0-9]{9}", pid)) return false;
        return true;
    }

}
