package com.company.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        var list = readFile();
        var increased = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i != 0 && list.get(i - 1) - list.get(i) < 0) {
                increased++;
            }
        }
        System.out.println(increased);
    }


    private static List<Integer> readFile() {
        List<Integer> integers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/day1/d1input.txt"))) {
            String line = br.readLine();
            while (line != null) {
                integers.add(Integer.parseInt(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return integers;
    }
}
