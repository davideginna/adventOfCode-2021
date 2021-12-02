package com.company.day1;

import com.company.utility.Util;

import java.util.List;


public class Day1 {
    public static void main(String[] args) {
        p1(Util.readFileInt("src/com/company/day1/d1input.txt"));
        p2(Util.readFileInt("src/com/company/day1/d1input.txt"));
    }

    private static void p1(List<Integer> l) {
        var c = 0;
        for (int i = 1; i < l.size(); i++) {
            if (l.get(i - 1) - l.get(i) < 0) {
                c++;
            }
        }
        System.out.println(c);
    }

    private static void p2(List<Integer> l) {
        var c = 0;
        for (int i = 3; i < l.size(); i++) {
            if ((l.get(i - 3) + l.get(i - 2) + l.get(i - 1)) -
                    (l.get(i - 2) + l.get(i - 1) + l.get(i)) < 0) {
                c++;
            }
        }
        System.out.println(c);
    }

}
