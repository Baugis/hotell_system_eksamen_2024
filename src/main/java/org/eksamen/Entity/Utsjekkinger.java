package org.eksamen.Entity;

import java.time.LocalDateTime;

public class Utsjekkinger {


    private int utsjekkingid;

    private int reservasjonid;

    private LocalDateTime utsjekkingdato;

    public Utsjekkinger(int utsjekkingid, int reservasjonid, LocalDateTime utsjekkingdato){
        this.utsjekkingid = utsjekkingid;
        this.reservasjonid = reservasjonid;
        this.utsjekkingdato = utsjekkingdato;
    }

    private int getUtsjekkingid(){
        return utsjekkingid;
    }

    private int getReservasjonid(){
        return reservasjonid;
    }

    private LocalDateTime getUtsjekkingdato(){
        return utsjekkingdato;
    }

    public void setUtsjekkingid(int utsjekkingid){
        this.utsjekkingid = utsjekkingid;
    }

    public void setReservasjonid(int reservasjonid){
        this.reservasjonid = reservasjonid;
    }

    public void setUtsjekkingdato(LocalDateTime utsjekkingdato){
        this.utsjekkingdato = utsjekkingdato;
    }
}
