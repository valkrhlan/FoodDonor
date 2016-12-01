package com.coky.core.entities;

/**
 * Created by Čoky on 1.12.2016..
 */

public class Paket {

    private long broj;
    private String datum, status;

    public Paket(long broj, String datum, String status) {
        this.broj = broj;
        this.datum = datum;
        this.status = status;
    }

    public long getBroj() {
        return broj;
    }

    public void setBroj(long broj) {
        this.broj = broj;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
