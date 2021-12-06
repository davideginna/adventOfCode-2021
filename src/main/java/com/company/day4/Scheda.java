package com.company.day4;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Scheda {

    private List<Casella> riga0;
    private List<Casella> riga1;
    private List<Casella> riga2;
    private List<Casella> riga3;
    private List<Casella> riga4;

    public Scheda() {
        this.riga0 = new ArrayList<>();
        this.riga1 = new ArrayList<>();
        this.riga2 = new ArrayList<>();
        this.riga3 = new ArrayList<>();
        this.riga4 = new ArrayList<>();
    }



    @Override
    public String toString() {
        return "{" +
                "\"riga0\":" + riga0 +
                ", \"riga1\":" + riga1 +
                ", \"riga2\":" + riga2 +
                ", \"riga3\":" + riga3 +
                ", \"riga4\":" + riga4 +
                "}";
    }
}
