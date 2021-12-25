package com.company.day14;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day14_2 {

    public static void main(String[] args) throws IOException {
        InputStream is = Day14_2.class.getClassLoader().getResourceAsStream("inputs/d14input.txt");
        assert is != null;

        StringBuilder base = new StringBuilder();
        Map<String, String> rules = new HashMap<>();

        try (Scanner scanner = new Scanner(is)) {
            base.append(scanner.nextLine());
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                var rule = scanner.nextLine().split(" -> ");
                rules.put(rule[0], rule[1]);
            }
        }

        var result = calcIterate(40, base, rules);
        countOccurrence(result.toString());
    }

    private static StringBuilder calcIterate(int iterazioni, StringBuilder base, Map<String, String> rules) {
        for (int i = 0; i < iterazioni; i++) {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < base.length(); j++) {
                temp.append(base.charAt(j));
                if (j + 1 < base.length()) {
                    temp.append(rules.get(base.charAt(j) + "" + base.charAt(j + 1)));
                }
            }
            base = temp;
        }
        return base;
    }

    private static void countOccurrence(String tmp) {
        System.out.println("conto occorrenze");
        Map<String, Integer> mapOcurrence = new HashMap<>();
        for (String s : tmp.split("")) {
            if (mapOcurrence.get(s) != null) {
                mapOcurrence.put(s, mapOcurrence.get(s) + 1);
                continue;
            }
            mapOcurrence.put(s, 1);
        }
        var max = Collections.max(mapOcurrence.entrySet(), Map.Entry.comparingByValue()).getValue();
        var min = Collections.min(mapOcurrence.entrySet(), Map.Entry.comparingByValue()).getValue();
        System.out.println(max - min);
    }


}
