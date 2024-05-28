package org.eksamen;

public class Hotell {

    private Liste liste;

    // Opprette Liste objekt, hente data fra database
    public Hotell() {
        liste = new Liste();
        Database database = new Database();
        Database.getData("tblkunde");
        Database.getData("tblrom");
        Database.getData("tblavbestilling");
        Database.getData("tblinnsjekking");
        Database.getData("tblutsjekking");
        Database.getData("tblreservasjon");
    }

    // FUNKSJONALITETER UNDER
    // KUNDE
        // Opprette bruker?
        // Det skal være mulig å lage bruker
        // Påvirker kunde

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

        // Utsjekking av kunde
        // Resepsjonen skal kunne sjekke ut en kunde ved hjelp av reservasjonsid
        // Får en utsjekkingsdato time

    // ADMINISTRASJON
        // Legge til rom
        // Det skal være mulig å legge til et nytt rom
        // Romid, romnummer, romtype, pris
        // Påvirker romtabell / liste

        // Slette rom
        // Det skal være mulig å slette et rom
}
