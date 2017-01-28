package com.coky.app.konfigurabilno;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.sip.SipAudioCall;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.coky.app.NotifikacijeOpcije;
import com.coky.app.R;
import com.coky.app.klase.UpraviteljNotifikacija;
import com.coky.app.loaders.NotifikacijaLoadedListener;

/**
 * Created by Valentina on 30.12.2016..
 */

public class Alarm extends BroadcastReceiver{
    private Context mCtx;
    private Intent intent;

    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager pm =(PowerManager)context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"My Tag");
        wl.acquire();

        mCtx=context;
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        String notifikacije=preferences.getString("notifikacije","prazno");
       // Toast.makeText(context,"Alarm u OnReceivu!!!!!!!"+notifikacije,Toast.LENGTH_SHORT).show();

        if(notifikacije.equals("Konfigurabilno")){
            SharedPreferences preferences2= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            String notifikacije2=preferences2.getString("emailKorisnika","prazno");

            Toast.makeText(context,"Alarm u equals Konfigurabilno!!!!!!!"+notifikacije2,Toast.LENGTH_SHORT).show();
            //to do for ws
        }
        else {
           // Toast.makeText(context,"Cancel Alarm u OnReceivu!!!!!!!",Toast.LENGTH_SHORT).show();
            cancelAlarm(context);
        }
            wl.release();

    }

    public void setAlarm(Context context){
        mCtx=context;
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context,Alarm.class);
        intent=i;

        PendingIntent pi=PendingIntent.getBroadcast(context,0,i,0);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        String notifikacije=preferences.getString("notifikacije",null);

        if(notifikacije!=null && notifikacije.equals("Konfigurabilno")){
            int interval=preferences.getInt("interval",-1);
            if(interval!=-1){
                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),1000*interval,pi);//millisec*sec*minute //sredi da budi pravi interval
            }
        }
        else {
            cancelAlarm(context);
        }

    }

    public void cancelAlarm(Context context){
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i =new Intent(context,Alarm.class);
        PendingIntent sender=PendingIntent.getBroadcast(context,0,i,0);
        alarmManager.cancel(sender);
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("alarm_ukljucen", 0);
        editor.apply();
    }


}
