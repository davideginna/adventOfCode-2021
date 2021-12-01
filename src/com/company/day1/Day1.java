package com.company.day1;

import java.util.List;

import static com.company.utility.Util.readFile;

public class Day1 {
    public static void main(String[] args) {
        var list = readFile("src/com/company/day1/d1input.txt");
        var increased = 0;
        increased = p1(list, 0);
        System.out.println(increased);
        increased = p2(list, 0);
        System.out.println(increased);
    }

    private static int p1(List<Integer> list, int increased) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) - list.get(i) < 0) {
                increased++;
            }
        }
        return increased;
    }

    private static int p2(List<Integer> list, int increased) {
        for (int i = 3; i < list.size(); i++) {
            if ((list.get(i - 3) + list.get(i - 2) + list.get(i - 1)) -
                    (list.get(i - 2) + list.get(i - 1) + list.get(i)) < 0) {
                increased++;
            }
        }
        return increased;
    }

}
