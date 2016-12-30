package com.coky.app.konfigurabilno;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;

/**
 * Created by Valentina on 30.12.2016..
 */

public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm =(PowerManager)context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"My Tag");
        wl.acquire();

        //Put here YOUR code
        Toast.makeText(context,"Alarm!!!!!!!",Toast.LENGTH_SHORT).show();
        wl.release();
    }

    public void setAlarm(Context context){
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i =new Intent(context,Alarm.class);
        PendingIntent pi=PendingIntent.getBroadcast(context,0,i,0);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),1000*60,pi);//millisec*sec*minute
    }

    public void cancelAlarm(Context context){
        Intent intent = new Intent(context,Alarm.class);
        PendingIntent sender=PendingIntent.getBroadcast(context,0,intent,0);
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
