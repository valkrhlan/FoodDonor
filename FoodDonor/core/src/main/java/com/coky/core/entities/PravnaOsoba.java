package com.coky.core.entities;

/**
 * Created by Čoky on 9.11.2016..
 */

public class PravnaOsoba extends Korisnik {
    public String naziv;
    public String tip;

    public PravnaOsoba(String metoda, String email, String lozinka, String oib, String grad, String adresa, String kontakt, String naziv, String tip) {
        super(metoda, email, lozinka, oib, grad, adresa, kontakt);
        this.naziv = naziv;
        this.tip = tip;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}