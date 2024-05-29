package org.eksamen.Entity;

import java.time.LocalDateTime;

public class Utsjekkinger {


    private int utsjekkingid;

    private int reservasjonid;

    private String utsjekkingdato;

    public Utsjekkinger(int utsjekkingid, int reservasjonid, String utsjekkingdato){
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

    private String getUtsjekkingdato(){
        return utsjekkingdato;
    }

    public void setUtsjekkingid(int utsjekkingid){
        this.utsjekkingid = utsjekkingid;
    }

    public void setReservasjonid(int reservasjonid){
        this.reservasjonid = reservasjonid;
    }

    public void setUtsjekkingdato(String utsjekkingdato){
        this.utsjekkingdato = utsjekkingdato;
    }
}
