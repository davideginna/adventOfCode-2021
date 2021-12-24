package com.company.day13;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day13 {

    // idea è spalmare la matrice in tanti array contigui con la regola
    // maxX * y + x
    // scelto l'array con maxX * y
    // setto valore con la x

    public static void main(String[] args) {
        InputStream is = Day13.class.getClassLoader().getResourceAsStream("inputs/d13input.txt");
        assert is != null;

        try (Scanner scanner = new Scanner(is)) {

            List<String> foldingInstructions = new ArrayList<>();

            int maxX = 0;
            int maxY = 0;

            List<String> initialArr = new ArrayList<>();

            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                if (line.isBlank()) continue;
                if (line.startsWith("fold along")) {
                    foldingInstructions.add(line.substring("fold along".length()).trim());
                    continue;
                }
                String[] lineArr = line.trim().split(",");
                int x = Integer.parseInt(lineArr[0]);
                int y = Integer.parseInt(lineArr[1]);

                maxX = Math.max(x, maxX);
                maxY = Math.max(y, maxY);

                initialArr.add(line);
            }

            maxX++;
            maxY++;

            boolean[] initialData = new boolean[maxX * maxY];
            Arrays.fill(initialData, false);

            for (String line2 : initialArr) {
                if (line2.isBlank()) continue;
                if (line2.startsWith("fold along")) continue;
                String[] lineArr = line2.trim().split(",");
                int x = Integer.parseInt(lineArr[0]);
                int y = Integer.parseInt(lineArr[1]);

                initialData[maxX * y + x] = true;
            }

            //printMap(initialData, maxX);

            for (String fold : foldingInstructions) {
                if (fold.startsWith("y=")) {
                    int foldYPos = Integer.parseInt(fold.substring(2));
                    initialData = foldY(initialData, maxX, maxY, foldYPos);
                    maxY = foldYPos;
                } else {
                    int foldXPos = Integer.parseInt(fold.substring(2));
                    initialData = foldX(initialData, maxX, maxY, foldXPos);
                    maxX = foldXPos;
                }
            }


            printMap(initialData, maxX);

            int count = 0;
            for (boolean dot : initialData) {
                count += dot ? 1 : 0;
            }

            System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean[] foldX(boolean[] initialData, int maxX, int maxY, int foldXPos) {
        boolean[] newData = new boolean[maxY * foldXPos];

        for (int y = 0; y < maxY; y++) {
            if (foldXPos >= 0) System.arraycopy(initialData, y * maxX, newData, y * foldXPos, foldXPos);
        }

        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX - foldXPos - 1; x++) {
                newData[y * foldXPos + foldXPos - 1 - x] =
                        initialData[y * maxX + foldXPos + 1 + x] ||
                                newData[y * foldXPos + foldXPos - 1 - x];
            }
        }
        return newData;
    }

    private static boolean[] foldY(boolean[] initialData, int maxX, int maxY, int foldYPos) {
        boolean[] newData = Arrays.copyOf(initialData, maxX * foldYPos);

        for (int y = 0; y < maxY - foldYPos - 1; y++) {
            for (int x = 0; x < maxX; x++) {
                newData[maxX * (foldYPos - 1 - y) + x] =
                        initialData[maxX * (foldYPos + 1 + y) + x] ||
                                newData[maxX * (foldYPos - 1 - y) + x];
            }
        }
        return newData;
    }

    public static void printMap(boolean[] map, int maxX) {
        int i = 0;
        for (boolean v : map) {
            if (i % maxX == 0) {
                System.out.println();
            }
            System.out.print(v ? "#" : ".");
            i++;
        }
        System.out.println();
    }
}