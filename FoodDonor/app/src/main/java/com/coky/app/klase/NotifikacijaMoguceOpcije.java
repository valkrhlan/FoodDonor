package com.coky.app.klase;

import android.content.Context;

import com.coky.app.konfigurabilno.Alarm;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Valentina on 30.12.2016..
 */

public class NotifikacijaMoguceOpcije {
    /*
    klasa koja će nakom implementacije ofigurabilnog načina slanja obavijesti
    definirati izgled mogućih načina odabira priakza notifikacija, bilo konfigurabilo, pomoću firebae ili nešto treće
     */

    public List<ItemNotifikacijaMoguceOpcije> moguceOpcije= new ArrayList<>();
    public Context mContext;


    public NotifikacijaMoguceOpcije(Context mContext) {
        this.mContext=mContext;
       // Alarm alarm=new Alarm();

      //  moguceOpcije.add(new ItemNotifikacijaMoguceOpcije("Firebase",Arrays.asList(0),new Object())); //new Object zamijeniti sa stvarnom vrijednosti
      //  moguceOpcije.add(new ItemNotifikacijaMoguceOpcije("Konfigurabilno",Arrays.asList(10,20,30),alarm));

        moguceOpcije.add(new ItemNotifikacijaMoguceOpcije("Firebase",Arrays.asList(0))); //new Object zamijeniti sa stvarnom vrijednosti
        moguceOpcije.add(new ItemNotifikacijaMoguceOpcije("Konfigurabilno",Arrays.asList(10,20,30)));

    }

    public Integer size(){
        return moguceOpcije.size();
    }
    public String getOpcija(Integer i){
        return moguceOpcije.get(i).opcija;
    }
    /*public Object getListener(Integer i){
        return moguceOpcije.get(i).getListener();
    }*/

    public List<ItemNotifikacijaMoguceOpcije> getMoguceOpcije() {
        return moguceOpcije;
    }

    public void setMoguceOpcije(List<ItemNotifikacijaMoguceOpcije> moguceOpcije) {
        this.moguceOpcije = moguceOpcije;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
