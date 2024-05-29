package org.eksamen;

import org.eksamen.Entity.*;
import org.eksamen.Liste;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Iterator;

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
    // Testet og godkjent av kandidatnummer 7035
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
    // Funksjonen er laget av kandidatnummer 7017
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

        // Filtrere gjennom listene
        ArrayList<Rom> tilgjengeligeRom = new ArrayList<>();
        for (Rom rom : liste.getRomListe()) {

            // Kriterier for rom
            if (rom.getRomtype().equalsIgnoreCase(romtype) && rom.getPris() < maksPris && rom.getPris() > minPris) {
                tilgjengeligeRom.add(rom);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Iterator<Rom> romIterator = tilgjengeligeRom.iterator();
        while (romIterator.hasNext()) {
            Rom rom = romIterator.next();
            for (Reservasjoner reservasjon : liste.getReservasjonerListe()) {
                LocalDate startDatoLocal = LocalDate.parse(startDato, formatter);
                LocalDate sluttDatoLocal = LocalDate.parse(sluttDato, formatter);
                LocalDate reservasjonStartDato = LocalDate.parse(reservasjon.getStartdato(), formatter);
                LocalDate reservasjonSluttDato = LocalDate.parse(reservasjon.getSluttdato(), formatter);

                // Dato kriterier
                if (rom.getRomid() == reservasjon.getRomid() &&
                        startDatoLocal.isBefore(reservasjonSluttDato) &&
                        sluttDatoLocal.isAfter(reservasjonStartDato)) {
                    System.out.println("yo");
                    romIterator.remove();
                }
            }
        }

        // Resultat (rom tilgjengelig)
        if (tilgjengeligeRom.isEmpty()) {
            System.out.println("Ingen rom");
        } else {
            for (Rom rom : tilgjengeligeRom) {
                System.out.println(rom);
            }
        }

        // Reservasjon basert på tilgjengelige rom
        System.out.println("Hvilket rom ønsker du");
        String bookeRom = skanner.nextLine();

        System.out.println("Kundenummer");
        String kundeid = skanner.nextLine();

        int reservasjonid = liste.getReservasjonerListe().size() + 1;
        int romid = Integer.parseInt(bookeRom);
        String status = "Bestilt";

        Reservasjoner reservasjon = new Reservasjoner(reservasjonid, Integer.parseInt(kundeid), romid, startDato, sluttDato, status);
        liste.getReservasjonerListe().add(reservasjon);
    }

    // Funksjon for reservasjon av rom. Funksjonen er laget av kandidatnummer 7017
    // Funksjonen er testet og godkjent av kandidatnummer ...
    public void reservasjon() {
        System.out.println("Reservasjon");
    }

    // Funksjon for avbestilling av rom. Funksjonen er laget av kandidatnummer 7035
    // Funksjonen er testet og godkjent av kandidatnummer ...
    public void avbestilleRom() {
        System.out.println("Avbestille rom");
    }

    // MENYVALG RESEPSJON

    // Funksjon for innsjekking av kunde. Funksjonen er laget av kandidatnummer 7001
    // Funksjonen er testet og godkjent av kandidatnummer ..

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
    // Funksjonen er testet og godkjent av kandidatnummer ..
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

    // Funksjonen for å legge til rom er kodet av kandidatnummer 7041
    // Funksjonen er testet og godkjent av kandidatnummer ...
    public void leggTilRom(int romNummer, String romType, float pris) {
        int nyRomId = liste.getRomListe().size() + 1;
        Rom rom = new Rom(nyRomId, romNummer, romType, pris);

        liste.getRomListe().add(rom);
    }

    // Funksjonen for å slette rom er kodet av kandidatnummer 7041
    // Funksjonen er testet og godkjent av kandidatnummer ...

    public void slettRom(int romid, int romnummer, String romtype, float pris) {

    }



    public Rom finnRom(int romid) {
        for (Rom rom : liste.getRomListe()) {
            if (rom != null && rom.getRomid() == romid) {
                return rom;
            }

        }
        return null;
    }
}
