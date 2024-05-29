package org.eksamen.Entity;

import java.time.LocalDateTime;

public class Reservasjoner {


    private int reservasjonid;

    private int kundeid;

    private int romid;

    private String startdato;

    private String sluttdato;

    private String status;

    public Reservasjoner(int reservasjonid, int kundeid, int romid, String startdato, String sluttdato, String status){
        this.reservasjonid = reservasjonid;
        this.kundeid = kundeid;
        this.romid = romid;
        this.startdato = startdato;
        this.sluttdato = sluttdato;
        this.status = status;
    }

    private int getReservasjonid(){
        return reservasjonid;
    }

    private int getKundeid(){
        return kundeid;
    }

    private int getRomid(){
        return romid;
    }

    private String getStartdato(){
        return startdato;
    }

    private String getSluttdato(){
        return sluttdato;
    }

    private String getStatus(){
        return status;
    }

    public void setReservasjonid(int reservasjonid){
        this.reservasjonid = reservasjonid;
    }

    public void setKundeid(int kundeid){
        this.kundeid = kundeid;
    }

    public void setRomid(int romid){
        this.romid = romid;
    }

    public void setStartdato(String startdato){
        this.startdato = startdato;
    }

    public void setSluttdato(String sluttdato){
        this.sluttdato = sluttdato;
    }

    public void setStatus(String status){
        this.status = status;
    }
}


