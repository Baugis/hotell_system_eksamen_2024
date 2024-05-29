package org.eksamen.Entity;
import org.eksamen.Hotell;
import org.eksamen.Liste;

// Kunder er opprettet av kandidatnummer 7001
public class Kunder {

    // Oppretter konstruktør
    public Kunder (int kundeid, String navn, String epost, String telefon) {
        this.kundeid = kundeid;
        this.navn = navn;
        this.epost = epost;
        this.telefon = telefon;
    }

    private int kundeid;
    private String navn;
    private String epost;
    private String telefon;

    // Lager gettere
    public int getKundeid() {
        return kundeid;
    }

    public String getNavn() {
        return navn;
    }

    public String getEpost() {
        return epost;
    }

    public String getTelefon() {
        return telefon;
    }

    // Lager settere
    public void setKundeid(int kundeid) {this.kundeid = kundeid;}

    public void setNavn(String navn) {this.navn = navn;}

    public void setEpost(String epost) {this.epost = epost;}

    public void setTelefon(String telefon) {this.telefon = telefon;}

    @Override
    public String toString() {
        return "Kunder{" +
                "kundeid=" + kundeid +
                ", navn='" + navn + '\'' +
                ", epost='" + epost + '\'' +
                ", telefon=" + telefon +
                '}';
    }
}
