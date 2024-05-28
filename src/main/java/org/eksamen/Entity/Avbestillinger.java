package org.eksamen.Entity;
import java.time.LocalDateTime;

public class Avbestillinger {

    public Avbestillinger(int avbestillingsid, int reservasjonsid, LocalDateTime avbestillingdato) {
        this.avbestillingsid = avbestillingsid;
        this.reservasjonsid = reservasjonsid;
        this.avbestillingdato = avbestillingdato;
    }

    private int avbestillingsid;

    private int reservasjonsid;

    private LocalDateTime avbestillingdato;

    public int getAvbestillingsid() {
        return avbestillingsid;
    }

    public int getReservasjonsid() {
        return reservasjonsid;
    }

    public LocalDateTime getAvbestillingdato() {
        return avbestillingdato;
    }

    public void setAvbestillingsid(int avbestillingsid) {
        this.avbestillingsid = avbestillingsid;
    }

    public void setReservasjonsid(int reservasjonsid) {
        this.reservasjonsid = reservasjonsid;
    }

    public void setAvbestillingdato(LocalDateTime avbestillingdato) {
        this.avbestillingdato = avbestillingdato;
    }
}

