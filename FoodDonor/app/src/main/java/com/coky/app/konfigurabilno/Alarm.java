package com.coky.app.konfigurabilno;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by Valentina on 30.12.2016..
 */

public class Alarm extends BroadcastReceiver {
    Context mCtx;
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm =(PowerManager)context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"My Tag");
        wl.acquire();

        //Put here YOUR code
        mCtx=context;
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        String notifikacije=preferences.getString("notifikacije",null);
        if(notifikacije!=null && notifikacije.equals("Konfigurabilno")){
            int interval=preferences.getInt("interval",-1);
            if(interval!=-1){
                Toast.makeText(context,"Alarm!!!!!!!",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            cancelAlarm(context);
        }
        wl.release();
    }

    public void setAlarm(Context context){
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i =new Intent(context,Alarm.class);
        PendingIntent pi=PendingIntent.getBroadcast(context,0,i,0);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        String notifikacije=preferences.getString("notifikacije",null);
        if(notifikacije!=null && notifikacije.equals("Konfigurabilno")){
            int interval=preferences.getInt("interval",-1);
            if(interval!=-1){
                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),1000*120,pi);//millisec*sec*minute
            }
        }
        else {
            cancelAlarm(context);
        }

    }

    public void cancelAlarm(Context context){
        Intent intent = new Intent(context,Alarm.class);
        PendingIntent sender=PendingIntent.getBroadcast(context,0,intent,0);
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
