package com.coky.core.entities;

/**
 * Created by Čoky on 1.12.2016..
 */

public class Paket {


    private String id;
    private String preuzimanje;
    private String hitno;
    private String id_volonter;
    private String naziv_volonter;
    private String id_donor;
    private String naziv_donor;
    private String id_potrebitog;
    private String naziv_potrebitog;
    //private String krivo1; //preuzimanje
    private String v_kreiranja;
    private String v_naruceno;
    //private String krivo2; //v_naruceno
    private String v_preuzeto;
    private String v_slanja;
    private String v_pristiglo;
    private Object stavke;

    public Paket(String id, String preuzimanje, String hitno, String id_volonter,String naziv_volonter, String id_donor,String naziv_donor, String id_potrebitog, String naziv_potrebitog, String v_kreiranja, String v_naruceno, String v_preuzeto, String v_slanja, String v_pristiglo, Object stavke) {
        this.id = id;
        this.preuzimanje = preuzimanje;
        this.hitno = hitno;
        this.id_volonter = id_volonter;
        this.naziv_volonter=naziv_volonter;
        this.id_donor = id_donor;
        this.naziv_donor=naziv_donor;
        this.id_potrebitog = id_potrebitog;
        this.naziv_potrebitog=naziv_potrebitog;
       // this.krivo1 = krivo1;
        this.v_kreiranja = v_kreiranja;
        this.v_naruceno = v_naruceno;
       // this.krivo2 = krivo2;
        this.v_preuzeto = v_preuzeto;
        this.v_slanja = v_slanja;
        this.v_pristiglo = v_pristiglo;
        this.stavke = stavke;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreuzimanje() {
        return preuzimanje;
    }

    public void setPreuzimanje(String preuzimanje) {
        this.preuzimanje = preuzimanje;
    }

    public String getHitno() {
        return hitno;
    }

    public void setHitno(String hitno) {
        this.hitno = hitno;
    }

    public String getId_volonter() {
        return id_volonter;
    }

    public void setId_volonter(String id_volonter) {
        this.id_volonter = id_volonter;
    }

    public String getId_donor() {
        return id_donor;
    }

    public void setId_donor(String id_donor) {
        this.id_donor = id_donor;
    }

    public String getId_potrebitog() {
        return id_potrebitog;
    }

    public void setId_potrebitog(String id_potrebitog) {
        this.id_potrebitog = id_potrebitog;
    }


    public String getV_kreiranja() {
        return v_kreiranja;
    }

    public void setV_kreiranja(String v_kreiranja) {
        this.v_kreiranja = v_kreiranja;
    }

    public String getV_naruceno() {
        return v_naruceno;
    }

    public void setV_naruceno(String v_naruceno) {
        this.v_naruceno = v_naruceno;
    }


    public String getV_preuzeto() {
        return v_preuzeto;
    }

    public void setV_preuzeto(String v_preuzeto) {
        this.v_preuzeto = v_preuzeto;
    }

    public String getV_slanja() {
        return v_slanja;
    }

    public void setV_slanja(String v_slanja) {
        this.v_slanja = v_slanja;
    }

    public String getV_pristiglo() {
        return v_pristiglo;
    }

    public void setV_pristiglo(String v_pristiglo) {
        this.v_pristiglo = v_pristiglo;
    }

    public Object getStavke() {
        return stavke;
    }

    public void setStavke(Object stavke) {
        this.stavke = stavke;
    }

    public String getNaziv_volonter() {
        return naziv_volonter;
    }

    public void setNaziv_volonter(String naziv_volonter) {
        this.naziv_volonter = naziv_volonter;
    }

    public String getNaziv_donor() {
        return naziv_donor;
    }

    public void setNaziv_donor(String naziv_donor) {
        this.naziv_donor = naziv_donor;
    }

    public String getNaziv_potrebitog() {
        return naziv_potrebitog;
    }

    public void setNaziv_potrebitog(String naziv_potrebitog) {
        this.naziv_potrebitog = naziv_potrebitog;
    }
}
