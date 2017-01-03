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
    public  void dodajPaket(String email, String json,Integer prijevoz,WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener=wsDataLoadedListener;
        FdWs.CallWsForDodajPaket(email,json,prijevoz);
    }
    public  void preuzmiPakete(String email, WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener=wsDataLoadedListener;
        FdWs.CallWsForPreuzmiPakete(email);
    }

    public  void slanjeTokena(String email, String token, WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener=wsDataLoadedListener;
        FdWs.CallWsForSpremanjeTokena(email,token);
    }

    public  void posaljiNotif(String email, String naslov, String poruka, WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener=wsDataLoadedListener;
        FdWs.CallWsForSaljiNotif(email,naslov,poruka);
    }

    public  void brisanjeTokena(String email, WsDataLoadedListener wsDataLoadedListener){
        this.wsDataLoadedListener=wsDataLoadedListener;
        FdWs.CallWsForBrisanjeTokena(email);
    }


    FdWebServiceHandler responseHandler = new FdWebServiceHandler() {
        @Override
        public void onDataArrived(Object message, int status) {
                wsDataLoadedListener.onWsDataLoaded(message, status);
        }
    };


}
