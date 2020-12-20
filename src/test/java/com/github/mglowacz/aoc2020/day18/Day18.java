package com.github.mglowacz.aoc2020.day18;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day18 {
    @Test
    public void test() {
        assertEquals(71, new Calculator("1 + 2 * 3 + 4 * 5 + 6").calculate());
    }

    @Test
    public void test2() {
        assertEquals(51, new Calculator("1 + (2 * 3) + (4 * (5 + 6))").calculate());
    }

    @Test
    public void test3() {
        assertEquals(26, new Calculator("2 * 3 + (4 * 5)").calculate());
    }

    @Test
    public void test4() {
        assertEquals(437, new Calculator("5 + (8 * 3 + 9 + 3 * 4 * 3)").calculate());
    }

    @Test
    public void test5() {
        assertEquals(12240, new Calculator("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))").calculate());
    }

    @Test
    public void test6() {
        assertEquals(13632, new Calculator("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2").calculate());
    }

    @Test
    public void data() throws IOException {
        long n = 0;
        for (String line : FileInputSource.getStrings("/day18/data")) {
            n += new Calculator(line).calculate();
        }
        assertEquals(6811433855019L, n);
    }


    @Test
    public void test7() {
        assertEquals(231, new CalculatorAdv("1 + 2 * 3 + 4 * 5 + 6").calculate());
    }

    @Test
    public void test8() {
        assertEquals(51, new CalculatorAdv("1 + (2 * 3) + (4 * (5 + 6))").calculate());
    }

    @Test
    public void test9() {
        assertEquals(46, new CalculatorAdv("2 * 3 + (4 * 5)").calculate());
    }

    @Test
    public void test10() {
        assertEquals(1445, new CalculatorAdv("5 + (8 * 3 + 9 + 3 * 4 * 3)").calculate());
    }

    @Test
    public void test11() {
        assertEquals(669060, new CalculatorAdv("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))").calculate());
    }

    @Test
    public void test12() {
        assertEquals(23340, new CalculatorAdv("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2").calculate());
    }

    @Test
    public void data2() throws IOException {
        long n = 0;
        for (String line : FileInputSource.getStrings("/day18/data")) {
            n += new CalculatorAdv(line).calculate();
        }
        assertEquals(129770152447927L, n);
    }
}
