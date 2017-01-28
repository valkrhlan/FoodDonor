package com.coky.app.klase;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.coky.app.Prijava;
import com.coky.app.konfigurabilno.Alarm;
import com.coky.app.konfigurabilno.KonfigurabilnoListener;
import com.coky.app.loaders.NotifikacijaLoadedListener;
import com.coky.app.loaders.SlanjePodatakaModulima;

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
    public void onNotifikacijaLoaded(String title, String message, int ikona) {
        ContextProvider cp=new ContextProvider();
        Context mCtx=cp.dohvatiContext();
        Intent intent = new Intent(mCtx, Prijava.class);
        mCtx=cp.dohvatiContext();
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
                .setSmallIcon(ikona) //
                .setContentText(message)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

       android.app.NotificationManager notificationManager = (android.app.NotificationManager)mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
       notificationManager.notify(ID_SMALL_NOTIFICATION,notification);
    }
    public  void pohraniPromjene(Context mContex, String opcija,String prethodnaOpcija, Integer interval){
        NotifikacijaMoguceOpcije notifikacijaMoguceOpcije= new NotifikacijaMoguceOpcije();

        for (int i=0; i<notifikacijaMoguceOpcije.size();i++){
            if(notifikacijaMoguceOpcije.getOpcija(i).equals("Konfigurabilno")){
                SlanjePodatakaModulima konfigurabilnoListener=new KonfigurabilnoListener(mContex,this);
                konfigurabilnoListener.obradiPromjenu(mContex,opcija,prethodnaOpcija,10);

            }
        }
    }
}
