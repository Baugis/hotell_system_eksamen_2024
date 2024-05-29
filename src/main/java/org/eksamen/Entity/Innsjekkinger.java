package org.eksamen.Entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

// Avbestillinger er opprettet av kandidatnummer 7001
public class Innsjekkinger {

    // Oppretter konstruktør
    public Innsjekkinger (int innsjekkingsid, int reservasjonsid, String innsjekkingdato) {
        this.innsjekkingsid = innsjekkingsid;
        this.reservasjonsid = reservasjonsid;
        this.innsjekkingdato = innsjekkingdato;
    }

    private int innsjekkingsid;

    private int reservasjonsid;

    private String innsjekkingdato;

    // Lager gettere
    public int getInnsjekkingsid() {
        return innsjekkingsid;
    }

    public int getReservasjonsid() {
        return reservasjonsid;
    }

    public String getInnsjekkingdato() {
        return innsjekkingdato;
    }

    // Lager settere
    public void setInnsjekkingsid(int innsjekkingsid) {this.innsjekkingsid = innsjekkingsid;}

    public void setReservasjonsid(int reservasjonsid) {this.reservasjonsid = reservasjonsid;}

    public void setInnsjekkingdato(String innsjekkingdato) {this.innsjekkingdato = innsjekkingdato;}

    @Override
    public String toString() {
        return "Innsjekkinger{" +
                "innsjekkingsid=" + innsjekkingsid +
                ", reservasjonsid='" + reservasjonsid + '\'' +
                ", innsjekkingdato='" + innsjekkingdato +
                '}';
    }
}
