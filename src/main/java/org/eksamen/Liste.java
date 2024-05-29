package org.eksamen;

import org.eksamen.Entity.*;

import java.util.ArrayList;

public class Liste {
    // ArrayList for hver tabell
    private ArrayList<Rom> romListe = new ArrayList<>();
    private ArrayList<Utsjekkinger> utsjekkingListe = new ArrayList<>();
    private ArrayList<Reservasjoner> reservasjonerListe = new ArrayList<>();
    private ArrayList<Innsjekkinger> innsjekkinngerListe = new ArrayList<>();
    private ArrayList<Avbestillinger> avbestillingerListe = new ArrayList<>();
    private ArrayList<Kunder> kundeListe = new ArrayList<>();
    private ArrayList<Innsjekkinger> innsjekkingListe = new ArrayList<>();
    private ArrayList<Avbestillinger> avbestillingListe = new ArrayList<>();
    private Database database;


    public ArrayList<Rom> getRomListe() {
        return romListe;
    }

    public ArrayList<Kunder> getKundeListe() { return kundeListe;}

    // Opprette Data objekt for å
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

        // Utskrift Romliste for test
        printRomListe();

        // Utsjekking objekt som legges i Utsjekking liste


        // Reservasjon objekt som legges i Reverasjon liste


        // Kunde objekt som legges i Kunde liste


        // Innsjekking objekt som legges i Innsjekking liste


        // Avbestilling objekt som legges i Avbestilling liste
    }



    // Slett


    // Print liste for testing
    public void printRomListe() {
        for (Rom rom : romListe) {
            System.out.println(rom);
        }
    }

}
