package com.coky.app.loaders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

import com.coky.app.MainActivity;
import com.coky.app.R;
import com.coky.core.entities.SpinnerElement;
import com.coky.core.entities.VrstaJedinica;
import com.coky.webservice.FdWebService;
import com.coky.webservice.FdWebServiceCaller;
import com.coky.webservice.FdWebServiceHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Čoky on 10.11.2016..
 */

public class WsDataLoader {

    private Boolean opSuccessful;
    private WsDataLoadedListener wsDataLoadedListener;

    public void prijava(ArrayList<String> data, WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener = wsDataLoadedListener;
        FdWebServiceCaller prijavaWs = new FdWebServiceCaller(responseHandler);
        prijavaWs.CallWs("prijava",data);
    }
    public void registracijaFizicka(ArrayList<String> data, WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener = wsDataLoadedListener;
        FdWebServiceCaller regFizickaWs = new FdWebServiceCaller(responseHandler);
        regFizickaWs.CallWs("registracijaVolontera",data);
    }
    public void registracijaPravna(ArrayList<String> data, WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener = wsDataLoadedListener;
        FdWebServiceCaller regPravnaWs = new FdWebServiceCaller(responseHandler);
        regPravnaWs.CallWs("registracijaOstali",data);
    }
    public void dohvatiVrstaJedinica(WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener=wsDataLoadedListener;
        FdWebServiceCaller vrstaJedinicaWs= new FdWebServiceCaller(responseHandler);
        vrstaJedinicaWs.CallWs("vrstaJedinica", new ArrayList<String>());
    }

    FdWebServiceHandler responseHandler = new FdWebServiceHandler() {
        @Override
        public void onDataArrived(Object message, int tip, boolean ok) {
            if(ok){
                if(tip != 0){
                    opSuccessful = true;
                }else{
                    opSuccessful = false;
                }
                wsDataLoadedListener.onWsDataLoaded(message,tip,opSuccessful);
            }
        }
    };


}
