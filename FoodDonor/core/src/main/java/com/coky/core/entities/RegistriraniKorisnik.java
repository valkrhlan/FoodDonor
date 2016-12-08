package com.coky.core.entities;

/**
 * Created by Čoky on 8.12.2016..
 */

public class RegistriraniKorisnik {
    private String email;
    private String lozinka;

    public RegistriraniKorisnik(String email, String lozinka) {
        this.email = email;
        this.lozinka = lozinka;
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
}
