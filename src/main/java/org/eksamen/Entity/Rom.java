package org.eksamen.Entity;

// Rom er opprettet av kandidatnummer 7041
public class Rom {
    private int romid;
    private int romnummer;
    private String romtype;
    private float pris;

    // Oppretter konstruktør
    public Rom(int romid, int romnummer, String romtype, float pris){
        this.romid = romid;
        this.romnummer = romnummer;
        this.romtype = romtype;
        this.pris = pris;
    }

    // Oppretter gettere
    public int getRomid(){
        return romid;
    }

    public int getRomnummer(){
        return romnummer;
    }

    public String getRomtype(){
        return romtype;
    }

    public float getPris(){
        return pris;
    }

    // Oppretter settere
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

    @Override
    public String toString() {
        return "Rom{" +
                "romid=" + romid +
                ", romnummer='" + romnummer + '\'' +
                ", romtype='" + romtype + '\'' +
                ", pris=" + pris +
                '}';
    }
}


