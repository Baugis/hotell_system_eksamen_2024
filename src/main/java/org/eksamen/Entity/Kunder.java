package org.eksamen.Entity;

public class Kunder {
    private int kundeid;
    private String navn;
    private String epost;
    private String telefon;

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

    public void setKundeid(int kundeid) {this.kundeid = kundeid;}

    public void setNavn(String navn) {this.navn = navn;}

    public void setEpost(String epost) {this.epost = epost;}

    public void setTelefon(String telefon) {this.telefon = telefon;}
}
