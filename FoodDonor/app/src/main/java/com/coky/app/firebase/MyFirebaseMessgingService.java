package com.coky.app.firebase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Valentina on 22.12.2016..
 */

public class MyFirebaseMessgingService extends FirebaseMessagingService{
    private static final String TAG="MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }
}
