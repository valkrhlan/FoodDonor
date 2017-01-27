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

    @GET("paket/novi/{email}/{data}/{prijevoz}/")
    Call<FdWebServiceResponse> dodajPaket(
            @Path("email") String email,
            @Path("data") String json,
            @Path("prijevoz") Integer prijevoz);

    @GET("paket/dohvati/{email}/{odabrani}/")
    Call<FdWebServiceResponse> dohvatiPakete(
            @Path("email") String email,
            @Path("odabrani") String odabrani);

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


    @GET("getNotifications/{email}/{timestamp}/")
    Call<FdWebServiceResponse>dohvatiNotifikacije(
            @Path("email") String email,
            @Path("timestamp") long timestamp);

    @GET("odaberiPaketPotrebiti/{email}/{hitno}/{idPaketa}/")
    Call<FdWebServiceResponse>odaberiPaketPotrebiti(
            @Path("email") String email,
            @Path("hitno") String hitno,
            @Path("idPaketa") String idPaketa);

    @GET("grad/all/")
    Call<FdWebServiceResponse> getGradovi();

    @GET("odaberiPaketVolonter/{email}/{idPaketa}/")
    Call<FdWebServiceResponse>odaberiPaketVolonter(
            @Path("email") String email,
            @Path("idPaketa") String idPaketa);

    @GET("evidentirajDolazak/{idPaketa}/")
    Call<FdWebServiceResponse>evidentirajDolazak(
            @Path("idPaketa") String idPaketa);
}
