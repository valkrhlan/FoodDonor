package com.coky.core.entities;

/**
 * Created by Aleksandar on 19.1.2017..
 */

public class Gradovi {
    private String pbt;
    private String naziv;

    public Gradovi(String pbt, String naziv) {
        this.pbt = pbt;
        this.naziv = naziv;
    }

    public String getPbt() {
        return pbt;
    }

    public void setPbt(String pbt) {
        this.pbt = pbt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
