package com.coky.webservice;

/**
 * Created by Čoky on 10.11.2016..
 */

public interface FdWebServiceHandler {
    void onDataArrived(String message, int status, boolean ok);
}
