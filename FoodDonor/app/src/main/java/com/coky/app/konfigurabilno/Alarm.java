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

/**
 * Created by Valentina on 30.12.2016..
 */

public class Alarm extends BroadcastReceiver implements KonfigurabilnoPromjernaOpcijaLoadedListener{
    Context mCtx;
    Intent intent;
    private static boolean postavljenAlarm=false;

    /*
    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener(){

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Toast.makeText(mCtx,"Promjena shared prefs!!!!!!!",Toast.LENGTH_SHORT).show();

        }
    };
  */
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm =(PowerManager)context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"My Tag");
        wl.acquire();


        mCtx=context;
     /*   SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
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
        */
        Toast.makeText(context,"Alarm!!!!!!!",Toast.LENGTH_SHORT).show();
        wl.release();
    }

    public void setAlarm(Context context){
        mCtx=context;
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i =new Intent(context,Alarm.class);
        intent=i;
        postavljenAlarm=true;
        PendingIntent pi=PendingIntent.getBroadcast(context,0,intent,0);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        String notifikacije=preferences.getString("notifikacije",null);
        if(notifikacije!=null && notifikacije.equals("Konfigurabilno")){
            int interval=preferences.getInt("interval",-1);
            if(interval!=-1){
                am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),1000*10,pi);//millisec*sec*minute //sredi da budi pravi interval
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

    @Override
    public void onPromjenaLoaded(Context mContex, String opcija, int interval) {
        //Toast.makeText(mContex,"onPromjenaLoaded",Toast.LENGTH_SHORT).show();
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(mContex);

        Integer ukljucen=preferences.getInt("alarm_ukljucen",-1);
        if (opcija.equals("Konfigurabilno")){
          if(ukljucen==-1 || ukljucen==0){
              SharedPreferences.Editor editor = preferences.edit();
              editor.putInt("alarm_ukljucen", 1);
              editor.apply();
              setAlarm(mContex);

          }
        }else{
            if(ukljucen==1){
                Toast.makeText(mContex,"cancel alarm",Toast.LENGTH_SHORT).show();
                cancelAlarm(mContex);

            }
        }
    }

}
