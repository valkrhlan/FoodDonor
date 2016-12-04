package com.coky.core.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentina on 3.12.2016..
 */

public class VrstaJedinica {

    @SerializedName("vrsta")
    public List<SpinnerElement> vrsta;
    @SerializedName("jedinica")
    public List<SpinnerElement> jedinica;

    public VrstaJedinica(List<SpinnerElement> vrsta, List<SpinnerElement> jedinica) {
        this.vrsta = vrsta;
        this.jedinica = jedinica;
    }

    public List<SpinnerElement> getVrsta() {
        return vrsta;
    }

    public void setVrsta(ArrayList<SpinnerElement> vrsta) {
        this.vrsta = vrsta;
    }

    public List<SpinnerElement> getJedinica() {
        return jedinica;
    }

    public void setJedinica(ArrayList<SpinnerElement> jedinica) {
        this.jedinica = jedinica;
    }
}
