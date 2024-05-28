package org.eksamen.Entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Innsjekkinger {

    public Innsjekkinger (int innsjekkingsid, int reservasjonsid, LocalDateTime innsjekkingdato) {
        this.innsjekkingsid = innsjekkingsid;
        this.reservasjonsid = reservasjonsid;
        this.innsjekkingdato = innsjekkingdato;
    }

    private int innsjekkingsid;

    private int reservasjonsid;

    private LocalDateTime innsjekkingdato;

    public int getInnsjekkingsid() {
        return innsjekkingsid;
    }

    public int getReservasjonsid() {
        return reservasjonsid;
    }

    public LocalDateTime getInnsjekkingdato() {
        return innsjekkingdato;
    }

    public void setInnsjekkingsid(int innsjekkingsid) {this.innsjekkingsid = innsjekkingsid;}

    public void setReservasjonsid(int reservasjonsid) {this.reservasjonsid = reservasjonsid;}
    public void setInnsjekkingdato(LocalDateTime innsjekkingdato) {this.innsjekkingdato = innsjekkingdato;}
}
