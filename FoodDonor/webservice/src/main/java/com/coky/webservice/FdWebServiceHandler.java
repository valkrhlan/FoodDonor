package com.coky.webservice;

import com.coky.core.entities.SpinnerElement;
import com.coky.core.entities.VrstaJedinica;

import java.util.List;

/**
 * Created by Čoky on 10.11.2016..
 */

public interface FdWebServiceHandler {
    void onDataArrived(Object message, int status);
}
