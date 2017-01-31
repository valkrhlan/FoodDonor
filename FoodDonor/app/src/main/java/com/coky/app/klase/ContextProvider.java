package com.coky.app.klase;

import android.app.Application;
import android.content.Context;

/**
 * Created by Valentina on 27.1.2017..
 *
 * klasa koja služi za dohvaćanje konteksta aplikacije
 *
 */

public class ContextProvider extends Application{
    private static Context mContext;
    public void onCreate(){
        super.onCreate();
        mContext=getApplicationContext();
    }
    public static Context dohvatiContext(){
        return mContext;
    }
}
