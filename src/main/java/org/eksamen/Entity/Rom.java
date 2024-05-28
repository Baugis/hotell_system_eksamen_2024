package org.eksamen.Entity;

public class Rom {

    private int romid;

    private int romnummer;

    private String romtype;

    private float pris;

    public Rom(int romid, int romnummer, String romtype, float pris){
        this.romid = romid;
        this.romnummer = romnummer;
        this.romtype = romtype;
        this.pris = pris;
    }

    private int getRomid(){
        return romid;
    }

    private int getRomnummer(){
        return romnummer;
    }

    private String getRomtype(){
        return romtype;
    }

    private float getPris(){
        return pris;
    }

    public void setRomid(int romid){
        this.romid = romid;
    }

    public void setRomnummer(int romnummer){
        this.romnummer = romnummer;
    }

    public void setRomtype(String romtype){
        this.romtype = romtype;
    }

    public void setPris(float pris){
        this.pris = pris;
    }
}


