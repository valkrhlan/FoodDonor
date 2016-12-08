package com.coky.app.loaders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

import com.coky.app.MainActivity;
import com.coky.app.R;
import com.coky.core.entities.FizickaOsoba;
import com.coky.core.entities.PravnaOsoba;
import com.coky.core.entities.RegistriraniKorisnik;
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
    private FdWebServiceCaller FdWs;

    public WsDataLoader() {
        FdWs = new FdWebServiceCaller(responseHandler);
    }

    public void prijava(RegistriraniKorisnik data, WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener = wsDataLoadedListener;
        FdWs.CallWsForRegistiraniKorisnik(data);
    }
    public void registracijaFizicka(FizickaOsoba data, WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener = wsDataLoadedListener;
        FdWs.CallWsForFizickaOsoba(data);
    }
    public void registracijaPravna(PravnaOsoba data, WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener = wsDataLoadedListener;
        FdWs.CallWsForPravnaOsoba(data);
    }
    public void dohvatiVrstaJedinica(WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener=wsDataLoadedListener;
        FdWs.CallWsForVrsteJedinica();
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
