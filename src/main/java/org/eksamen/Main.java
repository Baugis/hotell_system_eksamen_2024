package org.eksamen;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotell hotell = new Hotell();
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

    private static void visHovedmeny() {
        System.out.println("Velkommen til Hotell Cæsar!");
        System.out.println("Du har nå tre valg.");
        System.out.println("1. Administrasjon");
        System.out.println("2. Resepsjon");
        System.out.println("3. Kunde");
        System.out.println("9. Avslutt");
        System.out.println("----------------------------------------");

    }

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
                        // Håndter legge til rom
                        System.out.println("Legg til rom");
                        System.out.println("Du har valgt å legge til et rom.");





                        break;
                    case 2:
                        // Håndter slette rom
                        System.out.println("Slette rom valgt.");
                        System.out.println("----------------------------------------");

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
                        break;
                    case 2:
                        // Håndter utsjekk
                        System.out.println("Utsjekk valgt.");
                        System.out.println("----------------------------------------");
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

    public static void kunde(Scanner skanner) {
        boolean fortsettISubmeny = true;
        while (fortsettISubmeny) {
            System.out.println("Kunde");
            System.out.println("Du har nå to valg: ");
            System.out.println("1. Se tilgjengelige rom");
            System.out.println("2. Booke rom");
            System.out.println("9. Tilbake til hovedmenyen");
            System.out.println("Vennligst velg: ");
            System.out.println("----------------------------------------");

            try {
                int subValg = Integer.parseInt(skanner.nextLine());
                switch (subValg) {
                    case 1:
                        // Håndter se tilgjengelige rom
                        System.out.println("Se tilgjengelige rom valgt.");
                        System.out.println("----------------------------------------");
                        break;
                    case 2:
                        // Håndter booke rom
                        System.out.println("Booke rom valgt.");
                        System.out.println("----------------------------------------");
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
}