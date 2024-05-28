package org.eksamen.Entity;

public class Avbestillinger {
    private String avbestillingsid;

    private String reservasjonsid;

    private String avbestillingdato;

    public String getAvbestillingsid() {
        return avbestillingsid;
    }

    public String getReservasjonsid() {
        return reservasjonsid;
    }

    public String getAvbestillingdato() {
        return avbestillingdato;
    }

    public void setAvbestillingsid(String avbestillingsid) { this.avbestillingsid = avbestillingsid;}

    public void setReservasjonsid(String reservasjonsid) {this.reservasjonsid = reservasjonsid;}

    public void setAvbestillingdato(String avbestillingdato) {this.avbestillingdato = avbestillingdato;}
}

