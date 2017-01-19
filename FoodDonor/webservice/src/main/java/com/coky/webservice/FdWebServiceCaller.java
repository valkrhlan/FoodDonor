package com.coky.webservice;

import android.util.Log;

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

    public void CallWsForPreuzmiPakete(String email, String odabrani){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.dohvatiPakete(email, odabrani);
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
    public void CallWsForGetNotifications(String email, long timestamp){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.dohvatiNotifikacije(email,timestamp);
        HandleResponseFromCall("dohvatiNotifikacije");
    }

    public void CallWsForOdaberiPaketPotrebiti(String email, String hitno, String idPaketa){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.odaberiPaketPotrebiti(email,hitno,idPaketa);
        HandleResponseFromCall("odaberiPaketPotrebiti");
    }

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
                                }else{
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

    private void handleVstaJedinica(Response<FdWebServiceResponse> response){
            Gson gson = new Gson();
            VrstaJedinica vrstaJedinicaItem=gson.fromJson(response.body().getData(),VrstaJedinica.class);
            fdWebServiceHandler.onDataArrived((Object)vrstaJedinicaItem,response.body().getNbResults());
    }

    private void handlePreuzetiPaketi(Response<FdWebServiceResponse> response) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd") // response JSON format
                .create();
        Paket[] paketi = gson.fromJson(response.body().getData(), Paket[].class);
        if(fdWebServiceHandler != null){
            fdWebServiceHandler.onDataArrived(Arrays.asList(paketi),response.body().getNbResults());
        }
    }
    private void handleDohvatiNotifikacije(Response<FdWebServiceResponse> response){
        Gson gson=new Gson();
        Notifikacija[] notifikacije=gson.fromJson(response.body().getData(),Notifikacija[].class);
        if(fdWebServiceHandler!=null){
            fdWebServiceHandler.onDataArrived(Arrays.asList(notifikacije),response.body().getNbResults());
        }
    }

}
