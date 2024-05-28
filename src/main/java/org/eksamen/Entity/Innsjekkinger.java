package org.eksamen.Entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Innsjekkinger {
    private int innsjekkingsid;

    private int reservasjonsid;

    private LocalDateTime innsjekkingdato;

    public int getInnsjekkingsid() {
        return innsjekkingsid;
    }

    public int getReservasjonsid() {
        return reservasjonsid;
    }

    public void setInnsjekkingsid(int innsjekkingsid) {this.innsjekkingsid = innsjekkingsid;}

    public void setReservasjonsid(int reservasjonsid) {this.reservasjonsid = reservasjonsid;}
}
