package org.eksamen.Entity;

public class Rom {

    private int romid;

    private String romnummer;

    private String romtype;

    private float pris;

    private int getRomid(){
        return romid;
    }

    private String getRomnummer(){
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

    public void setRomnummer(String romnummer){
        this.romnummer = romnummer;
    }

    public void setRomtype(String romtype){
        this.romtype = romtype;
    }

    public void setPris(float pris){
        this.pris = pris;
    }
}
