package com.coky.app.loaders;

/**
 * Created by Čoky on 11.11.2016..
 */

public interface WsDataLoadedListener {
    void onWsDataLoaded(String message, String status, boolean opSuccessful);
}
