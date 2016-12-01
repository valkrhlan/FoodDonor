package com.coky.core.entities;

/**
 * Created by Čoky on 9.11.2016..
 */

public abstract class Korisnik {
    public String metoda;
    public String email;
    public String lozinka;
    public String oib;
    public String grad;
    public String adresa;
    public String kontakt;

    public Korisnik(String metoda, String email, String lozinka, String oib, String grad, String adresa, String kontakt) {
        this.metoda = metoda;
        this.email = email;
        this.lozinka = lozinka;
        this.oib = oib;
        this.grad = grad;
        this.adresa = adresa;
        this.kontakt = kontakt;
    }

    public String getMetoda() {
        return metoda;
    }

    public void setMetoda(String metoda) {
        this.metoda = metoda;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }
}
