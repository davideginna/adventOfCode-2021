package com.company.day15;

import java.io.InputStream;
import java.util.Scanner;

public class Day15 {
    public static int[][] map = new int[100][100];

    public static void main(String[] args) {
        InputStream is = Day15.class.getClassLoader().getResourceAsStream("inputs/d15input.txt");
        assert is != null;
        try (Scanner scanner = new Scanner(is)) {
            int j = 0;
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine().split("");
                for (int i = 0; i < line.length; i++) {
                    map[j][i] = Integer.parseInt(line[i]);
                }
                j++;
            }
        }
        System.out.println("Part1: " + calcolaRischio(map));
        System.out.println("Part2: " + calcolaRischio(scaleMap(map)));

    }

    public static int calcolaRischio(int[][] map) {
        int[][] mapPesi = new int[map.length][map[0].length];
        for (int r = 0; r < mapPesi.length; r++) {
            for (int c = 0; c < mapPesi[0].length; c++) {
                mapPesi[r][c] = 10000000;
            }
        }
        mapPesi[mapPesi.length - 1][mapPesi[0].length - 1] = 0;

        boolean changeMade = true;
        while (changeMade) {
            changeMade = false;
            for (int r = mapPesi.length - 1; r >= 0; r--) {
                for (int c = mapPesi[0].length - 1; c >= 0; c--) {
                    int min = Integer.MAX_VALUE;
                    if (r - 1 >= 0)
                        min = Math.min(min, map[r - 1][c] + mapPesi[r - 1][c]);
                    if (r + 1 < mapPesi.length)
                        min = Math.min(min, map[r + 1][c] + mapPesi[r + 1][c]);
                    if (c - 1 >= 0)
                        min = Math.min(min, map[r][c - 1] + mapPesi[r][c - 1]);
                    if (c + 1 < mapPesi[0].length)
                        min = Math.min(min, map[r][c + 1] + mapPesi[r][c + 1]);

                    int oldRisk = mapPesi[r][c];
                    mapPesi[r][c] = Math.min(mapPesi[r][c], min);
                    if (mapPesi[r][c] != oldRisk)
                        changeMade = true;
                }
            }
        }
        return (mapPesi[0][0]);
    }

    //ripeto per 5 volte il quadrato
    public static int[][] scaleMap(int[][] map) {
        int[][] newMap = new int[5 * map.length][5 * map[0].length];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                int val = map[r][c];
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        int newNum = val + i + j;
                        if (newNum > 9)
                            newNum -= 9;
                        newMap[r + i * map.length][c + j * map[0].length] = newNum;
                    }
                }
            }
        }
        return newMap;
    }
}
