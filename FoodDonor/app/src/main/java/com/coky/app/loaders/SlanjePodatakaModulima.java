package com.coky.app.loaders;

import android.content.Context;

/**
 * Created by Valentina on 27.1.2017..
 */

public interface SlanjePodatakaModulima {
    void obradiPromjenu(Context mContex, String opcija,String prethodnaOpcija, int interval);
    void dostaviPodatkeWS(Context mContext,Object data,NotifikacijaLoadedListener notifikacijaLoadedListener);

}
