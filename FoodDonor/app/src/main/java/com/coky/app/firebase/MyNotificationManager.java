package com.coky.app.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.coky.app.R;
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
    }

    public void showSmallNotification(String title, String message, Intent intent){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
        String notifikacije=prefs.getString("notifikacije",null);
        if(notifikacije==null || notifikacije.equals("Firebase")) {
            UpraviteljNotifikacija upraviteljNotifikacija = new UpraviteljNotifikacija();
            upraviteljNotifikacija.onNotifikacijaLoaded(title, message, android.R.drawable.ic_menu_delete);
        }

    }

    @Override
    public void obradiPromjenu(Context mContex, String opcija,String prethodnaOpcija, int interval) {

        //to do: obrisi ili postavi token
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(mContex);
        Integer ukljucen=preferences.getInt("alarm_ukljucen",-1);
        String prethodniOdabir=preferences.getString("notifikacije",null);


        Toast.makeText(mContex,"Firebase obradi promjenu",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void dostaviPodatkeWS(Object data) {

    }
}
