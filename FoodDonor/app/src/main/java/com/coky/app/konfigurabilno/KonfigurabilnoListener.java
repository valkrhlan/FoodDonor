package com.coky.app.konfigurabilno;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.coky.app.Prijava;
import com.coky.app.klase.NotifikacijaMoguceOpcije;
import com.coky.app.loaders.NotifikacijaLoadedListener;
import com.coky.app.loaders.SlanjePodatakaModulima;

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

    @Override
    public void obradiPromjenu(Context mContex, String opcija, int interval) {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(mContex);
        Integer ukljucen=preferences.getInt("alarm_ukljucen",-1);
        if (opcija.equals("Konfigurabilno")){
            if(ukljucen==-1 || ukljucen==0){
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("alarm_ukljucen", 1);
                editor.apply();
                alarm.setAlarm(mContex);
            }
        }else{
            if(ukljucen==1){
                Toast.makeText(mContex,"cancel alarm",Toast.LENGTH_SHORT).show();
                alarm.cancelAlarm(mContex);

            }
        }
    }

    @Override
    public void dostaviPodatkeWS(Object data) {

    }
}
