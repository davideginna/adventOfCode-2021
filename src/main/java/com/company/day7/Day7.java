package com.company.day7;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Float.NaN;

public class Day7 {
    public static void main(String[] args) {
        InputStream is = Day7.class.getClassLoader().getResourceAsStream("inputs/d7input.txt");
        assert is != null;
        try (Scanner scanner = new Scanner(is)) {
            List<Integer> crabsPosition = Arrays.stream(scanner.nextLine().split(",")).map(Integer::valueOf).collect(Collectors.toList());
            System.out.println(crabsPosition);

            //PART 1
            var median = ((double) crabsPosition.get(crabsPosition.size() / 2) + (double) crabsPosition.get(crabsPosition.size() / 2 - 1)) / 2;
            //System.out.println(median);
            System.out.println("Part1: " + crabsPosition.stream().mapToDouble(d -> Math.abs(d - median)).sum());

            //PART 2
            var average =Math.round(Arrays.stream(crabsPosition.stream()
                    .mapToInt(Integer::intValue)
                    .toArray()).average().orElse(NaN));
            System.out.println(average);

            System.out.println("Part2: " + BigDecimal.valueOf(crabsPosition.stream().mapToDouble(d -> {
                double sum = 0.0;
                for (int i = 0; i <= Math.abs(d - average); i++) {
                    sum += i;
                }
                return sum;
            }).sum()).toBigInteger());
        }

    }


}