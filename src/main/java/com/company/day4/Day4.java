package com.company.day4;

import com.company.utility.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Day4 {
    public static void main(String[] args) {
        p1(Util.readFileString("src/main/resources/inputs/d4input.txt"));
    }

    public static boolean NUOVA_SCHEDA = false;
    public static boolean WIN = false;
    public static int ID_SCHEDA_VINCENTE = -1;
    public static int ULTIMO_NUMERO_ESTRATTO = -1;

    private static void p1(List<String> l) {

        Pallottoliere pallottoliere = new Pallottoliere(Arrays.asList(l.get(0).split(",")));
        //System.out.println(pallottoliere);

        List<Scheda> schedaList = inizializzaSchede(l);


        //adesso si gioca

        int i = 0;
        while (!WIN && i < pallottoliere.size()) {
            var numeroEstratto = pallottoliere.get(i);
            //System.out.println("estratto il numero " + numeroEstratto);
            ULTIMO_NUMERO_ESTRATTO = Integer.parseInt(numeroEstratto);
            WIN = segnaNumero(numeroEstratto, schedaList);
            i++;
        }

        System.out.println("ID_SCHEDA_VINCENTE " + ID_SCHEDA_VINCENTE);
        System.out.println("ULTIMO_NUMERO_ESTRATTO " + ULTIMO_NUMERO_ESTRATTO);
        System.out.println(schedaList.get(ID_SCHEDA_VINCENTE));
        System.out.println("SOMMA " + sommaNumeriNonEstratti(schedaList.get(ID_SCHEDA_VINCENTE)));

        System.out.println(sommaNumeriNonEstratti(schedaList.get(ID_SCHEDA_VINCENTE)) * ULTIMO_NUMERO_ESTRATTO);

    }

    private static int sommaNumeriNonEstratti(Scheda scheda) {
        int totale = 0;
        for (int i = 0; i < scheda.getRiga0().size(); i++) {
            if (!scheda.getRiga0().get(i).isEstratto()) {
                totale += Integer.parseInt(scheda.getRiga0().get(i).getValore());
            }
        }
        for (int i = 0; i < scheda.getRiga1().size(); i++) {
            if (!scheda.getRiga1().get(i).isEstratto()) {
                totale += Integer.parseInt(scheda.getRiga1().get(i).getValore());
            }
        }
        for (int i = 0; i < scheda.getRiga2().size(); i++) {
            if (!scheda.getRiga2().get(i).isEstratto()) {
                totale += Integer.parseInt(scheda.getRiga2().get(i).getValore());
            }
        }
        for (int i = 0; i < scheda.getRiga3().size(); i++) {
            if (!scheda.getRiga3().get(i).isEstratto()) {
                totale += Integer.parseInt(scheda.getRiga3().get(i).getValore());
            }
        }
        for (int i = 0; i < scheda.getRiga4().size(); i++) {
            if (!scheda.getRiga4().get(i).isEstratto()) {
                totale += Integer.parseInt(scheda.getRiga4().get(i).getValore());
            }
        }
        return totale;
    }


    private static boolean segnaNumero(String numeroEstratto, List<Scheda> schedaList) {
        boolean victory = false;
        int i = 0;
        while (i < schedaList.size() && !victory) {
            victory = isVicotry(numeroEstratto, victory, schedaList.get(i).getRiga0(), schedaList.get(i), 0);
            if (!victory) {
                victory = isVicotry(numeroEstratto, victory, schedaList.get(i).getRiga1(), schedaList.get(i), 1);
            }
            if (!victory) {
                victory = isVicotry(numeroEstratto, victory, schedaList.get(i).getRiga2(), schedaList.get(i), 2);
            }
            if (!victory) {
                victory = isVicotry(numeroEstratto, victory, schedaList.get(i).getRiga3(), schedaList.get(i), 3);
            }
            if (!victory) {
                victory = isVicotry(numeroEstratto, victory, schedaList.get(i).getRiga4(), schedaList.get(i), 4);
            }
            if (victory) {
                ID_SCHEDA_VINCENTE = i;
            }
            i++;
        }
        return victory;
    }

    private static boolean isVicotry(String numeroEstratto, boolean vicotry, List<Casella> riga, Scheda scheda, int indice) {
        int i = 0;
        while (i < riga.size() && !vicotry) {
            if (riga.get(i).getValore().equals(numeroEstratto)) {
                riga.get(i).setEstratto(true);
                vicotry = checkVittoria(riga, scheda, indice);
            }
            i++;
        }
        return vicotry;
    }

    private static boolean checkVittoria(List<Casella> riga, Scheda scheda, int indice) {
        boolean result = true;
        result = checkVittoriRiga(riga, result);
        if (result) {
            return result;
        }
        result = checkVittoriColonna(scheda, indice);
        return result;
    }

    private static boolean checkVittoriColonna(Scheda scheda, int indice) {
        return scheda.getRiga0().get(indice).isEstratto() &&
                scheda.getRiga1().get(indice).isEstratto() &&
                scheda.getRiga2().get(indice).isEstratto() &&
                scheda.getRiga3().get(indice).isEstratto() &&
                scheda.getRiga4().get(indice).isEstratto();
    }

    private static boolean checkVittoriRiga(List<Casella> riga, boolean result) {
        for (Casella casella : riga) {
            result = result && casella.isEstratto();
        }
        return result;
    }

    private static List<Scheda> inizializzaSchede(List<String> l) {
        List<Scheda> schedaList = new ArrayList<>();

        Scheda scheda = new Scheda();

        for (int i = 2; i < l.size(); i++) {
            for (int j = 0; j < l.get(i).split(" ").length; j++) {
                if (!l.get(i).split(" ")[j].equals("")) {
                    Casella casella = new Casella();
                    casella.setEstratto(false);
                    casella.setValore(l.get(i).split(" ")[j]);
                    setCasellaInRiga(scheda, casella);
                }
            }

            if (l.get(i).length() > 0 && NUOVA_SCHEDA) {
                schedaList.add(scheda);
                scheda = new Scheda();
                NUOVA_SCHEDA = false;
            }
        }
        return schedaList;
    }

    private static void setCasellaInRiga(Scheda scheda, Casella casella) {
        if (scheda.getRiga0().size() < 5) {
            scheda.getRiga0().add(casella);
        } else if (scheda.getRiga1().size() < 5) {
            scheda.getRiga1().add(casella);
        } else if (scheda.getRiga2().size() < 5) {
            scheda.getRiga2().add(casella);
        } else if (scheda.getRiga3().size() < 5) {
            scheda.getRiga3().add(casella);
        } else if (scheda.getRiga4().size() < 5) {
            scheda.getRiga4().add(casella);
            if (scheda.getRiga4().size() == 5) {
                NUOVA_SCHEDA = true;
            }
        }
    }


}
