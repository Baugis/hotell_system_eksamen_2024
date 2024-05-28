package org.eksamen.Entity;

import java.time.LocalDateTime;

public class Reservasjoner {


    private int reservasjonid;

    private int kundeid;

    private int romid;

    private LocalDateTime startdato;

    private LocalDateTime sluttdato;

    private String status;

    public Reservasjoner(int reservasjonid, int kundeid, int romid, LocalDateTime startdato, LocalDateTime sluttdato, String status){
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

    private LocalDateTime getStartdato(){
        return startdato;
    }

    private LocalDateTime getSluttdato(){
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

    public void setStartdato(LocalDateTime startdato){
        this.startdato = startdato;
    }

    public void setSluttdato(LocalDateTime sluttdato){
        this.sluttdato = sluttdato;
    }

    public void setStatus(String status){
        this.status = status;
    }
}


