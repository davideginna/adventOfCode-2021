package com.company.day4;

import lombok.Data;

@Data
public class Casella {
    private String valore;
    private boolean estratto;

    @Override
    public String toString() {
        return "{" +
                "\"valore\":\"" + valore +
                "\", \"estratto\":" + estratto +
                "}";
    }
}
