package com.coky.app.klase;

import android.content.Context;

import com.coky.app.loaders.NotifikacijaLoadedListener;
import com.coky.app.loaders.SlanjePodatakaModulima;
import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;


/**
 * Created by Valentina on 2.1.2017..
 *
 * klasa za komunikaciju s modulima i dohvat liste mogućih modularnih opcija za dohvaćanje notifikacija
 */

public class UpraviteljNotifikacija implements NotifikacijaLoadedListener,WsDataLoadedListener {

    public NotifikacijaMoguceOpcije notifikacijaMoguceOpcije;
    private ContextProvider cp;
    public UpraviteljNotifikacija() {
        cp=new ContextProvider();

        this.notifikacijaMoguceOpcije = new NotifikacijaMoguceOpcije(cp.dohvatiContext(),this);
    }

    /**
     * Metoda za obradu podataka dobivenih od modula
     * na temelju dobivenih podataka se generiraju notifikacije
     *
     * @param title
     * @param message
     * @param ikona
     */
    @Override
    public void onNotifikacijaLoaded(String title, String message, int ikona) {
        Context mCtx=cp.dohvatiContext();
        PrikazNotifikacija prikazNotifikacija=new PrikazNotifikacija();
        prikazNotifikacija.prikaziNotifikaciju(title,message,ikona,mCtx);
    }


    /**
     * Metoda koja se okine nakon promjene odabrane opcije za dohvaćanje notifikacija
     * obavijesštavaju se mogduli o promjenama
     *
     * @param mContex
     * @param opcija
     * @param prethodnaOpcija
     * @param interval
     */
    public  void pohraniPromjene(Context mContex, String opcija,String prethodnaOpcija, Integer interval){
        for (int i=0; i<notifikacijaMoguceOpcije.size();i++){
              SlanjePodatakaModulima slanjePodatakaModulima=(SlanjePodatakaModulima)notifikacijaMoguceOpcije.getModul(i);
              slanjePodatakaModulima.obradiPromjenu(mContex,opcija,prethodnaOpcija,interval);
        }

    }

    /**
     * Metoda pomoću koje moduli traže da se napravi određena akcija koja zahtjeva uporabu web servisa
     *
     * @param opcija
     * @param param1
     * @param param2
     */
    @Override
    public void pozoviWS(String opcija, String param1, String param2) {
        WsDataLoader wsDataLoader = new WsDataLoader();
        switch (opcija){
            case "dodajToken":
                wsDataLoader.slanjeTokena(param1,param2,this);
                break;
            case "obrisiToken":
                wsDataLoader.brisanjeTokena(param1, this);
                break;
            case "dohvatiNotifikacije":
                wsDataLoader.dohvatiNotifikacije(param1,param2,this);
                break;
        }
    }

    /**
     * Metoda pomoću koje se podatci dobiveni od web servisa proslijeđuju modulima
     *
     * @param message
     * @param tip
     */
    @Override
    public void onWsDataLoaded(Object message, int tip) {
        for (int i=0; i<notifikacijaMoguceOpcije.size();i++){
            SlanjePodatakaModulima slanjePodatakaModulima=(SlanjePodatakaModulima)notifikacijaMoguceOpcije.getModul(i);
            slanjePodatakaModulima.dostaviPodatkeWS(cp.dohvatiContext(),message,this);
        }
    }
}
