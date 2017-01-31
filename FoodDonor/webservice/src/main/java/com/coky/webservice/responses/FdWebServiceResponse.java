package com.coky.webservice.responses;

/**
 * Created by Čoky on 9.11.2016..
 *
 * Klasa koje predstavlja izgled odgovora koji se prima sa web servisa
 * izrađena je na temelju parametara unutar jsona koji ws vraća ko odgovor
 */


public class FdWebServiceResponse {
    public String status;
    public int nbResults;
    public String message;
    public String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = status;
    }

    public int getNbResults() {
        return nbResults;
    }

    public void setNbResults(int nbResults) {
        this.nbResults = nbResults;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
