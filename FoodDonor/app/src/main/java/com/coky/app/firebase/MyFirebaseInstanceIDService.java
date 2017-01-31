package com.coky.app.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Valentina on 22.12.2016..
 * Klasa koja manipulira promjenom tokena
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

 private static final String TAG ="MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed token: "+ refreshedToken);
        storeToken(refreshedToken);
    }

    private void storeToken(String token) {
        SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(token);
    }
}
