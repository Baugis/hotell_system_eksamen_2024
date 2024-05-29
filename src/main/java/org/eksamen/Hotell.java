package org.eksamen;

import org.eksamen.Entity.*;
import org.eksamen.Liste;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Objects;
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
        int nyKundeId = liste.getKundeListe().size() + 1;

        Kunder kunde = new Kunder(nyKundeId, navn, epost, telefon);

        liste.getKundeListe().add(kunde);

        liste.printKundeListe();

        System.out.println("Velkommen til oss, " + navn + "!");
    }

    // Oppretter en søkealgoritme hvor bruker kan søke etter rom basert på ulike krav
    // Funksjonen er laget av kandidatnummer 7017
    // Funksjonen er testet og godkjent av kandidatnummer 7035

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
    // Funksjonen er testet og godkjent av kandidatnummer 7001
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

        // Går gjennom alle reservasjonene
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
    // Funksjonen er testet og godkjent av kandidatnummer 7041
    public void innsjekking() {
        Scanner skanner = new Scanner(System.in);
        System.out.println("Oppgi reservasjonsid:");
        int reservasjonsid = skanner.nextInt();

        // Må sjekke om reservasjons id finnes i reservasjonslisten først og deretter videre kode.
        // Samme i utsjekking

        ArrayList<Reservasjoner> reservasjonerArrayList = liste.getReservasjonerListe();

        for (Reservasjoner reservasjon : reservasjonerArrayList) {
            if (reservasjon.getReservasjonid() == reservasjonsid) {
                int nyInnsjekkingsId = liste.getInnsjekkingerListe().size() + 1;

                String innsjekkingdato = String.valueOf(LocalDateTime.now());

                Innsjekkinger innsjekking = new Innsjekkinger(nyInnsjekkingsId, reservasjonsid, innsjekkingdato);
                liste.getInnsjekkingerListe().add(innsjekking);

                System.out.println("Kunde med reservasjonsid " + reservasjonsid + " er sjekket inn med timestamp " + innsjekkingdato);
            }
        }
    }

    // Funksjon for å utsjekking av gjest. Funksjonen er laget av kandidatnummer 7001
    // Funksjonen er testet og godkjent av kandidatnummer 7041
    public void utsjekking() {
        Scanner skanner = new Scanner(System.in);
        System.out.println("Oppgi reservasjonsid:");
        int reservasjonsid = skanner.nextInt();

        ArrayList<Innsjekkinger> innsjekkingerArrayList = liste.getInnsjekkingerListe();

        for (Innsjekkinger innsjekkinger : innsjekkingerArrayList) {
            if (innsjekkinger.getReservasjonsid() == reservasjonsid) {
                int nyUtsjekkingsId = liste.getUtsjekkingListe().size() + 1;

                String utsjekkingdato = String.valueOf(LocalDateTime.now());

                Utsjekkinger utsjekking = new Utsjekkinger(nyUtsjekkingsId, reservasjonsid, utsjekkingdato);
                liste.getUtsjekkingListe().add(utsjekking);

                System.out.println("Kunde med reservasjonsid " + reservasjonsid + " er sjekket ut med timestamp " + utsjekkingdato);
            }
        }
    }


    // MENYVALG ADMINISTRASJON

    // Funksjonen for å legge til rom er kodet av kandidatnummer 7041
    // Funksjonen er testet og godkjent av kandidatnummer 7035
    public void leggTilRom(int romNummer, String romType, float pris) {
        int nyRomId = liste.getRomListe().size() + 1;
        Rom rom = new Rom(nyRomId, romNummer, romType, pris);

        rom.setRomid(nyRomId);
        rom.setRomnummer(romNummer);
        rom.setRomtype(romType);
        rom.setPris(pris);

        liste.getRomListe().add(rom);

        System.out.println("Rommet med romid " + nyRomId + " er lagt til");

    }

    // Funksjonen for å slette rom er kodet av kandidatnummer 7041
    // Funksjonen er testet og godkjent av kandidatnummer 7001

    public boolean slettRom(int romid) {
        Rom rom = finnRom(romid);

        if (rom != null){
            liste.getRomListe().remove(rom);

            database.deleteRoom(romid);

            return true;
        } else{
            String ikkeFunnet = "Rommet finnes ikke.";
        }
        return false;


    }

    public void alleRom(){
        for (Rom rom : liste.getRomListe()){
            System.out.println(rom.getRomid() + ", " + rom.getRomnummer() + ", " + rom.getRomtype() + ", " + rom.getPris());
        }


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