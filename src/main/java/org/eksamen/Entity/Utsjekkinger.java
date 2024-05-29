package org.eksamen.Entity;

import java.time.LocalDateTime;

// Utsjekkinger er opprettet av kandidatnummer 7041
public class Utsjekkinger {
    private int utsjekkingid;
    private int reservasjonid;
    private String utsjekkingdato;

    // Oppretter konstruktør
    public Utsjekkinger(int utsjekkingid, int reservasjonid, String utsjekkingdato){
        this.utsjekkingid = utsjekkingid;
        this.reservasjonid = reservasjonid;
        this.utsjekkingdato = utsjekkingdato;
    }

    // Oppretter gettere
    public int getUtsjekkingid(){
        return utsjekkingid;
    }

    public int getReservasjonid(){
        return reservasjonid;
    }

    public String getUtsjekkingdato(){
        return utsjekkingdato;
    }

    // Oppretter settere
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
