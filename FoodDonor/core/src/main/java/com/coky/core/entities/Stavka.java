package com.coky.core.entities;

/**
 * Created by Čoky on 10.1.2017..
 */

public class Stavka {
    private String id;
    private String naziv;
    private String kolicina;
    private String id_vrsta;
    private String vrsta;
    private String id_jedinica;
    private String jedinica;

    public Stavka(String id, String naziv, String kolicina, String id_vrsta, String vrsta, String id_jedinica, String jedinica) {
        this.id = id;
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.id_vrsta = id_vrsta;
        this.vrsta = vrsta;
        this.id_jedinica = id_jedinica;
        this.jedinica = jedinica;
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

    public String getKolicina() {
        return kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }

    public String getId_vrsta() {
        return id_vrsta;
    }

    public void setId_vrsta(String id_vrsta) {
        this.id_vrsta = id_vrsta;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getId_jedinica() {
        return id_jedinica;
    }

    public void setId_jedinica(String id_jedinica) {
        this.id_jedinica = id_jedinica;
    }

    public String getJedinica() {
        return jedinica;
    }

    public void setJedinica(String jedinica) {
        this.jedinica = jedinica;
    }
}
