package com.coky.app.klase;

import com.coky.core.entities.SpinnerElement;

/**
 * Created by Valentina on 6.12.2016..
 */

public class StavkaPaketa {
    String naziv;
    SpinnerElement vrsta;
    String kolicina;
    SpinnerElement jedinica;

    public StavkaPaketa(String naziv, SpinnerElement vrsta, String kolicina, SpinnerElement jedinica) {
        this.naziv = naziv;
        this.vrsta = vrsta;
        this.kolicina = kolicina;
        this.jedinica = jedinica;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public SpinnerElement getVrsta() {
        return vrsta;
    }

    public void setVrsta(SpinnerElement vrsta) {
        this.vrsta = vrsta;
    }

    public String getKolicina() {
        return kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }

    public SpinnerElement getJedinica() {
        return jedinica;
    }

    public void setJedinica(SpinnerElement jedinica) {
        this.jedinica = jedinica;
    }

}
