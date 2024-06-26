package org.eksamen;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import org.eksamen.Entity.*;

import org.eksamen.Entity.Rom;
import org.eksamen.Hotell;

// Selve menysystemet er laget av kandidatnummer 7001 og 7041
// Menysystemet er testet og godkjent av kandidatnummer 7017 og 7035
public class Main {
    static Hotell hotell = new Hotell();
    public static void main(String[] args) {
        Scanner skanner = new Scanner(System.in);

        int valg;

        while (true) {
            // Vis hovedmenyen
            visHovedmeny();

            // Les input fra bruker
            System.out.println("Vennligst velg: ");
            try {
                valg = Integer.parseInt(skanner.nextLine());

                switch (valg) {
                    // Gå til Administrasjon meny
                    case 1:
                        administrasjon(skanner);
                        break;

                    // Gå til Resepsjon meny
                    case 2:
                        resepsjon(skanner);
                        break;

                    // Gå til Kunde meny
                    case 3:
                        kunde(skanner);
                        break;

                    // Avslutt programmet
                    case 9:
                        System.out.println("Takk for nå! Avslutter program.");
                        System.out.println("----------------------------------------");
                        skanner.close();

                        // Sender til database
                        // Sendingen til database er laget av kandidatnummer 7035
                        // Sendingen til database er testet og godkjent av kandidatnummer 7017
                        // ROM
                        ArrayList<Rom> romListe = hotell.getListe().getRomListe();
                        String romQuery = "INSERT INTO tblrom (romid, romnummer, romtype, pris) VALUES (?, ?, ?, ?)";
                        hotell.getListe().getDatabase().sendData(romQuery, romListe);

                        // KUNDER
                        ArrayList<Kunder> kundeListe = hotell.getListe().getKundeListe();
                        String kundeQuery = "INSERT INTO tblkunde (kundeid, navn, epost, telefon) VALUES (?, ?, ?, ?)";
                        hotell.getListe().getDatabase().sendData(kundeQuery, kundeListe);

                        // Avbestillinger
                        ArrayList<Avbestillinger> avbestillingsListe = hotell.getListe().getAvbestillingerListe();
                        String avbestillingsQuery = "INSERT INTO tblavbestilling (avbestillingid, reservasjonid, avbestillingdato) VALUES (?, ?, ?)";
                        hotell.getListe().getDatabase().sendData(avbestillingsQuery, avbestillingsListe);

                        // Innsjekkinger
                        ArrayList<Innsjekkinger> innsjekkingListe = hotell.getListe().getInnsjekkingerListe();
                        String innsjekkingQuery = "INSERT INTO tblinnsjekking (innsjekkingid, reservasjonid, innsjekkingdato) VALUES (?, ?, ?)";
                        hotell.getListe().getDatabase().sendData(innsjekkingQuery, innsjekkingListe);

                        // Reservasjoner
                        ArrayList<Reservasjoner> reservasjonListe = hotell.getListe().getReservasjonerListe();
                        String reservasjonQuery = "INSERT INTO tblreservasjon (reservasjonid, kundeid, romid, startdato, sluttdato, status) VALUES (?, ?, ?, ?, ?)";
                        hotell.getListe().getDatabase().sendData(reservasjonQuery, reservasjonListe);

                        // Utsjekkinger
                        ArrayList<Utsjekkinger> utsjekkingListe = hotell.getListe().getUtsjekkingListe();
                        String utsjekkingQuery = "INSERT INTO tblutsjekking (utsjekkingid, reservasjonid, utsjekkingdato) VALUES (?, ?, ?)";
                        hotell.getListe().getDatabase().sendData(utsjekkingQuery, utsjekkingListe);

                        System.exit(0);
                        break;

                    // Håndter ugyldige valg
                    default:
                        System.out.println("Ugyldig valg. Vennligst velg på nytt.");
                        System.out.println("----------------------------------------");

                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig valg. Vennligst velg på nytt.");
                System.out.println("----------------------------------------");

                // Legg til mellomrom
                System.out.println();
                System.out.println();
            }
        }
    }

    public static void visHovedmeny() {
        System.out.println("Velkommen til Hotell Cæsar!");
        System.out.println("Du har nå tre valg.");
        System.out.println("1. Administrasjon");
        System.out.println("2. Resepsjon");
        System.out.println("3. Kunde");
        System.out.println("9. Avslutt");
        System.out.println("----------------------------------------");
    }

    // Administrasjon er laget av 7041
    // Testet og godkjent av 7017
    public static void administrasjon(Scanner skanner) {
        boolean fortsettISubmeny = true;
        while (fortsettISubmeny) {
            System.out.println("Administrasjon");
            System.out.println("Du har nå to valg: ");
            System.out.println("1. Legge til rom");
            System.out.println("2. Slette rom");
            System.out.println("9. Tilbake til hovedmenyen");
            System.out.println("Vennligst velg: ");
            System.out.println("----------------------------------------");

            try {
                int subValg = Integer.parseInt(skanner.nextLine());
                switch (subValg) {
                    case 1:
                        // Hånter legge til rom
                        System.out.println("Legg til rom");
                        System.out.println("Du har valgt å legge til et rom.");

                        Scanner scanner = new Scanner(System.in);

                        System.out.println("Oppgi romnummer: ");
                        int romNummer = Integer.parseInt(scanner.nextLine());

                        System.out.println("Oppgi romtype: ");
                        String romType = scanner.nextLine();

                        System.out.println("Oppgi pris: ");
                        float pris = Float.parseFloat(scanner.nextLine());

                        hotell.leggTilRom(romNummer, romType, Float.parseFloat(String.valueOf(pris)));

                        System.out.println("");
                        break;
                    case 2:
                        // Håndter slette rom
                        System.out.println("Slette rom valgt.");
                        System.out.println("----------------------------------------");

                        System.out.println("Du har valgt å slette et rom");

                        Scanner scanner2 = new Scanner(System.in);
                        System.out.println("Dette er alle eksisterende rom.");

                        hotell.alleRom();

                        //her
                        System.out.println("Skriv inn id til rom du ønsker å slette: ");
                        int romid = Integer.parseInt(scanner2.nextLine());

                        Rom rom = hotell.finnRom(romid);

                        System.out.println("Dette er rommet du sletter nå: " + rom.getRomid() +", "+ rom.getRomnummer() +", "+ rom.getRomtype() +", "+ rom.getPris());

                        boolean romSlettet = hotell.slettRom(romid);

                        if (romSlettet == true){
                            System.out.println("Rom slettet: " + rom.getRomid() +", "+ rom.getRomnummer() +", "+ rom.getRomtype() +", " + rom.getPris());

                        } else{
                            System.out.println("Rom med Id: " + romid + " kunne ikke slettes.");
                        }

                        System.out.println("");


                        break;
                    case 9:
                        // Gå tilbake til hovedmenyen
                        fortsettISubmeny = false;
                        break;
                    default:
                        System.out.println("Ugyldig valg. Vennligst velg på nytt.");
                        System.out.println("----------------------------------------");

                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig valg. Vennligst velg på nytt.");
                System.out.println("----------------------------------------");

            }
        }
    }

    // Resepsjon er laget av 7001
    // Testet og godkjent av 7017
    public static void resepsjon(Scanner skanner) {
        boolean fortsettISubmeny = true;
        while (fortsettISubmeny) {
            System.out.println("Resepsjon");
            System.out.println("Du har nå to valg: ");
            System.out.println("1. Innsjekk");
            System.out.println("2. Utsjekk");
            System.out.println("9. Tilbake til hovedmenyen");
            System.out.println("Vennligst velg: ");
            System.out.println("----------------------------------------");

            try {
                int subValg = Integer.parseInt(skanner.nextLine());
                switch (subValg) {
                    case 1:
                        // Håndter innsjekk
                        System.out.println("Innsjekk valgt.");
                        System.out.println("----------------------------------------");
                        hotell.innsjekking();

                        break;
                    case 2:
                        // Håndter utsjekk
                        System.out.println("Utsjekk valgt.");
                        System.out.println("----------------------------------------");
                        hotell.utsjekking();

                        break;
                    case 9:
                        // Gå tilbake til hovedmenyen
                        fortsettISubmeny = false;
                        break;
                    default:
                        System.out.println("Ugyldig valg. Vennligst velg på nytt.");
                        System.out.println("----------------------------------------");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig valg. Vennligst velg på nytt.");
                System.out.println("----------------------------------------");
            }
        }
    }

    // Kunde er laget av 7001
    // Testet og godkjent av 7041
    public static void kunde(Scanner skanner) {
        boolean fortsettISubmeny = true;
        while (fortsettISubmeny) {
            System.out.println("Kunde");
            System.out.println("Du har nå tre valg: ");
            System.out.println("1. Søk etter rom og reserver");
            System.out.println("2. Opprett kundebruker");
            System.out.println("3. Avbestill reservasjon");
            System.out.println("9. Tilbake til hovedmenyen");
            System.out.println("Vennligst velg: ");
            System.out.println("----------------------------------------");

            try {
                int subValg = Integer.parseInt(skanner.nextLine());
                switch (subValg) {
                    case 1:
                        // Håndter se tilgjengelige rom
                        System.out.println("Søk etter rom og reservere");
                        System.out.println("----------------------------------------");
                        hotell.sokeRom();
                        break;
                    case 2:
                        System.out.println("Legge til kundebruker er valgt");
                        System.out.println("----------------------------------------");
                        hotell.leggTilNyKunde();
                        break;
                    case 3:
                        System.out.println("Avbestille reservasjon er valgt");
                        System.out.println("----------------------------------------");
                        hotell.avbestilleRom();
                        break;
                    case 9:
                        // Gå tilbake til hovedmenyen
                        fortsettISubmeny = false;
                        break;
                    default:
                        System.out.println("Ugyldig valg. Vennligst velg på nytt.");
                        System.out.println("----------------------------------------");
                        hotell.leggTilNyKunde();
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig valg. Vennligst velg på nytt.");
                System.out.println("----------------------------------------");
            }
        }
    }
}