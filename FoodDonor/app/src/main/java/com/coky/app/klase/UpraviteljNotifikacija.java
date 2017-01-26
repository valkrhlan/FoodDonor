package com.coky.app.klase;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.coky.app.konfigurabilno.Alarm;
import com.coky.app.loaders.NotifikacijaLoadedListener;

import java.util.List;

/**
 * Created by Valentina on 2.1.2017..
 */

public class UpraviteljNotifikacija implements NotifikacijaLoadedListener {

    /*
      klasa u kojoj se definira izgled notifikacije koja se prikazue korisniku
     */
    public static final int ID_SMALL_NOTIFICATION = 235;

    @Override
    public void onNotifikacijaLoaded(String title, String message, int ikona, Intent intent,Context mCtx) {
        PendingIntent resultPendingIntent= PendingIntent.getActivity(
                mCtx,
                ID_SMALL_NOTIFICATION,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mCtx);
        Notification notification;
        notification=mBuilder.setSmallIcon(android.R.drawable.ic_menu_delete).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setContentTitle(title)
                .setSmallIcon(ikona) //               .setSmallIcon() //
                .setContentText(message)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

       android.app.NotificationManager notificationManager = (android.app.NotificationManager)mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
       notificationManager.notify(ID_SMALL_NOTIFICATION,notification);
    }
    public  void pohraniPromjene(Context mContex, String opcija, Integer interval){
        NotifikacijaMoguceOpcije notifikacijaMoguceOpcije= new NotifikacijaMoguceOpcije();

        for (int i=0; i<notifikacijaMoguceOpcije.size();i++){
            if(notifikacijaMoguceOpcije.getOpcija(i).equals("Konfigurabilno")){
                Alarm alarm=new Alarm();
                alarm.onPromjenaLoaded(mContex,opcija,10);

            }
        }
    }
}
