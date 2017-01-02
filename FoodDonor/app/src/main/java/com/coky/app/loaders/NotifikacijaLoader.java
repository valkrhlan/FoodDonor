package com.coky.app.loaders;

import android.graphics.drawable.Icon;

/**
 * Created by Valentina on 2.1.2017..
 */

public abstract class NotifikacijaLoader {

    protected NotifikacijaLoadedListener mNotifikacijaLoadedListener;

    public void loadNotifikacija(NotifikacijaLoadedListener notifikacijaLoadedListener){
        this.mNotifikacijaLoadedListener=notifikacijaLoadedListener;
    }

}
