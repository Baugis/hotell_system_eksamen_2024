package org.eksamen;

import org.eksamen.Entity.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Liste {
    // ArrayList for hver tabell
    private ArrayList<Rom> romListe = new ArrayList<>();
    private ArrayList<Utsjekkinger> utsjekkingListe = new ArrayList<>();
    private ArrayList<Reservasjoner> reservasjonerListe = new ArrayList<>();
    private ArrayList<Innsjekkinger> innsjekkingerListe = new ArrayList<>();
    private ArrayList<Avbestillinger> avbestillingerListe = new ArrayList<>();
    private ArrayList<Kunder> kundeListe = new ArrayList<>();
    private Database database;

    // Getter for hver liste
    public ArrayList<Rom> getRomListe() {return romListe;}
    public ArrayList<Kunder> getKundeListe() { return kundeListe;}
    public ArrayList<Utsjekkinger> getUtsjekkingListe() { return utsjekkingListe;}
    public ArrayList<Innsjekkinger> getInnsjekkingerListe() { return innsjekkingerListe;}
    public ArrayList<Avbestillinger> getAvbestillingerListe() { return avbestillingerListe;}
    public ArrayList<Reservasjoner> getReservasjonerListe() { return reservasjonerListe;}

    public Liste(Database database) {
        this.database = database;

        // Tabell rom
        ArrayList<String> romData = database.getData("tblrom");
        leggTilListe(romData, "tblrom");

        // Tabell utsjekking
        ArrayList<String> utsjekkingData = database.getData("tblutsjekking");
        leggTilListe(utsjekkingData, "tblutsjekking");

        // Tabell reservasjon
        ArrayList<String> reservasjonData = database.getData("tblreservasjon");
        leggTilListe(reservasjonData, "tblreservasjon");

        // Tabell kunder
        ArrayList<String> kundeData = database.getData("tblkunde");
        leggTilListe(kundeData, "tblkunde");

        // Tabell innsjekking
        ArrayList<String> innsjekkingData = database.getData("tblinnsjekking");
        leggTilListe(innsjekkingData, "tblinnsjekking");

        // Tabell avbestilling
        ArrayList<String> avbestillingData = database.getData("tblavbestilling");
        leggTilListe(avbestillingData, "tblavbestilling");
    }
    // Legg til
    public void leggTilListe(ArrayList<String> tempListe, String tabellNavn) {
        // Finne ut hvilket objekt basert på tabellNavn
        System.out.println(tempListe);

        // Rom objekt som legges i Rom liste
        if (tabellNavn.equalsIgnoreCase("tblrom")) {
            for (int i = 0; i < tempListe.size(); i += 4) {
                int romid = Integer.parseInt(tempListe.get(i));
                int romnummer = Integer.parseInt(tempListe.get(i + 1));
                String romtype = tempListe.get(i + 2);
                float pris = Float.parseFloat(tempListe.get(i + 3));
                Rom rom = new Rom(romid, romnummer, romtype, pris);
                romListe.add(rom);
            }
        }

        // Utsjekking objekt som legges i Utsjekking liste
        if (tabellNavn.equalsIgnoreCase("tblutsjekking")) {
            for (int i = 0; i < tempListe.size(); i += 3) {
                int utsjekkingid = Integer.parseInt(tempListe.get(i));
                int reservasjonid = Integer.parseInt(tempListe.get(i + 1));
                String utsjekkingdato = tempListe.get(i + 2);
                Utsjekkinger utsjekking = new Utsjekkinger(utsjekkingid, reservasjonid, utsjekkingdato);
                utsjekkingListe.add(utsjekking);
            }
        }

        // Reservasjon objekt som legges i Reverasjon liste
        if (tabellNavn.equalsIgnoreCase("tblreservasjon")) {
            for (int i = 0; i < tempListe.size(); i += 6) {
                int reservasjonid = Integer.parseInt(tempListe.get(i));
                int kundeid = Integer.parseInt(tempListe.get(i + 1));
                int romid = Integer.parseInt(tempListe.get(i + 2));
                String startdato = tempListe.get(i + 3);
                String sluttdato = tempListe.get(i + 4);
                String status = tempListe.get(i + 5);
                Reservasjoner reservasjon = new Reservasjoner(reservasjonid, kundeid, romid, startdato, sluttdato, status);
                reservasjonerListe.add(reservasjon);
            }
        }

        // Kunde objekt som legges i Kunde liste
        if (tabellNavn.equalsIgnoreCase("tblkunde")) {
            for (int i = 0; i < tempListe.size(); i += 4) {
                int kundeid = Integer.parseInt(tempListe.get(i));
                String navn = tempListe.get(i + 1);
                String epost = tempListe.get(i + 2);
                String telefon = tempListe.get(i + 3);
                Kunder kunde = new Kunder(kundeid, navn, epost, telefon);
                kundeListe.add(kunde);
            }
        }

        // Innsjekking objekt som legges i Innsjekking liste
        if (tabellNavn.equalsIgnoreCase("tblinnsjekking")) {
            for (int i = 0; i < tempListe.size(); i += 3) {
                int innsjekkingsid = Integer.parseInt(tempListe.get(i));
                int reservasjonsid = Integer.parseInt(tempListe.get(i + 1));
                String innsjekkingdato = tempListe.get(i + 2);
                Innsjekkinger innsjekking = new Innsjekkinger(innsjekkingsid, reservasjonsid, innsjekkingdato);
                innsjekkingerListe.add(innsjekking);
            }
        }

        // Avbestilling objekt som legges i Avbestilling liste
        if (tabellNavn.equalsIgnoreCase("tblavbestilling")) {
            for (int i = 0; i < tempListe.size(); i += 4) {
                int avbestillingstid = Integer.parseInt(tempListe.get(i));
                int reservasjonsid = Integer.parseInt(tempListe.get(i + 1));
                String avbestillingdato = tempListe.get(i + 2);
                Avbestillinger avbestilling = new Avbestillinger(avbestillingstid, reservasjonsid, avbestillingdato);
                avbestillingerListe.add(avbestilling);
            }
        }
    }



    // Slett


    // Print liste for testing
    public void printRomListe() {
        for (Rom rom : romListe) {
            System.out.println(rom);
        }
    }

    public void printKundeListe() {
        for (Kunder kunde : kundeListe) {
            System.out.println(kunde);
        }
    }

    public Database getDatabase() {
        return database;
    }

}
