package com.coky.webservice;

import com.coky.webservice.responses.FdWebServiceResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Čoky on 9.11.2016..
 */

public interface FdWebService {

    String baseUrl = "https://air-web-service.000webhostapp.com/webservice/";

    @GET(baseUrl + "prijava/{metoda}/{email}/{lozinka}/")
    Call<FdWebServiceResponse> getKorisnik(@Path("metoda") String metoda, @Path("email") String email, @Path("lozinka") String loznika);

    @POST(baseUrl + "registracija/{metoda}/{email}/{lozinka}/{oib}/{grad}/{adresa}/{kontakt}/{ime}/{prezime}/")
    Call<FdWebServiceResponse> setFizickaOsoba(
            @Path("metoda") String metoda,
            @Path("email") String email,
            @Path("lozinka") String lozinka,
            @Path("oib") String oib,
            @Path("grad") String grad,
            @Path("adresa") String adresa,
            @Path("kontakt") String kontakt,
            @Path("ime") String ime,
            @Path("prezime") String prezime);



    @POST(baseUrl + "registracijaOstali/{metoda}/{email}/{lozinka}/{oib}/{grad}/{adresa}/{kontakt}/{naziv}/{tip}/")
    Call<FdWebServiceResponse> setPravnaOsoba(
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
