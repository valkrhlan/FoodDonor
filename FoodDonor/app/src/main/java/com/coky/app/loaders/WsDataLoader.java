package com.coky.app.loaders;

import com.coky.core.entities.Korisnik;
import com.coky.core.entities.RegistriraniKorisnik;
import com.coky.webservice.FdWebServiceCaller;
import com.coky.webservice.FdWebServiceHandler;

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
    public void registracijaFizicka(Korisnik data, WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener = wsDataLoadedListener;
        FdWs.CallWsForFizickaOsoba(data);
    }
    public void registracijaPravna(Korisnik data, WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener = wsDataLoadedListener;
        FdWs.CallWsForPravnaOsoba(data);
    }
    public void dohvatiVrstaJedinica(WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener=wsDataLoadedListener;
        FdWs.CallWsForVrsteJedinica();
    }
    public  void dodajPaket(String email, String json,WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener=wsDataLoadedListener;
        FdWs.CallWsForDodajPaket(email,json);
    }

    FdWebServiceHandler responseHandler = new FdWebServiceHandler() {
        @Override
        public void onDataArrived(Object message, int status) {
                wsDataLoadedListener.onWsDataLoaded(message, status);
        }
    };


}
