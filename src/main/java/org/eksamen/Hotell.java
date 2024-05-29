package org.eksamen;

import org.eksamen.Entity.*;
import org.eksamen.Liste;
import org.w3c.dom.ls.LSOutput;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Objects;
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

    // FUNKSJONALITETER UNDER
    // KUNDE

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

    // Søkealgoritme
    // Bruker skal søke på rom etter tilgjengelige rom, pris og romtype
    // Rommet kan ikke være reservert i tidsperioden bruker søker etter rommet
    // Bruker skal kunne oppgi en fra-til pris
    // Bruker skal kunne oppgi ønsket romtype

    // Bruker skal først skrive inn til-fra dato
    // Bruker skal så skrive inn til-fra pris
    // Bruker skal så skrive inn ønsket romtype
    // Det skal deretter printes ut de tilgjengelige rommene

    // Oppretter en søkealgoritme hvor bruker kan søke etter rom basert på ulike krav
    // Funksjonen er laget av kandidatnummer 7001 og kandidatnummer 7017
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

    // Reservere rom
    // Bruker skal kunne bestille/reservere rom
    // Bruker må skrive inn kundeid, startdato og sluttdato
    // Dette påvirker reservasjonstabellen / reservasjonslisten

    public void reservasjon() {
        System.out.println("Reservasjon");
    }



    // Avbestille rom
    // Bruker skal kunne avbestille egen reservasjon
    // Bruker må skrive inn kundeid for å få opp reservasjonen og bekrefte avbestilling
    // Reservasjonstabellen / reservasjonslisten

    public void avbestilleRom() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Oppgi kundeid");
        Integer kundeId = scanner.nextInt();

        System.out.println("Oppgi reservasjonid:");
        Integer reservasjonId = scanner.nextInt();

        ArrayList<Reservasjoner> reservasjonListe = liste.getReservasjonerListe();

        // Loop through the reservations list
        for (Reservasjoner reservasjon : reservasjonListe) {
            boolean found = false;
            if (kundeId == reservasjon.getKundeid() && reservasjonId == reservasjon.getReservasjonid()) {
                if (!Objects.equals(reservasjon.getStatus(), "avbestilt")) {
                    // Endrer status fra "Bekreftet" til "Avbestilt" på reservasjon
                    reservasjon.setStatus("avbestilt");

                    // Legger til rad i avbestillingtabbelen med info om avbestilling
                    int nyAvbestillingid = liste.getAvbestillingerListe().size() + 1;
                    String avbestillingDato = String.valueOf(LocalDateTime.now());
                    Avbestillinger avbestillinger = new Avbestillinger(nyAvbestillingid, reservasjonId, avbestillingDato);

                    liste.getAvbestillingerListe().add(avbestillinger);

                    System.out.println("Rom allerede avbestilt");
                    return;
                } else {
                    System.out.println("Rom allerede avbestilt");
                    return;
                }
            }
        }
    }

    // RESEPSJON
    // Innsjekking av kunde
    // Resepsjonen skal kunne sjekke inn en kunde ved hjelp av reservasjonsid
    // Opprettes innsjekkingsid og innsjekkingsdato
    // Påvirker innsjekkingstabellen/liste

    // Funksjon for innsjekking av kunde. Funksjonen er laget av kandidatnummer 7001
    // Testet av kandidatnummer ...

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

    // Utsjekking av kunde
    // Resepsjonen skal kunne sjekke ut en kunde ved hjelp av reservasjonsid
    // Får en utsjekkingsdato time

    // Funksjon for å utsjekking av gjest. Funksjonen er laget av kandidatnummer 7001
    // Funksjonen er testet av kandidatnummer ...

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


    // ADMINISTRASJON
    // Legge til rom
    // Det skal være mulig å legge til et nytt rom
    // Romid, romnummer, romtype, pris
    // Påvirker romtabell / liste
    // Bruker konstruktør for å enkelt legge til nytt rom



    public void leggTilRom(int romNummer, String romType, float pris) {
        int nyRomId = liste.getRomListe().size() + 1;
        Rom rom = new Rom(nyRomId, romNummer, romType, pris);

        liste.getRomListe().add(rom);
    }

    // Slette rom
    // Det skal være mulig å slette et rom

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
