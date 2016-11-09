package com.coky.webservice;

import com.coky.webservice.responses.FbWebServiceResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Čoky on 9.11.2016..
 */

public interface FdWebService {

    String baseUrl = "https://air-web-service.000webhostapp.com/webservice/";

    @GET(baseUrl + "prijava/{email}/{lozinka}/")
    Call<FbWebServiceResponse> getKorisnik(@Path("email") String email, @Path("lozinka") String loznika);

    @POST(baseUrl + "registracija/{metoda}/{email}/{lozinka}/{oib}/{grad}/{adresa}/{kontakt}/{ime}/{prezime}/")
    Call<FbWebServiceResponse> setFizickaOsoba(
            @Path("metoda") String metoda,
            @Path("email") String email,
            @Path("lozinka") String lozinka,
            @Path("oib") String oib,
            @Path("grad") String grad,
            @Path("adresa") String adresa,
            @Path("kontakt") String kontakt,
            @Path("ime") String ime,
            @Path("prezime") String prezime);


    //Krhlanko, daj provjeri ovo i onaj word, nekaj smo fulali u nastavku URL-a.

    @POST(baseUrl + "registracijaOstali/{metoda}/{email}/{lozinka}/{oib}/{grad}/{adresa}/{kontakt}/{naziv}/{tip}/")
    Call<FbWebServiceResponse> setPravnaOsoba(
            @Path("metoda") String metoda,
            @Path("email") String email,
            @Path("lozinka") String lozinka,
            @Path("oib") String oib,
            @Path("grad") String grad,
            @Path("adresa") String adresa,
            @Path("kontakt") String kontakt,
            @Path("ime") String ime,
            @Path("prezime") String prezime);
}
