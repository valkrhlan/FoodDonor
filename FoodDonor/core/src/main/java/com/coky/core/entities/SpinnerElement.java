package com.coky.core.entities;

/**
 * Created by Valentina on 2.12.2016..
 */

public class SpinnerElement {
    private String id;
    private String naziv;

    public SpinnerElement(String id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public SpinnerElement() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
