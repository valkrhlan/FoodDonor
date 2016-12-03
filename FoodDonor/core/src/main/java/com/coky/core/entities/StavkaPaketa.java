package com.coky.core.entities;

/**
 * Created by Valentina on 2.12.2016..
 */

public class StavkaPaketa {
    public int id;
    public String naziv;
    public int kolicina;
    public String jedinica;
    public SpinnerElement vrsta;

    public StavkaPaketa(int id, String naziv, int kolicina, String jedinica, SpinnerElement vrsta) {
        this.id = id;
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.jedinica = jedinica;
        this.vrsta = vrsta;
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

    public String getJedinica() {
        return jedinica;
    }

    public void setJedinica(String jedinica) {
        this.jedinica = jedinica;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public SpinnerElement getVrsta() {
        return vrsta;
    }

    public void setVrsta(SpinnerElement vrsta) {
        this.vrsta = vrsta;
    }
}
