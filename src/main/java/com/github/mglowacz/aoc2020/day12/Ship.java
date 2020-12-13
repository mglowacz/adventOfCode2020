package com.github.mglowacz.aoc2020.day12;

public class Ship {

    int direction = 90;
    int x = 0;
    int y = 0;

    public int getManhattanDistance() {
        return Math.abs(x) + Math.abs(y);
    }

    public void move(String commandLine) {
        String command = commandLine.substring(0, 1);
        int value = Integer.parseInt(commandLine.substring(1));
        switch (command) {
            case "N" : y-=value;break;
            case "S" : y+=value;break;
            case "E" : x+=value;break;
            case "W" : x-=value;break;
            case "L" : direction-=value; direction = (direction + 360) % 360;break;
            case "R" : direction+=value; direction = (direction + 360) % 360;break;
            case "F" : {
                switch (direction) {
                    case 0 : y -= value; break;
                    case 90 : x += value; break;
                    case 180 : y += value; break;
                    case 270 : x -= value; break;

                }
            }
            break;
        }
    }
}
