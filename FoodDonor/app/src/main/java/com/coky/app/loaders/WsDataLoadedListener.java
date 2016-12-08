package com.coky.app.loaders;

import com.coky.core.entities.SpinnerElement;
import com.coky.core.entities.VrstaJedinica;

import java.util.List;

/**
 * Created by Čoky on 11.11.2016..
 */

public interface WsDataLoadedListener {
    void onWsDataLoaded(Object message, int tip);
}
