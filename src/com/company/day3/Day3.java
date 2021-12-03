package com.company.day3;

import com.company.utility.Util;

import java.util.List;

public class Day3 {


    public static void main(String[] args) {
        p1(Util.readFileString("src/com/company/resource/d3input.txt"));
        //p2(Util.readFileInt("src/com/company/resource/d3input.txt"));
    }

    private static void p1(List<String> l) {
        int[] tot = new int[l.get(0).length()];

        for (String s : l) {
            for (int j = 0; j < s.split("").length; j++) {
                tot[j] += Integer.parseInt(s.split("")[j]);
            }
        }
        var gamma = 0;
        var epsilon = 0;
        StringBuilder binGamma = new StringBuilder();
        StringBuilder binEpsilon = new StringBuilder();
        for (int j : tot) {
            if (j > l.size() / 2) {
                binGamma.append("1");
            } else {
                binGamma.append("0");
            }
        }
        invertString(binGamma, binEpsilon);
        gamma = Integer.parseInt(binGamma.toString(), 2);
        epsilon = Integer.parseInt(binEpsilon.toString(), 2);
        System.out.println(Integer.parseInt(binGamma.toString(), 2));
        System.out.println(Integer.parseInt(binEpsilon.toString(), 2));
        System.out.println(gamma * epsilon);
    }

    private static void p2() {
        /*
        00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010
        */

        //tenere solo i numeri che hanno in posizione x un uno
    }


    private static void invertString(StringBuilder binGamma, StringBuilder binEpsilon) {
        for (int i = 0; i < binGamma.toString().length(); i++) {
            if (binGamma.toString().split("")[i].equals("0")) {
                binEpsilon.append("1");
            } else {
                binEpsilon.append("0");
            }
        }
    }
}
