package com.github.mglowacz.aoc2020.day8;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Day8 {
    @Test
    public void test() throws IOException {
        Processor processor = new Processor();
        boolean run = processor.run(FileInputSource.getStrings("/day8/test"));
        assertFalse(run);
        assertEquals(5, processor.accumulator);
    }

    @Test
    public void data() throws IOException {
        Processor processor = new Processor();
        boolean run = processor.run(FileInputSource.getStrings("/day8/data"));
        assertFalse(run);
        assertEquals(2014, processor.accumulator);
    }


    @Test
    public void test2() throws IOException {
        List<String> program = FileInputSource.getStrings("/day8/test");
        List<String> modified = new ArrayList<>(program);

        Processor processor = new Processor();
        int lineIxd = 0;
        while(!processor.run(modified)) {
            processor = new Processor();
            modified = new ArrayList<>(program);
            if (modified.get(lineIxd).split(" ")[0].trim().equals("nop")) {
                modified.set(lineIxd, modified.get(lineIxd).replace("nop", "jmp"));
            } else if (modified.get(lineIxd).split(" ")[0].trim().equals("jmp")) {
                modified.set(lineIxd, modified.get(lineIxd).replace("jmp", "nop"));
            }
            lineIxd++;
        }

        assertEquals(8, processor.accumulator);
    }

    @Test
    public void data2() throws IOException {
        List<String> program = FileInputSource.getStrings("/day8/data");
        List<String> modified = new ArrayList<>(program);

        Processor processor = new Processor();
        int lineIxd = 0;
        while(!processor.run(modified)) {
            processor = new Processor();
            modified = new ArrayList<>(program);
            if (modified.get(lineIxd).split(" ")[0].trim().equals("nop")) {
                modified.set(lineIxd, modified.get(lineIxd).replace("nop", "jmp"));
            } else if (modified.get(lineIxd).split(" ")[0].trim().equals("jmp")) {
                modified.set(lineIxd, modified.get(lineIxd).replace("jmp", "nop"));
            }
            lineIxd++;
        }

        assertEquals(2251, processor.accumulator);
    }
}
