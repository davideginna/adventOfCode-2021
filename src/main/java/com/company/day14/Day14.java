package com.company.day14;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Day14 {

    public static void main(String[] args) throws IOException {
        var iterazioni = 40;
        InputStream is = Day14.class.getClassLoader().getResourceAsStream("inputs/d14input.txt");
        assert is != null;
        String[] baseSplit;
        Map<String, String> template = new HashMap<>();
        List<String> baseToIterate = new ArrayList<>();

        try (Scanner scanner = new Scanner(is)) {
            baseSplit = scanner.nextLine().split("");
            for (int i = 0; i < baseSplit.length - 1; i++) {
                baseToIterate.add(baseSplit[i] + baseSplit[i + 1]);
            }
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                var split = scanner.nextLine().split("->");
                template.put(split[0].trim(), split[0].split("")[0].trim() + split[1].trim() + split[0].split("")[1]);
            }
        }
        String tmp = iterate(iterazioni, template, baseToIterate);
        var mapOcurrence = countOccurrence(tmp);
        var max = Collections.max(mapOcurrence.entrySet(), Map.Entry.comparingByValue()).getValue();
        var min = Collections.min(mapOcurrence.entrySet(), Map.Entry.comparingByValue()).getValue();
        System.out.println(max - min);
    }

    private static Map<String, Integer> countOccurrence(String tmp) {
        System.out.println("conto occorrenze");
        Map<String, Integer> mapOcurrence = new HashMap<>();
        for (String s : tmp.split("")) {
            if (mapOcurrence.get(s) != null) {
                mapOcurrence.put(s, mapOcurrence.get(s) + 1);
                continue;
            }
            mapOcurrence.put(s, 1);
        }
        return mapOcurrence;
    }

    private static String iterate(int iterazioni, Map<String, String> template, List<String> baseToIterate) {
        //scorro la base e rimpiazzo
        String tmp = null;
        for (int i = 0; i < iterazioni; i++) {
            System.out.println("iterazione: " + i);
            tmp = "";
            for (int k = 0; k < baseToIterate.size(); k++) {
                tmp += (template.get(baseToIterate.get(k)));
                if (k < baseToIterate.size() - 1) {
                    tmp = tmp.substring(0, tmp.length() - 1);
                }
            }
            //aggiungo ultimo carattere
            baseToIterate = new ArrayList<>();
            var tmpSplit = tmp.split("");
            for (int j = 0; j < tmpSplit.length - 1; j++) {
                baseToIterate.add(tmpSplit[j] + tmpSplit[j + 1]);
            }
        }
        return tmp;
    }


}
