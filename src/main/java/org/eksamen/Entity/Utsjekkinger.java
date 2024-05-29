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

    public int getUtsjekkingid(){
        return utsjekkingid;
    }

    public int getReservasjonid(){
        return reservasjonid;
    }

    public String getUtsjekkingdato(){
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
