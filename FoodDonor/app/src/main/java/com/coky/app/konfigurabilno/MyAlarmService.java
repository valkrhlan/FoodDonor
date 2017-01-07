package com.coky.app.konfigurabilno;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Valentina on 30.12.2016..
 */

public class MyAlarmService extends Service {
    //klasa za postavljanje alarma prilikom rebootanja uređaja

    Alarm alarm=new Alarm();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        alarm.setAlarm(this);
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        alarm.setAlarm(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
