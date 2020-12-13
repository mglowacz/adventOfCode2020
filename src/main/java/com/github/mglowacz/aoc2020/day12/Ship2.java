package com.github.mglowacz.aoc2020.day12;

public class Ship2 {

    int x = 0;
    int y = 0;

    int wx = 10;
    int wy = -1;


    public int getManhattanDistance() {
        return Math.abs(x) + Math.abs(y);
    }

    public void move(String commandLine) {
        String command = commandLine.substring(0, 1);
        int value = Integer.parseInt(commandLine.substring(1));
        int nwx = 0;
        int nwy = 0;

        switch (command) {
            case "N" : wy-=value;break;
            case "S" : wy+=value;break;
            case "E" : wx+=value;break;
            case "W" : wx-=value;break;
            case "L" :
                switch (value) {
                    case 270 : nwx = -wy; nwy = wx; break;
                    case 180 :nwx = - wx; nwy = -wy; break;
                    case 90 :nwx = wy ; nwy = -wx; break;
                }
                wx = nwx;
                wy = nwy;
                break;
            case "R" :
                switch (value) {
                    case 90 : nwx = -wy; nwy = wx; break;
                    case 180 :nwx = - wx; nwy = -wy; break;
                    case 270 :nwx = wy ; nwy = -wx; break;
                }
                wx = nwx;
                wy = nwy;
                break;
            case "F" : {
                x += value * wx;
                y += value * wy;
            }
            break;
        }
    }
}
