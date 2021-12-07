package com.company.day6;

import com.company.day05.Day05;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Day06 {
    public static void main(String[] args) {
        InputStream is = Day05.class.getClassLoader().getResourceAsStream("inputs/d6input.txt");
        assert is != null;
        try (Scanner scanner = new Scanner(is)) {
            var fish = Arrays.stream(scanner.nextLine().split(",")).map(Integer::valueOf).toArray(Integer[]::new);
            long[] lifeFish = getInts(fish);
            for (int i = 0; i < 80; i++) {
                shiftToLeft(lifeFish, i);
            }
            System.out.println("p1: " + Arrays.stream(lifeFish).sum());

            lifeFish = getInts(fish);
            for (int i = 0; i < 256; i++) {
                shiftToLeft(lifeFish, i);
            }
            //System.out.println(Arrays.toString(Arrays.stream(lifeFish).toArray()));
            System.out.println("p2: " + Arrays.stream(lifeFish).sum());
        }

    }

    private static long[] getInts(Integer[] fish) {
        long[] lifeFish = new long[9];
        for (Integer f : fish) {
            lifeFish[f] += 1;
        }
        return lifeFish;
    }

    public static void shiftToLeft(long[] lifeFish, int iterazione) {
        long pesceZero = lifeFish[0];
        System.arraycopy(lifeFish, 1, lifeFish, 0, lifeFish.length - 1);
        lifeFish[6] = lifeFish[6] + pesceZero;
        lifeFish[8] = pesceZero;
    }
}