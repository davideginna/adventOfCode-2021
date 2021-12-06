package com.company.day4;

import java.util.List;

public class Pallottoliere {

    private List<String> pallottoliere;

    public Pallottoliere() {
    }

    public String get(int i) {
        return pallottoliere.get(i);
    }

    public int size() {
        return pallottoliere.size();
    }

    public Pallottoliere(List<String> pallottoliere) {
        this.pallottoliere = pallottoliere;
    }

    public void setPallottoliere(List<String> pallottoliere) {
        this.pallottoliere = pallottoliere;
    }

    @Override
    public String toString() {
        return pallottoliere.toString();
    }
}
