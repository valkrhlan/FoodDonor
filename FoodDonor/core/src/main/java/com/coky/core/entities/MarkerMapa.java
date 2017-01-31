package com.coky.core.entities;

/**
 * Created by Aleksandar on 31.1.2017..
 */

public class MarkerMapa {

    private String latt;
    private String longt;
    private String naziv;

    public MarkerMapa(String latt, String longt, String naziv) {
        this.latt = latt;
        this.longt = longt;
        this.naziv = naziv;
    }

    public String getLatt() {
        return latt;
    }

    public void setLatt(String latt) {
        this.latt = latt;
    }

    public String getLongt() {
        return longt;
    }

    public void setLongt(String longt) {
        this.longt = longt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
