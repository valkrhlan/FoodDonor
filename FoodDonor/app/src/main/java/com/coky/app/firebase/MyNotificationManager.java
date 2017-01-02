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

import com.coky.app.R;
import com.coky.app.klase.UpraviteljNotifikacija;

/**
 * Created by Valentina on 22.12.2016..
 */

public class MyNotificationManager {
    public static final int ID_SMALL_NOTIFICATION = 235;
    private Context mCtx;
    public MyNotificationManager(Context mCtx) {

        this.mCtx = mCtx;
    }

    public void showSmallNotification(String title, String message, Intent intent){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
        String notifikacije=prefs.getString("notifikacije",null);
        if(notifikacije==null || notifikacije.equals("Firebase")) { //po defaultu nek bude firebase
            UpraviteljNotifikacija upraviteljNotifikacija = new UpraviteljNotifikacija();
            upraviteljNotifikacija.onNotifikacijaLoaded(title, message, android.R.drawable.ic_menu_delete, intent, mCtx);
        }

    }

}
