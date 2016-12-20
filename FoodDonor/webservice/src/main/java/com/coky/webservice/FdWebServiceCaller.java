package com.coky.webservice;

import com.coky.core.entities.Korisnik;
import com.coky.core.entities.RegistriraniKorisnik;
import com.coky.core.entities.VrstaJedinica;
import com.coky.webservice.responses.FdWebServiceResponse;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

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
    public void CallWsForDodajPaket(String email, String json){
        FdWebService fdWebService=retrofit.create(FdWebService.class);
        call=fdWebService.dodajPaket(email,json);
        HandleResponseFromCall("dodajPaket");
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

}
