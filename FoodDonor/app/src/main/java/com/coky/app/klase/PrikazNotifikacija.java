package com.coky.app.klase;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.coky.app.Prijava;

/**
 * Created by Valentina on 30.1.2017..
 */

public class PrikazNotifikacija {

    final static String GROUP_KEY_EMAILS = "food_donor_notifications";
    public PrikazNotifikacija() {

    }
    public void prikaziNotifikaciju(String title, String message, int ikona,Context mCtx){

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(mCtx);
        int notifId=preferences.getInt("notifId",100);
        Intent intent = new Intent(mCtx, Prijava.class);
        PendingIntent resultPendingIntent= PendingIntent.getActivity(
                mCtx,
                notifId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mCtx);
        Notification notification;
        notification=mBuilder.setSmallIcon(android.R.drawable.ic_menu_delete).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setContentTitle(title)
                .setSmallIcon(ikona)
                .setContentText(message)
                .setGroup(GROUP_KEY_EMAILS)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        android.app.NotificationManager notificationManager = (android.app.NotificationManager)mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifId,notification);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("notifId",notifId+1);
        editor.apply();
    }
}
