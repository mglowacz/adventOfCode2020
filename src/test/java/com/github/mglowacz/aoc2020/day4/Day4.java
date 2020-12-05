package com.github.mglowacz.aoc2020.day4;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4 {
    @Test
    public void test() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day4/test");

        List<Passport> passports = new PassportParser(strings).getPassports();
        SimpleValidator simpleValidator = new SimpleValidator();
        int cnt = 0;
        for (Passport passport1 : passports) {
            cnt += simpleValidator.isValid(passport1) ? 1 : 0;
        }
        assertEquals(2, cnt);
    }

    @Test
    public void data() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day4/data");

        List<Passport> passports = new PassportParser(strings).getPassports();
        SimpleValidator simpleValidator = new SimpleValidator();
        int cnt = 0;
        for (Passport passport1 : passports) {
            cnt += simpleValidator.isValid(passport1) ? 1 : 0;
        }
        assertEquals(196, cnt);
    }

    @Test
    public void test2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day4/test2");

        List<Passport> passports = new PassportParser(strings).getPassports();
        ExtendedValidator simpleValidator = new ExtendedValidator();
        int cnt = 0;
        for (Passport passport1 : passports) {
            cnt += simpleValidator.isValid(passport1) ? 1 : 0;
        }
        assertEquals(0, cnt);
    }

    @Test
    public void test3() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day4/test3");

        List<Passport> passports = new PassportParser(strings).getPassports();
        ExtendedValidator simpleValidator = new ExtendedValidator();
        int cnt = 0;
        for (Passport passport1 : passports) {
            cnt += simpleValidator.isValid(passport1) ? 1 : 0;
        }
        assertEquals(4, cnt);
    }

    @Test
    public void data2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day4/data");

        List<Passport> passports = new PassportParser(strings).getPassports();
        ExtendedValidator simpleValidator = new ExtendedValidator();
        int cnt = 0;
        for (Passport passport1 : passports) {
            cnt += simpleValidator.isValid(passport1) ? 1 : 0;
        }
        assertEquals(114, cnt);
    }
}
