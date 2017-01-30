package com.coky.app.konfigurabilno;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.coky.app.klase.UpraviteljNotifikacija;
import com.coky.app.loaders.NotifikacijaLoadedListener;
import com.coky.app.loaders.SlanjePodatakaModulima;
import com.coky.core.entities.Notifikacija;

/**
 * Created by Valentina on 27.1.2017..
 */

public class KonfigurabilnoListener implements SlanjePodatakaModulima {

    private Alarm alarm=new Alarm();
    private Context mContext;
    NotifikacijaLoadedListener notifikacijaLoadedListener;

    public KonfigurabilnoListener(Context mContext,NotifikacijaLoadedListener notifikacijaLoadedListener) {
        this.notifikacijaLoadedListener = notifikacijaLoadedListener;
        this.mContext=mContext;
    }
    public KonfigurabilnoListener(Context mContext){
        this.notifikacijaLoadedListener=new UpraviteljNotifikacija();
    }
    public void pozoviWS(Context mContext){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(mContext);
        String email=preferences.getString("emailKorisnika",null);
        Long timestamp=preferences.getLong("timestamp",-1);
        if(email!=null && timestamp!=-1){
            notifikacijaLoadedListener.pozoviWS("dohvatiNotifikacije",email,timestamp.toString());
            SharedPreferences.Editor  editor=preferences.edit();
            long ts=System.currentTimeMillis()/1000;
            editor.putLong("timestamp",ts);
            editor.apply();
        }
    }


    @Override
    public void obradiPromjenu(Context mContex, String opcija,String prethodnaOpcija, int interval) {

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(mContex);

        if (opcija.equals("Konfigurabilno")){
            SharedPreferences.Editor editor=preferences.edit();
            if(opcija!=prethodnaOpcija){
                editor.putInt("alarm_ukljucen", 1);
                alarm.setAlarm(mContex);
            }else{
                editor.putInt("alarm_ukljucen", 0);
            }
            editor.apply();

        }else{
            if(prethodnaOpcija.equals("Konfigurabilno")){
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("alarm_ukljucen",0);
                editor.apply();
                alarm.cancelAlarm(mContex);
            }

        }
    }

    @Override
    public void dostaviPodatkeWS(Context mContext,Object data,NotifikacijaLoadedListener notifikacijaLoadedListener) {
         this.notifikacijaLoadedListener=notifikacijaLoadedListener;
        if(data instanceof Notifikacija[]){
             String test="test";
            Notifikacija[] notifikacije=(Notifikacija[]) data;
            for (Notifikacija notifikacija:notifikacije){
                notifikacijaLoadedListener.onNotifikacijaLoaded(notifikacija.getTitle(),notifikacija.getMessage(),android.R.drawable.ic_dialog_email);

            }
        }


    }
}
