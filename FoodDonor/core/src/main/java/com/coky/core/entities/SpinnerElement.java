package com.coky.core.entities;

/**
 * Created by Valentina on 2.12.2016..
 */

public class SpinnerElement {
    public int id;
    public String naziv;

    public SpinnerElement(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
