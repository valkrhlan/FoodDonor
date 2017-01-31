package com.coky.app.klase;


import java.util.List;

/**
 * Created by Valentina on 30.12.2016..
 *
 * klasa koja predstavlja pojedinu mogućnost u listi svih mogućnosti u klasi NotifikacijaMOgućeOpcije
 *
 */

public class ItemNotifikacijaMoguceOpcije {

    public String opcija;

    /**
     *
     * @param intervali
     *
     * U slučaju da modul ne treba interval, postavlja se njegova vrijednost na 0
     *
     */
    public List<Integer> intervali;
    public Object modul;


    public ItemNotifikacijaMoguceOpcije(String opcija, List<Integer> intervali,Object modul) {
        this.opcija = opcija;
        this.intervali = intervali;
        this.modul = modul;
    }

    public Object getModul() {
        return modul;
    }

    public void setModul(Object modul) {
        this.modul = modul;
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


}
