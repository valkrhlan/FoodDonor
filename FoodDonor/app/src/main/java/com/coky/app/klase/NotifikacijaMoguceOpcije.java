package com.coky.app.klase;

import android.content.Context;

import com.coky.app.firebase.MyNotificationManager;
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
    //private Context mContext;


    public NotifikacijaMoguceOpcije() {
      //  this.mContext=mContext;
        moguceOpcije.add(new ItemNotifikacijaMoguceOpcije("Firebase",Arrays.asList(0)));
        moguceOpcije.add(new ItemNotifikacijaMoguceOpcije("Konfigurabilno",Arrays.asList(10,20,30)));

    }


    public Integer size(){
        return moguceOpcije.size();
    }


    public String getOpcija(Integer i){

        return moguceOpcije.get(i).opcija;
    }

    public List<Integer> getInterval(Integer i){
        return moguceOpcije.get(i).intervali;
    }
    public List<Integer> getInterval(String option){
        int i=0;
        boolean nadjen=false;
        while (nadjen==false){
            if(moguceOpcije.get(i).opcija.equals(option)){
                nadjen=true;

            }
        }
        return moguceOpcije.get(i).intervali;
    }


    public List<ItemNotifikacijaMoguceOpcije> getMoguceOpcije() {
        return moguceOpcije;
    }

    public void setMoguceOpcije(List<ItemNotifikacijaMoguceOpcije> moguceOpcije) {
        this.moguceOpcije = moguceOpcije;
    }

   /* public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
    */
}
