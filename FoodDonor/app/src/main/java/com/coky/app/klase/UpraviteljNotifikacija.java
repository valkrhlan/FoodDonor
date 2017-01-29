package com.coky.app.klase;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.coky.app.Prijava;
import com.coky.app.firebase.MyNotificationManager;
import com.coky.app.konfigurabilno.Alarm;
import com.coky.app.konfigurabilno.KonfigurabilnoListener;
import com.coky.app.loaders.NotifikacijaLoadedListener;
import com.coky.app.loaders.SlanjePodatakaModulima;
import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;

import java.util.List;

/**
 * Created by Valentina on 2.1.2017..
 */

public class UpraviteljNotifikacija implements NotifikacijaLoadedListener,WsDataLoadedListener {

    /*
      klasa u kojoj se definira izgled notifikacije koja se prikazue korisniku
     */
    public static final int ID_SMALL_NOTIFICATION = 235;
    public NotifikacijaMoguceOpcije notifikacijaMoguceOpcije;
    private ContextProvider cp;
    public UpraviteljNotifikacija() {
        cp=new ContextProvider();

        this.notifikacijaMoguceOpcije = new NotifikacijaMoguceOpcije(cp.dohvatiContext(),this);
    }

    @Override
    public void onNotifikacijaLoaded(String title, String message, int ikona) {

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
        for (int i=0; i<notifikacijaMoguceOpcije.size();i++){
              SlanjePodatakaModulima slanjePodatakaModulima=(SlanjePodatakaModulima)notifikacijaMoguceOpcije.getModul(i);
              slanjePodatakaModulima.obradiPromjenu(mContex,opcija,prethodnaOpcija,interval);
        }

    }

    @Override
    public void pozoviWS(String opcija, String param1, String param2) {
        WsDataLoader wsDataLoader = new WsDataLoader();
        switch (opcija){
            case "dodajToken":
                wsDataLoader.slanjeTokena(param1,param2,this);
                break;
            case "obrisiToken":
                wsDataLoader.brisanjeTokena(param1, this);
                break;
        }
    }

    @Override
    public void onWsDataLoaded(Object message, int tip) {
        for (int i=0; i<notifikacijaMoguceOpcije.size();i++){
            SlanjePodatakaModulima slanjePodatakaModulima=(SlanjePodatakaModulima)notifikacijaMoguceOpcije.getModul(i);
            slanjePodatakaModulima.dostaviPodatkeWS(cp.dohvatiContext(),message);
        }
    }
}
