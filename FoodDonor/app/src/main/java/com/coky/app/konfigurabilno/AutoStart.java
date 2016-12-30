package com.coky.app.konfigurabilno;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Valentina on 30.12.2016..
 */

public class AutoStart extends BroadcastReceiver {

    Alarm alarm=new Alarm();
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){

            //to do -> setiraj samo kad je odabrnao u izborniku

            alarm.setAlarm(context);
        }
    }
}
