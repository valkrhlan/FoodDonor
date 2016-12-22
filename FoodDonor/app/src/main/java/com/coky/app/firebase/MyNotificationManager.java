package com.coky.app.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.coky.app.R;

/**
 * Created by Valentina on 22.12.2016..
 */

public class MyNotificationManager {

    public static final int ID_SMALL_NOTIFICATION = 235;

    private Context mCtx;
    public MyNotificationManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    public void showSmallNotification(String title, String message, Intent intent){
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
                    .setSmallIcon(android.R.drawable.ic_menu_delete)
                    .setContentText(message)
                    .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager)mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(ID_SMALL_NOTIFICATION,notification);
    }
}
