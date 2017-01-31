package com.coky.app.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.widget.Toast;

import com.coky.app.klase.UpraviteljNotifikacija;
import com.coky.app.loaders.NotifikacijaLoadedListener;
import com.coky.app.loaders.SlanjePodatakaModulima;

/**
 * Created by Valentina on 22.12.2016..
 */

public class MyNotificationManager implements SlanjePodatakaModulima{
    private Context mCtx;
    NotifikacijaLoadedListener notifikacijaLoadedListener;

    public MyNotificationManager(Context mCtx, NotifikacijaLoadedListener notifikacijaLoadedListener) {
        this.mCtx = mCtx;
        this.notifikacijaLoadedListener = notifikacijaLoadedListener;
    }

    public MyNotificationManager(Context mCtx) {
        this.mCtx = mCtx;
        this.notifikacijaLoadedListener=new UpraviteljNotifikacija();
    }

    /**
     * Metoda pomoću koje se upravitelja notifikacija obaviještava da je pristigla nova notifikacija koju treba prikazati
     * @param title naslov notifikacije
     * @param message poruka notifikacije
     * @param intent intent
     */
    public void showSmallNotification(String title, String message, Intent intent){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
        String notifikacije=prefs.getString("notifikacije",null);
        if(notifikacije==null || notifikacije.equals("Firebase")) {
             notifikacijaLoadedListener.onNotifikacijaLoaded(title, message, android.R.drawable.ic_menu_delete);
        }
    }

    /**
     *
     * @param mContex kontekst aplikacije
     * @param opcija string parametar koji predstavlja opciju koju je kroisnik odabrao za prikaz notifikacija
     * @param prethodnaOpcija string parametar koji predstavlja opciju koja je prethodno bila odabrana
     * @param interval interval opcije
     */
    @Override
    public void obradiPromjenu(Context mContex, String opcija,String prethodnaOpcija, int interval) {

        if(opcija.equals("Firebase")){

            if(prethodnaOpcija!="Firebase" || prethodnaOpcija!="prazno"){
                     posaljiPovratnuInformaciju(mContex,"dodajToken");
            }

        }else{
            if(prethodnaOpcija.equals("Firebase")){
                posaljiPovratnuInformaciju(mContex,"obrisiToken");
            }
        }

    }

    /**
     *
     * @param mContext kontekst aplikacije
     * @param data podatci koje su dohvaćeni sa web servisa
     * @param notifikacijaLoadedListener instanca listenera za komunikaciju s modulima
     */
    @Override
    public void dostaviPodatkeWS(Context mContext,Object data,NotifikacijaLoadedListener notifikacijaLoadedListener) {
        this.notifikacijaLoadedListener=notifikacijaLoadedListener;
        if(data instanceof String){
            Toast.makeText(mContext,data.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Metoda koja zahtijeva da se iz klase UpraviteljNotifikacija pozove neka metoda web servisa
     * @param mContext aplikacijski kontekst
     * @param opcija opcija koja označava koju metodu sa ws treba pozvati
     */
    private void posaljiPovratnuInformaciju(Context mContext,String opcija){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(mContext);
        String email=preferences.getString("emailKorisnika",null);
        String token= SharedPrefManager.getInstance(mContext).getDeviceToken();
        SharedPreferences.Editor  editor=preferences.edit();
        long ts=System.currentTimeMillis()/1000;
        editor.putLong("timestamp",ts);
        editor.apply();
        notifikacijaLoadedListener.pozoviWS(opcija,email,token);

    }
}
