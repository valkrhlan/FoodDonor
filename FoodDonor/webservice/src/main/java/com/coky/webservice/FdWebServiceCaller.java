package com.coky.webservice;

import android.util.Log;

import com.coky.core.entities.GoogleMapa;
import com.coky.core.entities.Gradovi;
import com.coky.core.entities.Korisnik;
import com.coky.core.entities.Notifikacija;
import com.coky.core.entities.Paket;
import com.coky.core.entities.RegistriraniKorisnik;
import com.coky.core.entities.VrstaJedinica;
import com.coky.webservice.responses.FdWebServiceResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.Arrays;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Čoky on 10.11.2016..
 */

public class FdWebServiceCaller {
    FdWebServiceHandler fdWebServiceHandler;
    Retrofit retrofit;
    Call<FdWebServiceResponse> call;

    private final String baseUrl = "https://air-web-service.000webhostapp.com/webservice/";
     public FdWebServiceCaller(FdWebServiceHandler fdWebServiceHandler){
        this.fdWebServiceHandler = fdWebServiceHandler;
        OkHttpClient okHttpClient  = new OkHttpClient();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public void CallWsForRegistiraniKorisnik(RegistriraniKorisnik data) {
        FdWebService fdWebService = retrofit.create(FdWebService.class);
        call = fdWebService.getKorisnik("prijava",data.getEmail(),data.getLozinka());
        HandleResponseFromCall("prijava");
    }

    public void CallWsForFizickaOsoba(Korisnik data) {
        FdWebService fdWebService = retrofit.create(FdWebService.class);
        call = fdWebService.setFizickaOsoba("registracijaVolontera",
                data.getEmail(),
                data.getLozinka(),
                data.getOib(),
                data.getGrad(),
                data.getAdresa(),
                data.getKontakt(),
                data.getIme(),
                data.getPrezime());
        HandleResponseFromCall("registracijaVolontera");
    }

    public void CallWsForPravnaOsoba(Korisnik data) {
        FdWebService fdWebService = retrofit.create(FdWebService.class);
        call = fdWebService.setPravnaOsoba("registracijaOstali",
                data.getEmail(),
                data.getLozinka(),
                data.getOib(),
                data.getGrad(),
                data.getAdresa(),
                data.getKontakt(),
                data.getNaziv() + "_" + data.getIme() + "_" + data.getPrezime(),
                data.getTip());
        HandleResponseFromCall("registracijaOstali");
    }

    public void CallWsForVrsteJedinica(){
        FdWebService fdWebService = retrofit.create(FdWebService.class);
        call = fdWebService.getVrstaJedinica();
        HandleResponseFromCall("vrstaJedinica");
    }

    public void CallWsForDodajPaket(String email, String json,Integer prijevoz){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.dodajPaket(email,json,prijevoz);
        HandleResponseFromCall("dodajPaket");
    }

    public void CallWsForPreuzmiPakete(String email, String odabrani, String grad){
        Log.d("paketi","1c. metoda preuzmi pakete (WScaller)");
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.dohvatiPakete(email, odabrani, grad);
        HandleResponseFromCall("dohvatiPakete");
    }

    public void CallWsForSpremanjeTokena(String email, String token){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.spremiToken(email,token);
        HandleResponseFromCall("spremiToken");
    }

    public void CallWsForSaljiNotif(String email, String naslov, String poruka){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        //Log.d("slanjee",baseUrl+"sendNotification/"+email+"/"+naslov+"/"+poruka+"/");
        call=fdWebService.saljiNotif(email, naslov, poruka);
        HandleResponseFromCall("saljiNotif");
    }

    public void CallWsForBrisanjeTokena(String email){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.brisiToken(email);
        HandleResponseFromCall("brisiToken");
    }
    public void CallWsForGetNotifications(String email, String timestamp){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.dohvatiNotifikacije(email,timestamp);
        HandleResponseFromCall("dohvatiNotifikacije");
    }

    public void CallWsForOdaberiPaketPotrebiti(String email, String hitno, String idPaketa){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.odaberiPaketPotrebiti(email,hitno,idPaketa);
        HandleResponseFromCall("odaberiPaketPotrebiti");
    }

    public void CallWsForGradovi(){
        Log.d("grad", "2c. caller");
        FdWebService fdWebService = retrofit.create(FdWebService.class);
        call = fdWebService.getGradovi();
        HandleResponseFromCall("getGradovi");
    }

    public void CallWsForOdaberiPaketVolonter(String email, String idPaketa){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.odaberiPaketVolonter(email,idPaketa);
        HandleResponseFromCall("odaberiPaketVolonter");
    }

    public void CallWsForEvidentirajDolazak(String idPaketa){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.evidentirajDolazak(idPaketa);
        HandleResponseFromCall("evidentirajDolazak");
    }

    public void CallWsForPreuzmiKoordinate(String idPaketa){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.preuzmiKoordinate(idPaketa);
        HandleResponseFromCall("preuzmiKoordinate");
    }

    /**
     * ovisno o emtodi koja se poziva rukuje odgovorima od web servisa
     * neke metode zahtjevaju da se vraća obićan string,a neke da se vrati skup podataka
     *
     * @param method
     */
    public void HandleResponseFromCall(final String method){
        if(call != null){
            call.enqueue(new Callback<FdWebServiceResponse>() {
                @Override
                public void onResponse(Response<FdWebServiceResponse> response, Retrofit retrofit) {
                    try{
                        if(response.isSuccess()){
                            if(fdWebServiceHandler != null)
                                if(method=="vrstaJedinica"){
                                    // fdWebServiceHandler.onDataArrived(response.body().getData(),response.body().getNbResults(),true);
                                    handleVstaJedinica(response);
                                }else if(method=="dohvatiPakete"){
                                    handlePreuzetiPaketi(response);
                                }else if(method=="saljiNotif" || method=="brisiToken"){
                                    //DO NOTHING!!!
                                }else if (method=="dohvatiNotifikacije"){
                                    handleDohvatiNotifikacije(response);
                                }else if(method=="getGradovi"){
                                    handleDohvatiGradove(response);
                                }else if(method=="preuzmiKoordinate"){
                                    handleDohvatiKoordinate(response);
                                }                                else{
                                    fdWebServiceHandler.onDataArrived(response.body().getMessage().toString(),response.body().getNbResults());

                                }
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    /**
     * metoda koja kao odgovor vraća klasu VrstaJedinica koja se kasnije koristi za popunjavanje spinner elemenata pri dodavanju novog paketa
     *
     * @param response
     */
    private void handleVstaJedinica(Response<FdWebServiceResponse> response){
            Gson gson = new Gson();
            VrstaJedinica vrstaJedinicaItem=gson.fromJson(response.body().getData(),VrstaJedinica.class);
            fdWebServiceHandler.onDataArrived((Object)vrstaJedinicaItem,response.body().getNbResults());
    }

    /**
     * Kaoresponse vraća niz paketa za određenog koisnika
     *
     * @param response
     */
    private void handlePreuzetiPaketi(Response<FdWebServiceResponse> response) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd") // response JSON format
                .create();
        Paket[] paketi = gson.fromJson(response.body().getData(), Paket[].class);
        if(fdWebServiceHandler != null){
            fdWebServiceHandler.onDataArrived(Arrays.asList(paketi),response.body().getNbResults());
        }
    }

    /**
     * Metoda koja vraća niz notifikacija koje su dohvaćene sa web servisa
     *
     * @param response
     */
    private void handleDohvatiNotifikacije(Response<FdWebServiceResponse> response){
        Gson gson=new Gson();
        Notifikacija[] notifikacije=gson.fromJson(response.body().getData(),Notifikacija[].class);
        String pom="test";
        if(fdWebServiceHandler!=null){
            fdWebServiceHandler.onDataArrived(notifikacije,response.body().getNbResults());
        }
    }
    
    private void handleDohvatiGradove(Response<FdWebServiceResponse> response){
        Gson gson=new Gson();
        Gradovi[] gradovi=gson.fromJson(response.body().getData(),Gradovi[].class);
        if(fdWebServiceHandler!=null){
            fdWebServiceHandler.onDataArrived(Arrays.asList(gradovi),response.body().getNbResults());
        }
    }

    /**
     * Metoda koja vraća  2 para kordinata (longitude i latitude za gradove iz kojih su donor i potrebiti
     *
     * @param response
     */
    private void handleDohvatiKoordinate(Response<FdWebServiceResponse> response){
        Gson gson=new Gson();
        GoogleMapa koordinate=gson.fromJson(response.body().getData(),GoogleMapa.class);
        if(fdWebServiceHandler!=null){
            fdWebServiceHandler.onDataArrived(koordinate,response.body().getNbResults());
        }
    }

}
