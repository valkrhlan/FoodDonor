package com.coky.webservice.responses;

/**
 * Created by Čoky on 9.11.2016..
 */

public class FdWebServiceResponse {
    public String Status;
    public int nbResults;
    public String message;
    public String data;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
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
