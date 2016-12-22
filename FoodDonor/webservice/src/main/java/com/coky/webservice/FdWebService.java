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

    @GET("{metoda}/{email}/{lozinka}/")
    Call<FdWebServiceResponse> getKorisnik(@Path("metoda") String metoda, @Path("email") String email, @Path("lozinka") String loznika);

    @GET("registracija/{metoda}/{email}/{lozinka}/{oib}/{grad}/{adresa}/{kontakt}/{ime}/{prezime}/")
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



    @GET("registracijaOstali/{metoda}/{email}/{lozinka}/{oib}/{grad}/{adresa}/{kontakt}/{naziv}/{tip}/")
    Call<FdWebServiceResponse> setPravnaOsoba(
            @Path("metoda") String metoda,
            @Path("email") String email,
            @Path("lozinka") String lozinka,
            @Path("oib") String oib,
            @Path("grad") String grad,
            @Path("adresa") String adresa,
            @Path("kontakt") String kontakt,
            @Path("naziv") String naziv,
            @Path("tip") String tip);

    @GET("vrstaJedinica/all/")
    Call<FdWebServiceResponse> getVrstaJedinica();

    @GET("paket/novi/{email}/{data}/")
    Call<FdWebServiceResponse> dodajPaket(
            @Path("email") String email,
            @Path("data") String json );

    @GET("paket/dohvati/{email}/")
    Call<FdWebServiceResponse> dohvatiPakete(
            @Path("email") String email);

    @GET("registerDevice/{email}/{token}/")
    Call<FdWebServiceResponse> spremiToken(
            @Path("email") String email,
            @Path("token") String token);


    @GET("sendNotifications/{email}/{title}/{message}/")
    Call<FdWebServiceResponse> saljiNotif(
            @Path("email") String email,
            @Path("title") String naslov,
            @Path("message") String poruka);


    @GET("obrisiToken/{email}/")
    Call<FdWebServiceResponse> brisiToken(
            @Path("email") String email);

}
