package com.github.mglowacz.aoc2020.day20;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class Tile {
    Integer number;
    List<String> borders;
    char[][] map;

    private Tile() {

    }

    public static Tile create(Integer number, char[][] map) {
        Tile tile = new Tile();
        tile.number = number;
        tile.borders = getTileBorders(map);
        tile.map = map;
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


    private int flipIndex = 0;

    public void flip() {
        if (flipIndex % 4 == 0) flipSide();
        else rotateLeft();
        flipIndex++;
    }

    private void rotateLeft() {
        this.borders = new LinkedList<>(asList(
                borders.get(2), //top - right
                new StringBuilder(borders.get(0)).reverse().toString(),//left - reversed top
                new StringBuilder(borders.get(3)).reverse().toString(), //right - reversed bottom
                borders.get(1) //bottom - left
        ));

        map = rotateLeftMap(map);
    }

    static char[][] rotateLeftMap(char[][] map) {
        char[][] map2 = new char[map.length][map[0].length];
        for(int j = 0 ; j < map2.length ; j++)
            for(int i = 0 ; i < map2[0].length ; i++) {
                map2[j][i] = map[i][map2.length - 1 - j];
            }
        return map2;
    }

    private void flipSide() {
        this.borders = new LinkedList<>(asList(
                new StringBuilder(borders.get(0)).reverse().toString(), //top - reversed top
                borders.get(2), //left - right
                borders.get(1), //right - left
                new StringBuilder(borders.get(3)).reverse().toString() //bottom - reversed bottom
        ));
        map = flipSideMap(map);
    }

    static char[][] flipSideMap(char[][] map) {
        char[][] map2 = new char[map.length][map[0].length];
        for(int j = 0 ; j < map2.length ; j++)
            for(int i = 0 ; i < map2[0].length ; i++) {
                map2[j][i] = map[j][map2.length - 1 - i];
            }
        return map2;
    }

}
