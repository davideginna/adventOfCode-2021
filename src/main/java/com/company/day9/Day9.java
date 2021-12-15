package com.company.day9;

import org.javatuples.Pair;

import java.io.InputStream;
import java.util.*;
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
        List<Pair<Integer, Integer>> positions = new ArrayList<>();
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
                positions.add(new Pair<>(row, col));
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


        System.out.println("positions" + positions);
        // part 2
        List<Integer> sizeBasins = new ArrayList<>();
        positions.forEach(p -> {
            LinkedList<Pair<Integer, Integer>> basinScanner = new LinkedList<>();
            Set<Pair<Integer, Integer>> basin = new HashSet<>();
            basinScanner.add(p);

            while (!basinScanner.isEmpty()) {
                Pair<Integer, Integer> posToScan = basinScanner.pop();
                basin.add(posToScan);

                // select vicino
                int i = posToScan.getValue0();
                int j = posToScan.getValue1();
                Set<Pair<Integer, Integer>> vicino = new HashSet<>();
                if (i > 0) vicino.add(new Pair<>(i - 1, j)); // nord
                if (j > 0) vicino.add(new Pair<>(i, j - 1)); // est
                if (j < sizeCol - 1) vicino.add(new Pair<>(i, j + 1)); // ovest
                if (i < mat.size() - 1) vicino.add(new Pair<>(i + 1, j)); //sud
                vicino.removeIf(n -> mat.get(n.getValue0()).get(n.getValue1()) == 9 || basinScanner.contains(n) || basin.contains(n));

                basinScanner.addAll(vicino);
            }
            sizeBasins.add(basin.size());
        });
        sizeBasins.sort((i1, i2) -> Integer.compare(i2, i1));
        System.out.printf("Part 2: %s%n", sizeBasins.get(0) * sizeBasins.get(1) * sizeBasins.get(2));
    }
}