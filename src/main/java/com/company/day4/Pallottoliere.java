package com.company.day4;

import java.util.List;

public class Pallottoliere {

    private List<String> pallottoliere;


    public Pallottoliere() {
    }

    public Pallottoliere(List<String> pallottoliere) {
        this.pallottoliere = pallottoliere;
    }

    public List<String> getPallottoliere() {
        return pallottoliere;
    }

    public void setPallottoliere(List<String> pallottoliere) {
        this.pallottoliere = pallottoliere;
    }

    public String extractNumber(List<String> pallottoliere) {
        return pallottoliere.remove(0);
    }

}
