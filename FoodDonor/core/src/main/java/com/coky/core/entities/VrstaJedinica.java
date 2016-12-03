package com.coky.core.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentina on 3.12.2016..
 */

public class VrstaJedinica {
    public List<SpinnerElement> vrsta;
    public List<SpinnerElement> jedinica;

    public VrstaJedinica(List<SpinnerElement> vrsta, List<SpinnerElement> jedinica) {
        this.vrsta = vrsta;
        this.jedinica = jedinica;
    }

    public List<SpinnerElement> getVrsta() {
        return vrsta;
    }

    public void setVrsta(List<SpinnerElement> vrsta) {
        this.vrsta = vrsta;
    }

    public List<SpinnerElement> getJedinica() {
        return jedinica;
    }

    public void setJedinica(List<SpinnerElement> jedinica) {
        this.jedinica = jedinica;
    }
}
