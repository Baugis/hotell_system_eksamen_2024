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

        System.out.println("Velkommen til oss, " + navn + "!");
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
    }

    // Funksjon for reservasjon av rom. Funksjonen er laget av kandidatnummer 7017
    // Funksjonen er testet og godkjent av kandidatnummer ...
    public void reservasjon() {
        System.out.println("Reservasjon");
    }

    // Funksjon for avbestilling av rom. Funksjonen er laget av kandidatnummer 7035
    // Funksjonen er testet og godkjent av kandidatnummer ...
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

    // MENYVALG RESEPSJON

    // Funksjon for innsjekking av kunde. Funksjonen er laget av kandidatnummer 7001
    // Funksjonen er testet og godkjent av kandidatnummer ..

    public void innsjekking () {
        Scanner skanner = new Scanner(System.in);
        System.out.println("Oppgi reservasjonsid:");
        String reservasjonsid = skanner.nextLine();

        // Må sjekke om reservasjons id finnes i reservasjonslisten først og deretter videre kode.
        // Samme i utsjekking

        int reservasjonsidInt = Integer.parseInt(reservasjonsid);

        int nyInnsjekkingsId = liste.getInnsjekkingerListe().size() + 1;

        String innsjekkingdato = String.valueOf(LocalDateTime.now());

        Innsjekkinger innsjekking = new Innsjekkinger(nyInnsjekkingsId, reservasjonsidInt, innsjekkingdato);
        liste.getInnsjekkingerListe().add(innsjekking);

        System.out.println("Kunde med reservasjonsid " + reservasjonsid + " er sjekket inn med timestamp " + innsjekkingdato);
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

        System.out.println("Kunde med reservasjonsid " + reservasjonsid + " er sjekket ut med timestamp " + utsjekkingdato);
    }


    // MENYVALG ADMINISTRASJON

    // Funksjonen for å legge til rom er kodet av kandidatnummer 7041
    // Funksjonen er testet og godkjent av kandidatnummer ...
    public void leggTilRom(int romNummer, String romType, float pris) {
        int nyRomId = liste.getRomListe().size() + 1;
        Rom rom = new Rom(nyRomId, romNummer, romType, pris);

        liste.getRomListe().add(rom);

        System.out.println("Rommet med romid " + nyRomId + " er lagt til");
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
