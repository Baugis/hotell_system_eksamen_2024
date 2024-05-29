package org.eksamen.Entity;
import java.time.LocalDateTime;

// Avbestillinger er opprettet av kandidatnummer 7001
public class Avbestillinger {

    // Oppretter konstruktør
    public Avbestillinger(int avbestillingsid, int reservasjonsid, String avbestillingdato) {
        this.avbestillingsid = avbestillingsid;
        this.reservasjonsid = reservasjonsid;
        this.avbestillingdato = avbestillingdato;
    }

    private int avbestillingsid;
    private int reservasjonsid;
    private String avbestillingdato;

    // Lager gettere
    public int getAvbestillingsid() {
        return avbestillingsid;
    }

    public int getReservasjonsid() {
        return reservasjonsid;
    }

    public String getAvbestillingdato() {
        return avbestillingdato;
    }

    // Lager settere
    public void setAvbestillingsid(int avbestillingsid) {
        this.avbestillingsid = avbestillingsid;
    }

    public void setReservasjonsid(int reservasjonsid) {
        this.reservasjonsid = reservasjonsid;
    }

    public void setAvbestillingdato(String avbestillingdato) {
        this.avbestillingdato = avbestillingdato;
    }
}

