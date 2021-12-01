package com.company.day1;

import static com.company.utility.Util.readFile;

public class Day1 {
    public static void main(String[] args) {
        var list = readFile("src/com/company/day1/d1input.txt");
        var increased = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) - list.get(i) < 0) {
                increased++;
            }
        }
        System.out.println(increased);
    }
}
