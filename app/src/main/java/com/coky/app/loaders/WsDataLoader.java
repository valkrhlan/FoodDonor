package com.coky.app.loaders;

import com.coky.webservice.FdWebServiceCaller;
import com.coky.webservice.FdWebServiceHandler;

import java.util.ArrayList;

/**
 * Created by Čoky on 10.11.2016..
 */

public class WsDataLoader {
    public void prijava(ArrayList<String> data){
        FdWebServiceCaller prijavaWs = new FdWebServiceCaller(prijavaHandler);
        prijavaWs.CallWs("prijava",data);
    }
    public void registracijaFizicka(ArrayList<String> data){
        FdWebServiceCaller regFizickaWs = new FdWebServiceCaller(regFizickaHandler);
                regFizickaWs.CallWs("prijava",data);
    }
    public void registracijaPravna(ArrayList<String> data){
        FdWebServiceCaller regPravnaWs = new FdWebServiceCaller(regPravnaHandler);
                regPravnaWs.CallWs("prijava",data);
    }

    FdWebServiceHandler prijavaHandler = new FdWebServiceHandler() {
        @Override
        public void onDataArrived(String message, boolean ok) {

        }
    };

    FdWebServiceHandler regFizickaHandler = new FdWebServiceHandler() {
        @Override
        public void onDataArrived(String message, boolean ok) {

        }
    };

    FdWebServiceHandler regPravnaHandler = new FdWebServiceHandler() {
        @Override
        public void onDataArrived(String message, boolean ok) {

        }
    };
}
