package com.coky.app.loaders;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;

/**
 * Created by Valentina on 2.1.2017..
 */

public interface NotifikacijaLoadedListener {
    void onNotifikacijaLoaded(String title, String message, int slika, Intent intent, Context mCtx);
}
