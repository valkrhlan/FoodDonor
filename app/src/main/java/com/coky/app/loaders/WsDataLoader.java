package com.coky.app.loaders;

import com.coky.webservice.FdWebServiceCaller;
import com.coky.webservice.FdWebServiceHandler;

import java.util.ArrayList;

/**
 * Created by Čoky on 10.11.2016..
 */

public class WsDataLoader {

    private String mMessage;
    private String mStatus;
    private Boolean opSuccessful;
    private Boolean checkCompleted = false;

    public Boolean getCheckCompleted() {
        return checkCompleted;
    }

    public String getmMessage() {
        return mMessage;
    }

    public String getmStatus() {
        return mStatus;
    }

    public Boolean getOpSuccessful() {
        return opSuccessful;
    }

    public void prijava(ArrayList<String> data){
        FdWebServiceCaller prijavaWs = new FdWebServiceCaller(responseHandler);
        prijavaWs.CallWs("prijava",data);
    }
    public void registracijaFizicka(ArrayList<String> data){
        FdWebServiceCaller regFizickaWs = new FdWebServiceCaller(responseHandler);
        regFizickaWs.CallWs("registracijaVolontera",data);
    }
    public void registracijaPravna(ArrayList<String> data){
        FdWebServiceCaller regPravnaWs = new FdWebServiceCaller(responseHandler);
        regPravnaWs.CallWs("registracijaOstali",data);
    }

    FdWebServiceHandler responseHandler = new FdWebServiceHandler() {
        @Override
        public void onDataArrived(String message, String status, boolean ok) {
            if(ok){
                mMessage = message;
                mStatus = status;
                if(mStatus.startsWith("O")){
                    opSuccessful = true;
                }else{
                    opSuccessful = false;
                }
                checkCompleted = true;
            }
        }
    };


}
