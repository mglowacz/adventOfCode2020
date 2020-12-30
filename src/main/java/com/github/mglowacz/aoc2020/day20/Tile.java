package com.github.mglowacz.aoc2020.day20;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class Tile {
    Integer number;
    List<String> borders;

    private Tile() {

    }

    public static Tile create(Integer number, char[][] map) {
        Tile tile = new Tile();
        tile.number = number;
        tile.borders = getTileBorders(map);
        return tile;
    }

    private static List<String> getTileBorders(char[][] tile) {
        String top = new String(tile[0]);
        String bottom = new String(tile[tile.length - 1]);
        char[] left = new char[tile.length];
        char[] right = new char[tile.length];
        for (int i = 0; i < tile.length; i++) {
            left[i] = tile[i][0];
            right[i] = tile[i][tile.length - 1];
        }

        return asList(top, new String(left), new String(right), bottom);
    }

    public static List<Tile> createListOfTiles(Map<Integer, char[][]> map) {
        List<Tile> tiles = new LinkedList<>();
        for (Map.Entry<Integer, char[][]> entry : map.entrySet()) {
            Tile tile = Tile.create(entry.getKey(), entry.getValue());
            tiles.add(tile);
        }
        return tiles;
    }

}
