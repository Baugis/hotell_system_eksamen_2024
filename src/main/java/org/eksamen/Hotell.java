package org.eksamen;

import org.eksamen.Entity.Innsjekkinger;
import org.eksamen.Entity.Rom;
import org.eksamen.Entity.Kunder;
import org.eksamen.Entity.Utsjekkinger;
import org.eksamen.Liste;

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

    // FUNKSJONALITETER UNDER
    // KUNDE
    // Opprette bruker?
    // Det skal være mulig å lage bruker
    // Påvirker kunde
    // Bruker konstruktør for å enkelt legge til ny bruker
    // Bruker newBruker = new Bruker(1, "Ola Nordmann", "ola@nordmann.no", "12345678");

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
    }

    // Søkealgoritme
    // Bruker skal søke på rom etter tilgjengelige rom, pris og romtype
    // Rommet kan ikke være reservert i tidsperioden bruker søker etter rommet
    // Bruker skal kunne oppgi en fra-til pris
    // Bruker skal kunne oppgi ønsket romtype

    // Reservere rom
    // Bruker skal kunne bestille/reservere rom
    // Bruker må skrive inn kundeid, startdato og sluttdato
    // Dette påvirker reservasjonstabellen / reservasjonslisten

    // Avbestille rom
    // Bruker skal kunne avbestille egen reservasjon
    // Bruker må skrive inn kundeid for å få opp reservasjonen og bekrefte avbestilling
    // Reservasjonstabellen / reservasjonslisten

    // RESEPSJON
    // Innsjekking av kunde
    // Resepsjonen skal kunne sjekke inn en kunde ved hjelp av reservasjonsid
    // Opprettes innsjekkingsid og innsjekkingsdato
    // Påvirker innsjekkingstabellen/liste

    public void innsjekking () {
        Scanner skanner = new Scanner(System.in);
        System.out.println("Oppgi reservasjonsid:");
        String reservasjonsid = skanner.nextLine();

        int reservasjonsidInt = Integer.parseInt(reservasjonsid);

        int nyInnsjekkingsId = liste.getInnsjekkingListe().size() + 1;

        LocalDateTime innsjekkingdato = LocalDateTime.now();

        Innsjekkinger innsjekking = new Innsjekkinger(nyInnsjekkingsId, reservasjonsidInt, innsjekkingdato);
        liste.getInnsjekkingListe().add(innsjekking);
    }

    // Utsjekking av kunde
    // Resepsjonen skal kunne sjekke ut en kunde ved hjelp av reservasjonsid
    // Får en utsjekkingsdato time

    public void utsjekking () {
        Scanner skanner = new Scanner(System.in);
        System.out.println("Oppgi reservasjonsid:");
        String reservasjonsid = skanner.nextLine();

        int reservasjonsidInt = Integer.parseInt(reservasjonsid);

        int nyUtsjekkingsId = liste.getUtsjekkingListe().size() + 1;

        LocalDateTime utsjekkingdato = LocalDateTime.now();

        Utsjekkinger utsjekking = new Utsjekkinger(nyUtsjekkingsId, reservasjonsidInt, utsjekkingdato);
        liste.getUtsjekkingListe().add(utsjekking);
    }


    // ADMINISTRASJON
    // Legge til rom
    // Det skal være mulig å legge til et nytt rom
    // Romid, romnummer, romtype, pris
    // Påvirker romtabell / liste
    // Bruker konstruktør for å enkelt legge til nytt rom


    public void leggTilRom(int romId, int romNummer, String romType, float pris){
        Rom rom = new Rom(romId, romNummer, romType, pris);

        liste.getRomListe().add(rom);
    }

    // Slette rom
    // Det skal være mulig å slette et rom
}
