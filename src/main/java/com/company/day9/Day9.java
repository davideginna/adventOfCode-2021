package com.company.day9;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day9 {

    public static void main(String[] args) {
        InputStream is = Day9.class.getClassLoader().getResourceAsStream("inputs/d9input.txt");
        assert is != null;
        List<List<Integer>> mat = new ArrayList<>();
        try (Scanner scanner = new Scanner(is)) {
            while (scanner.hasNextLine()) {
                mat.add(Arrays.stream(scanner.nextLine().split("")).map(Integer::valueOf).collect(Collectors.toList()));
            }
            p1(mat);
        }
    }

    private static void p1(List<List<Integer>> mat) {
        //parto da 0,0

        int row = 0;
        int col = 0;
        int sizeRow = mat.size();
        int sizeCol = mat.get(0).size();
        List<Integer> result = new ArrayList<>();
        while (row < sizeRow) {//controllare gli indici
            boolean trovatoMinore = true;
            int candidate = mat.get(row).get(col);
            //sud
            if (row + 1 < sizeRow && mat.get(row + 1).get(col) <= candidate) {
                trovatoMinore = false;
            }
            //nord
            if (row - 1 >= 0 && mat.get(row - 1).get(col) <= candidate) {
                trovatoMinore = false;
            }
            //est
            if (col + 1 < sizeCol && mat.get(row).get(col + 1) <= candidate) {
                trovatoMinore = false;
            }
            //ovest
            if (col - 1 >= 0 && mat.get(row).get(col - 1) <= candidate) {
                trovatoMinore = false;
            }

            //se non c'e un minore mi candido
            if (trovatoMinore) {
                result.add(candidate);
            }
            if (col < sizeCol - 1) {
                col++;
            } else {
                col = 0;
                row++;
            }

        }
        var tot = result.size() + result.stream().reduce(0, Integer::sum);
        System.out.println("Part 1:" + tot);
    }
}