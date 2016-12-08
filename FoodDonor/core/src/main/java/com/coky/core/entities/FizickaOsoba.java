package com.coky.core.entities;

/**
 * Created by Čoky on 9.11.2016..
 */

public class FizickaOsoba extends Korisnik {
    private String ime;
    private String prezime;

    public FizickaOsoba(String metoda, String email, String lozinka, String oib, String grad, String adresa, String kontakt, String ime, String prezime) {
        super(metoda, email, lozinka, oib, grad, adresa, kontakt);
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
