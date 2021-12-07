package com.company.Day05;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day05 {
    public static void main(String[] args) {
        InputStream is = Day05.class.getClassLoader().getResourceAsStream("inputs/d5input.txt");
        List<Integer[]> vents = new ArrayList<>();
        int dim = -1;
        assert is != null;
        try (Scanner scanner = new Scanner(is)) {
            while (scanner.hasNextLine()) {
                var points = scanner.nextLine().split("->");
                var x = Arrays.stream(points[0].trim().split(",")).map(Integer::valueOf).toArray(Integer[]::new);
                var y = Arrays.stream(points[1].trim().split(",")).map(Integer::valueOf).toArray(Integer[]::new);
                var vent = Stream.concat(Arrays.stream(x), Arrays.stream(y)).toArray(Integer[]::new);
                vents.add(vent);
                //calcola numero massimo
                dim = calcoloMax(dim, vent);

            }
            System.out.println("Part 1: " + p1(dim + 1, vents));
            System.out.println("Part 2: " + p2(dim + 1, vents));
        }
    }

    public static int p1(int dim, List<Integer[]> vents) {
        int[][] mat = new int[dim][dim];
        for (Integer[] vent : vents) {
            int x1 = vent[0];
            int x2 = vent[1];
            int y1 = x1 - vent[2];
            int y2 = x2 - vent[3];
            if (y1 == 0 || y2 == 0) {
                while (y1 != 0 || y2 != 0) {
                    mat[x2][x1] += 1;
                    if (y1 < 0) {
                        x1++;
                        y1++;
                    } else if (y1 > 0) {
                        x1--;
                        y1--;
                    }
                    if (y2 < 0) {
                        x2++;
                        y2++;
                    } else if (y2 > 0) {
                        x2--;
                        y2--;
                    }
                }
                //ultimo punto
                mat[x2][x1] += 1;
            }
        }
        return getCount(dim, mat);
    }


    public static int p2(int dim, List<Integer[]> vents) {
        int[][] mat = new int[dim][dim];
        for (Integer[] vent : vents) {
            int x1 = vent[0];
            int x2 = vent[1];
            int y1 = x1 - vent[2];
            int y2 = x2 - vent[3];
            while (y1 != 0 || y2 != 0) {
                mat[x2][x1] += 1;
                if (y1 < 0) {
                    x1++;
                    y1++;
                } else if (y1 > 0) {
                    x1--;
                    y1--;
                }
                if (y2 < 0) {
                    x2++;
                    y2++;
                } else if (y2 > 0) {
                    x2--;
                    y2--;
                }
            }
            //ultimo punto
            mat[x2][x1] += 1;
        }
        return getCount(dim, mat);
    }


    private static int getCount(int dim, int[][] mat) {
        int count = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (mat[i][j] > 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int calcoloMax(int max, Integer[] vent) {
        int tmpMax = -1;
        for (Integer integer : vent) {
            if (tmpMax < integer) {
                tmpMax = integer;
            }
        }
        return Math.max(max, tmpMax);
    }
}
