package com.coky.webservice;

import com.coky.core.entities.SpinnerElement;
import com.coky.core.entities.VrstaJedinica;

import java.util.List;

/**
 * Created by Čoky on 10.11.2016..
 */

public interface FdWebServiceHandler {
    void onDataArrived(String message, int status, boolean ok);
    void onDataArrived(VrstaJedinica data, int status, boolean ok);
}
