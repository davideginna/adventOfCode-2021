package com.company.day3;

import com.company.utility.Util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

public class Day3 {

    public static void main(String[] args) {
        p1(Util.readFileString("ssrc/main/resources/inputs/d3input.txt"));
        p2(Util.readFileString("src/main/resources/inputs/d3input.txt"));
    }

    private static void p1(List<String> lines) {
        int length = lines.get(0).length();
        int[] result = new int[length];
        for (String line : lines) {
            char[] binaryChars = line.toCharArray();
            for (int i = 0; i < length; i++) {
                result[i] += binaryChars[i] == '0' ? -1 : 1;
            }
        }
        int gammaRate = 0;
        for (int i = 0; i < length; i++) {
            gammaRate += result[i] > 0 ? (Math.pow(2, length - i - 1)) : 0;
        }
        //svisionata pura con i bit
        System.out.println("Part 1: " + (int) (gammaRate * (Math.pow(2, length) - 1 - gammaRate)));
    }

    private static void p2(List<String> lines) {
        int ratingO = calculateValue(lines, (one, zero) -> one >= zero);
        int ratingCO2 = calculateValue(lines, (one, zero) -> one < zero);
        System.out.println("Part 2: " + ratingO * ratingCO2);
    }

    private static Integer calculateValue(List<String> lines, BiPredicate<Integer, Integer> biPredicate) {
        int length = lines.get(0).length();
        LinkedList<String> linkedList = new LinkedList<>(lines);
        List<String> listZero = new ArrayList<>();
        List<String> listUno = new ArrayList<>();

        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            while (!linkedList.isEmpty()) {
                String line = linkedList.pop();
                char[] binaryChars = line.toCharArray();
                if (binaryChars[i] == '0') {
                    listZero.add(line);
                } else {
                    listUno.add(line);
                }
            }
            //test della condizione
            if (biPredicate.test(listUno.size(), listZero.size())) {
                result[i] = '1';
                linkedList.addAll(listUno);
            } else {
                result[i] = '0';
                linkedList.addAll(listZero);
            }
            if (linkedList.size() == 1) {
                result = linkedList.get(0).toCharArray();
                break;
            }
            listUno = new ArrayList<>();
            listZero = new ArrayList<>();
        }
        return Integer.valueOf(String.valueOf(result), 2);
    }


}
