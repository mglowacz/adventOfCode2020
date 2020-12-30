package com.github.mglowacz.aoc2020.day20;

import com.github.mglowacz.aoc2020.FileInputSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day20 {
    @Test
    public void test() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day20/test");
        List<Tile> tiles = Tile.createListOfTiles(createMap(strings));

        Map<String, Set<Tile>> map2 = new HashMap<>();
        tiles.forEach(tile1 -> {
            tile1.borders.forEach(s -> {
                if (!map2.containsKey(s) && !map2.containsKey(reverse(s))) {
                    map2.put(s, new HashSet<>());
                }
                Set<Tile> orDefault = map2.getOrDefault(s, map2.get(reverse(s)));
                orDefault.add(tile1);

            });
        });

        List<Map.Entry<String, Set<Tile>>> collect = map2.entrySet().stream().filter(stringIntegerEntry -> stringIntegerEntry.getValue().size() == 1).collect(Collectors.toList());
        Stream<Map.Entry<Integer, List<Tile>>> entryStream = collect.stream().map(stringSetEntry -> stringSetEntry.getValue().iterator().next()).collect(Collectors.toList()).stream().collect(Collectors.groupingBy(tile1 -> tile1.number, Collectors.toList())).entrySet().stream().filter(integerListEntry -> integerListEntry.getValue().size() == 2);
        assertEquals(20899048083289L, entryStream.flatMap(integerListEntry -> integerListEntry.getValue().stream()).map(tile1 -> tile1.number.longValue()).collect(Collectors.toSet()).stream().reduce(Math::multiplyExact).orElseThrow());
    }


    @Test
    public void data() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day20/data");
        List<Tile> tiles = Tile.createListOfTiles(createMap(strings));

        Map<String, Set<Tile>> map2 = new HashMap<>();
        tiles.forEach(tile1 -> {
            tile1.borders.forEach(s -> {
                if (!map2.containsKey(s) && !map2.containsKey(reverse(s))) {
                    map2.put(s, new HashSet<>());
                }
                Set<Tile> orDefault = map2.getOrDefault(s, map2.get(reverse(s)));
                orDefault.add(tile1);

            });
        });

        List<Map.Entry<String, Set<Tile>>> collect = map2.entrySet().stream().filter(stringIntegerEntry -> stringIntegerEntry.getValue().size() == 1).collect(Collectors.toList());
        Stream<Map.Entry<Integer, List<Tile>>> entryStream = collect.stream().map(stringSetEntry -> stringSetEntry.getValue().iterator().next()).collect(Collectors.toList()).stream().collect(Collectors.groupingBy(tile1 -> tile1.number, Collectors.toList())).entrySet().stream().filter(integerListEntry -> integerListEntry.getValue().size() == 2);
        assertEquals(29125888761511L, entryStream.flatMap(integerListEntry -> integerListEntry.getValue().stream()).map(tile1 -> tile1.number.longValue()).collect(Collectors.toSet()).stream().reduce(Math::multiplyExact).orElseThrow());
    }

    private Map<Integer, char[][]> createMap(List<String> strings) {
        Map<Integer, char[][]> map = new HashMap<>();
        int i = 0;
        char[][] tile = null;
        String line;
        int t = 0;
        int tLineIdx = 0;

        while (i < strings.size() - 1) {
            while ((i < strings.size()) && !(line = strings.get(i)).isEmpty()) {
                if (line.startsWith("Tile")) {
                    t = Integer.parseInt(line.replace("Tile ", "").replace(":", ""));
                    tLineIdx = 0;
                    tile = null;
                } else {
                    if (tile == null) tile = new char[line.length()][line.length()];
                    tile[tLineIdx++] = line.toCharArray();
                }
                i++;
            }
            i++;
            map.put(t, tile);
        }

        return map;
    }

    private String reverse(String key) {
        return new StringBuilder(key).reverse().toString();
    }




}
