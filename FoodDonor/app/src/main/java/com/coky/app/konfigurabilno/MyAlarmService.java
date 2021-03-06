package com.coky.app.konfigurabilno;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Valentina on 30.12.2016..
 * klasa za postavljanje alarma prilikom rebootanja uređaja
 *
 */

public class MyAlarmService extends Service {

    Alarm alarm=new Alarm();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        alarm.setAlarm(this.getApplicationContext());
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {

        alarm.setAlarm(this.getApplicationContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
