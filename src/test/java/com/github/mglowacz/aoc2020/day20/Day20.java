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

    char[][] monster = new char[3][20];

    {
        monster[0] = "                  # ".toCharArray();
        monster[1] = "#    ##    ##    ###".toCharArray();
        monster[2] = " #  #  #  #  #  #   ".toCharArray();
    }

    @Test
    public void test2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day20/test");
        List<Tile> tiles = Tile.createListOfTiles(createMap(strings));


        List<Integer> corners = findCorners(tiles);

        char[][] tmp = createImage(tiles, corners.get(0));

        int c = 0;
        for (int j = 0; j < tmp.length; j++)
            for (int i = 0; i < tmp[0].length; i++)
                if (tmp[j][i] == '#') c++;

        int f = 0;
        int monsterCount = 0;
        while (monsterCount == 0) {
            for (int j = 0; j < tmp.length - monster.length; j++)
                for (int i = 0; i < tmp[0].length - monster[0].length; i++)
                    if (checkMonster(tmp, j, i, monster))
                        monsterCount++;
            if (f % 4 == 0) tmp = Tile.flipSideMap(tmp);
            else tmp = Tile.rotateLeftMap(tmp);
            f++;
        }

        assertEquals(273, c - monsterCount * 15);
    }

    @Test
    public void data2() throws IOException {
        List<String> strings = FileInputSource.getStrings("/day20/data");
        List<Tile> tiles = Tile.createListOfTiles(createMap(strings));


        List<Integer> corners = findCorners(tiles);

        char[][] tmp = createImage(tiles, corners.get(0));

        int c = 0;
        for (int j = 0; j < tmp.length; j++)
            for (int i = 0; i < tmp[0].length; i++)
                if (tmp[j][i] == '#') c++;

        int f = 0;
        int monsterCount = 0;
        while (monsterCount == 0) {
            for (int j = 0; j < tmp.length - monster.length; j++)
                for (int i = 0; i < tmp[0].length - monster[0].length; i++)
                    if (checkMonster(tmp, j, i, monster))
                        monsterCount++;
            if (f % 4 == 0) tmp = Tile.flipSideMap(tmp);
            else tmp = Tile.rotateLeftMap(tmp);
            f++;
        }

        assertEquals(2219, c - monsterCount * 15);
    }

    private List<Integer> findCorners(List<Tile> tiles) {
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

        //List<Integer> corners = entryStream.map(Map.Entry::getKey).collect(Collectors.toList());
        return entryStream.map(Map.Entry::getKey).collect(Collectors.toList());
    }

    private boolean checkMonster(char[][] image, int j, int i, char[][] monster) {
        for (int y = 0; y < monster.length; y++)
            for (int x = 0; x < monster[0].length; x++) {
                if (monster[y][x] != '#') {
                    continue;
                } else if (image[j + y][i + x] != '#') {
                    return false;
                }
            }
        return true;
    }

    private char[][] createImage(List<Tile> tiles, int corner) {
        List<Tile> all = new LinkedList<>(tiles);
        int n = (int) Math.sqrt(tiles.size());
        char[][] image = new char[8 * n][8 * n];
        int[][] tmp = new int[n][n];

        Tile tile = tiles.stream().filter(t -> t.number.equals(corner)).reduce((a, b) -> {
            throw new RuntimeException("");
        }).orElseThrow();
        tiles.remove(tile);

        while (hasPairBorder(tiles, tile.borders.get(0)) || hasPairBorder(tiles, tile.borders.get(1))) {
            tile.flip();
        }

        Tile firstInRow = tile;
        tmp[0][0] = tile.number;
        printTile(tile);
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    continue;
                }
                String right = tile.borders.get(2);
                tile = findTileOn(tiles, right);
                while (!right.equals(tile.borders.get(1))) tile.flip();
                tmp[j][i] = tile.number;
                printTile(tile);
            }

            if (j < n - 1) {
                tile = findTileOn(tiles, firstInRow.borders.get(3));
                while (!firstInRow.borders.get(3).equals(tile.borders.get(0))) tile.flip();
                firstInRow = tile;
                tmp[j + 1][0] = firstInRow.number;
            }

        }

        for (int j = 0; j < n; j++)
            for (int i = 0; i < n; i++) {
                for (int k = 1; k < 9; k++)
                    for (int m = 1; m < 9; m++) {
                        image[j * 8 + k - 1][i * 8 + m - 1] = getTile(all, tmp[j][i]).map[k][m];
                    }
            }

        return image;
    }

    private void printTile(Tile tile) {
        System.out.println(tile.number + " : ");
        for (int j = 0; j < tile.map.length; j++) {
            for (int i = 0; i < tile.map[0].length; i++) {
                System.out.print(tile.map[j][i]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private Tile getTile(List<Tile> tiles, int i) {
        return tiles.stream().filter(t -> t.number == i).reduce((a, b) -> {
            throw new RuntimeException();
        }).orElseThrow();
    }

    private Tile findTileOn(List<Tile> tiles, String s) {
        String r = new StringBuilder(s).reverse().toString();
        Tile tile1 = tiles.stream().filter(tile -> tile.borders.contains(s) || tile.borders.contains(r)).reduce((a, b) -> {
            throw new RuntimeException();
        }).orElseThrow();
        tiles.remove(tile1);
        return tile1;
    }

    private boolean hasPairBorder(List<Tile> tiles, String s) {
        return 0 != tiles.stream().flatMap(tile -> tile.borders.stream()).filter(s1 -> s1.equals(s) || s1.equals(new StringBuilder(s).reverse().toString())).count();
    }

}
