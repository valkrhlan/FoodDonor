package com.coky.webservice;

import com.coky.webservice.responses.FdWebServiceResponse;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

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

    public void CallWs(String method, ArrayList<String> params){
        FdWebService fdWebService = retrofit.create(FdWebService.class);
        Call<FdWebServiceResponse> call;
        if(method == "registracijaVolontera"){
            call = fdWebService.setFizickaOsoba(
                    method,
                    params.get(0),
                    params.get(1),
                    params.get(2),
                    params.get(3),
                    params.get(4),
                    params.get(5),
                    params.get(6),
                    params.get(7));
        }else if (method == "registracijaOstali"){
            call = fdWebService.setPravnaOsoba(
                    method,
                    params.get(0),
                    params.get(1),
                    params.get(2),
                    params.get(3),
                    params.get(4),
                    params.get(5),
                    params.get(6),
                    params.get(7));
        }else{
            call = fdWebService.getKorisnik(
                    method,
                    params.get(0),
                    params.get(1));
        }

        if(call != null){
            call.enqueue(new Callback<FdWebServiceResponse>() {

                @Override
                public void onResponse(Response<FdWebServiceResponse> response, Retrofit retrofit) {

                    try{
                        if(response.isSuccess()){
                            if(fdWebServiceHandler != null)
                                fdWebServiceHandler.onDataArrived(response.body().getMessage().toString(),response.body().getNbResults(),true);
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
}
