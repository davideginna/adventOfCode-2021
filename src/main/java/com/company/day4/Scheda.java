package com.company.day4;

public class Scheda {

    private int row;
    private int col;

    public Scheda() {
    }

    public String[][] generaScheda(int row, int col) {
        return new String[row][col];
    }
}
