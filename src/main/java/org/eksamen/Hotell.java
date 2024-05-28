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
}
