package com.coky.app.klase;

import java.util.List;

/**
 * Created by Valentina on 30.12.2016..
 */

public class ItemNotifikacijaMoguceOpcije {
    /*
    klasa koja predstavlja pojedinu mogućnost u listi svih mogućnosti u klasi NotifikacijaMOgućeOpcije
     */

    public String opcija;
    public List<Integer> intervali;
   // public Object listener;

    public ItemNotifikacijaMoguceOpcije(String opcija, List<Integer> intervali ) {
        this.opcija = opcija;
        this.intervali = intervali;
        //this.listener = listener;
    }


    public String getOpcija() {
        return opcija;
    }

    public void setOpcija(String opcija) {
        this.opcija = opcija;
    }

    public List<Integer> getIntervali() {
        return intervali;
    }

    public void setIntervali(List<Integer> intervali) {
        this.intervali = intervali;
    }

   /* public Object getListener() {
        return listener;
    }

    public void setListener(Object listener) {
        this.listener = listener;
    }
    */
}
