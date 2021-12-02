package com.company.day2;

import com.company.utility.Util;

import java.util.List;

public class Day2 {

    public static final String FORWARD = "forward";
    public static final String DOWN = "down";
    public static final String UP = "up";

    public static void main(String[] args) {
        p1(Util.readFileString("src/com/company/day2/d2input.txt"));
    }

    private static void p1(List<String> l) {
        var x = 0;
        var y = 0;
        for (String s : l) {
            var coordinate = s.split(" ");
            switch (coordinate[0]) {
                case FORWARD:
                    x = x + Integer.parseInt(coordinate[1]);
                    break;
                case DOWN:
                    y = y + Integer.parseInt(coordinate[1]);
                    break;
                case UP:
                    y = y - Integer.parseInt(coordinate[1]);
                    break;
            }
        }
        System.out.println("x: " + x + " y: " + y + " x*y: " + x * y);
    }
}
