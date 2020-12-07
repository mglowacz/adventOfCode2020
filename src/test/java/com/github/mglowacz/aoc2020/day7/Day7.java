package com.github.mglowacz.aoc2020.day7;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7 {

    @Test
    public void test() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day7/test");
        assertEquals(4, foundColorBags(strings));
    }

    private int foundColorBags(List<String> strings) {
        Map<String, Set<String>> sacks = new HashMap<>();
        for (String s : strings) {
            String[] contains = s.split("contain");
            String key = contains[0].replace("bags", "").trim();
            List<String> collect = Arrays.stream(contains[1]
                    .replace(".", "")
                    .replace("bags", "")
                    .replace("bag", "")
                    .split(","))
                    .map(s1 -> s1.trim().substring(s1.trim().indexOf(" ")))
                    .collect(Collectors.toList());
            collect.forEach(s1 -> {
                sacks.putIfAbsent(s1.trim(), new HashSet<>());
                sacks.get(s1.trim()).add(key);
            });
        }

        Deque<String> toSearch = new LinkedList<>(sacks.get("shiny gold"));
        Set<String> found = new HashSet<>();
        while (!toSearch.isEmpty()) {
            String pop = toSearch.pop();
            if (found.contains(pop)) continue;
            found.add(pop);
            if (sacks.containsKey(pop)) {
                toSearch.addAll(sacks.get(pop));
            }
        }
        return found.size();
    }


    @Test
    public void test2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day7/test");
        assertEquals(32, foundBagsCount(strings));
    }

    @Test
    public void test3() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day7/test2");
        assertEquals(126, foundBagsCount(strings));
    }

    @Test
    public void data2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day7/data");
        assertEquals(85324, foundBagsCount(strings));
    }

    static class BagGroup {
        String name;
        int count;
    }

    private int foundBagsCount(List<String> strings) {
        Map<String, Set<BagGroup>> sacks = new HashMap<>();
        for (String s : strings) {
            String[] contains = s.split("contain");
            String key = contains[0].replace("bags", "").trim();


            Set<BagGroup> collect = Arrays.stream(contains[1]
                    .replace(".", "")
                    .replace("bags", "")
                    .replace("bag", "")
                    .split(","))
                    .map(s1 -> {
                        BagGroup bg = new BagGroup();
                        bg.name = s1.trim().substring(s1.trim().indexOf(" ")).trim();
                        String quantity = s1.trim().split(" ")[0];
                        bg.count = quantity.equals("no") ? 0 : Integer.parseInt(quantity);
                        return bg;
                    })
                    .collect(Collectors.toSet());
            sacks.put(key, collect);
        }
        return getNumberFor("shiny gold", sacks) - 1;
    }

    private int getNumberFor(String bag, Map<String, Set<BagGroup>> sacks) {
        int cnt = 1;
        Set<BagGroup> bagGroups = sacks.get(bag);
        for (BagGroup bg : bagGroups) {
            if (bg.count > 0) {
                cnt += bg.count * getNumberFor(bg.name, sacks);
            }
        }
        return cnt;
    }

    @Test
    public void data() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day7/data");
        assertEquals(197, foundColorBags(strings));
    }

}
