package org.eksamen;

import org.eksamen.Entity.Innsjekkinger;
import org.eksamen.Entity.Rom;
import org.eksamen.Entity.Kunder;
import org.eksamen.Entity.Utsjekkinger;
import org.eksamen.Liste;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Hotell {
    private Database database;
    private Liste liste;

    public Hotell() {
        this.database = new Database();
        this.liste = new Liste(database);
    }

    public Liste getListe() {
        return liste;
    }

    // Her kommer de ulike funksjonene
    // MENYVALG KUNDE

    // Funksjon for å legge til en ny kunde. Funksjonen er laget av kandidatnummer 7001
    // Testet av kandidatnummer ..
    public void leggTilNyKunde() {
        Scanner skanner = new Scanner(System.in);

        System.out.println("Oppgi navn:");
        String navn = skanner.nextLine();

        System.out.println("Oppgi epost:");
        String epost = skanner.nextLine();

        System.out.println("Oppgi telefonnummer:");
        String telefon = skanner.nextLine();

        // Genererer kundeID
        // Husk å legg til genrerererNyKundeId i kunder klassen så den returnerer
        // en unik kundeId basert på tidlgiere kunder. telle + 1
        int nyKundeId = liste.getKundeListe().size() + 1;

        Kunder kunde = new Kunder(nyKundeId, navn, epost, telefon);

        liste.getKundeListe().add(kunde);

        liste.printKundeListe();
    }

    // Oppretter en søkealgoritme hvor bruker kan søke etter rom basert på ulike krav
    // Funksjonen er laget av kandidatnummer 7001 og kandidatnummer 7017
    // Funksjonen er testet og godkjent av kandidatnummer ...

    public void sokeRom() {
        Scanner skanner = new Scanner(System.in);

        System.out.println("Oppgi startdato (yyyy-MM-dd)");
        String startDato = skanner.nextLine();

        System.out.println("Oppgi sluttdato(yyyy-MM-dd)");
        String sluttDato = skanner.nextLine();

        System.out.println("Oppgi minimumspris:");
        double minPris = Double.parseDouble(skanner.nextLine());

        System.out.println("Oppgi maksimumspris:");
        double maksPris = Double.parseDouble(skanner.nextLine());

        System.out.println("Oppgi ønsket romtype:");
        String romtype = skanner.nextLine();
    }

    // Funksjon for reservasjon av rom. Funksjonen er laget av kandidatnummer ....
    // Funksjonen er testet og godkjent av kandidatnummer ...
    public void reservasjon() {
        System.out.println("Reservasjon");
    }

    // Funksjon for avbestilling av rom. Funksjonen er laget av kandidatnummer ....
    // Funksjonen er testet og godkjent av kandidatnummer ...
    public void avbestilleRom() {
        System.out.println("Avbestille rom");
    }

    // MENYVALG RESEPSJON

    // Funksjon for innsjekking av kunde. Funksjonen er laget av kandidatnummer 7001
    // Funksjonen er testet og godkjent av kandidatnummer ...

    public void innsjekking () {
        Scanner skanner = new Scanner(System.in);
        System.out.println("Oppgi reservasjonsid:");
        String reservasjonsid = skanner.nextLine();

        int reservasjonsidInt = Integer.parseInt(reservasjonsid);

        int nyInnsjekkingsId = liste.getInnsjekkingerListe().size() + 1;

        String innsjekkingdato = String.valueOf(LocalDateTime.now());

        Innsjekkinger innsjekking = new Innsjekkinger(nyInnsjekkingsId, reservasjonsidInt, innsjekkingdato);
        liste.getInnsjekkingerListe().add(innsjekking);
    }

    // Funksjon for å utsjekking av gjest. Funksjonen er laget av kandidatnummer 7001
    // Funksjonen er testet og godkjent av kandidatnummer ...
    public void utsjekking () {
        Scanner skanner = new Scanner(System.in);
        System.out.println("Oppgi reservasjonsid:");
        String reservasjonsid = skanner.nextLine();

        int reservasjonsidInt = Integer.parseInt(reservasjonsid);

        int nyUtsjekkingsId = liste.getUtsjekkingListe().size() + 1;

        String utsjekkingdato = String.valueOf(LocalDateTime.now());

        Utsjekkinger utsjekking = new Utsjekkinger(nyUtsjekkingsId, reservasjonsidInt, utsjekkingdato);
        liste.getUtsjekkingListe().add(utsjekking);
    }


    // MENYVALG ADMINISTRASJON

    // Funksjonen for å legge til rom er kodet av kandidatnummer ...
    // Funksjonen er testet og godkjent av kandidatnummer ...
    public void leggTilRom(int romNummer, String romType, float pris) {
        int nyRomId = liste.getRomListe().size() + 1;
        Rom rom = new Rom(nyRomId, romNummer, romType, pris);

        liste.getRomListe().add(rom);
    }

    // Funksjonen for å slette rom er kodet av kandidatnummer ...
    // Funksjonen er testet og godkjent av kandidatnummer ...

    public void slettRom(int romid, int romnummer, String romtype, float pris) {

    }

    public Rom finnRom(int romid) {
        for (Rom rom : liste.getRomListe()) {
            if (rom.getRomid() == romid) {
                return rom;
            }

        }
        return null;
    }
}
