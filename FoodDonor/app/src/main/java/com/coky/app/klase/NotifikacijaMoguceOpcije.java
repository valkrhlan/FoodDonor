package com.coky.app.klase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentina on 30.12.2016..
 */

public class NotifikacijaMoguceOpcije {
    public List<ItemNotifikacijaMoguceOpcije> moguceOpcije= new ArrayList<>();



    public NotifikacijaMoguceOpcije() {
        moguceOpcije.add(new ItemNotifikacijaMoguceOpcije("Firebase","firebaseOption"));
        moguceOpcije.add(new ItemNotifikacijaMoguceOpcije("Konfigurabilno","konfigurabilnoOption"));
    }

    public int size(){
        return moguceOpcije.size();
    }
    public void setMoguceOpcije(ArrayList<ItemNotifikacijaMoguceOpcije> moguceOpcije) {
        this.moguceOpcije = moguceOpcije;
    }

    public List<ItemNotifikacijaMoguceOpcije> getMoguceOpcije() {
        return moguceOpcije;
    }
}
