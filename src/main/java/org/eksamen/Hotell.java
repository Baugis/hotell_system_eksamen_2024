package org.eksamen;

public class Hotell {

    private Database database;
    private Liste liste;

    public Hotell() {
        this.database = new Database();
        this.liste = new Liste(database);
    }

    // FUNKSJONALITETER UNDER
}
