package com.coky.app.konfigurabilno;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Valentina on 30.12.2016..
 */

public class AutoStart extends BroadcastReceiver {

    Alarm alarm=new Alarm();
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){

            SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
            String notifikacije=preferences.getString("notifikacije",null);
            if(notifikacije.equals("Konfigurabilno")){
                alarm.setAlarm(context.getApplicationContext());
            }
        }
    }
}
