package com.company.day7;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Day7 {
    public static void main(String[] args) {
        InputStream is = Day7.class.getClassLoader().getResourceAsStream("inputs/d7input.txt");
        assert is != null;
        try (Scanner scanner = new Scanner(is)) {
            int[] postionCrabs = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::valueOf).toArray();
            int minPos = Arrays.stream(postionCrabs).min().getAsInt();
            int maxPos = Arrays.stream(postionCrabs).max().getAsInt();
            p1(postionCrabs, minPos, maxPos);
            p2(postionCrabs, minPos, maxPos);
        }
    }

    private static void p1(int[] postionCrabs, int minPos, int maxPos) {
        System.out.println("Part 1: " + IntStream.range(minPos, maxPos + 1)
                .map(pos -> {
                    int fuel = 0;
                    for (int crab : postionCrabs) {
                        fuel += Math.abs(pos - crab);
                    }
                    return fuel;
                }).min().getAsInt());
    }

    private static void p2(int[] postionCrabs, int minPos, int maxPos) {
        System.out.println("Part 2: " + IntStream.range(minPos, maxPos + 1)
                .map(pos -> {
                    int fuel = 0;
                    for (int crab : postionCrabs) {
                        int moves = Math.abs(pos - crab);
                        fuel += (moves * (moves + 1)) / 2;
                    }
                    return fuel;
                }).min().getAsInt());
    }
}