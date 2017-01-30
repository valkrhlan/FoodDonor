package com.coky.core.entities;

import java.util.List;

/**
 * Created by Valentina on 30.1.2017..
 */

public class ListaNotifikacija {
    List<Notifikacija> notifikacija;

    public ListaNotifikacija(List<Notifikacija> notifikacija) {
        this.notifikacija = notifikacija;
    }

    public List<Notifikacija> getNotifikacija() {
        return notifikacija;
    }

    public void setNotifikacija(List<Notifikacija> notifikacija) {
        this.notifikacija = notifikacija;
    }
    public int size(){
        return notifikacija.size();
    }
    public String getTitle(int i){
        return notifikacija.get(i).getTitle();
    }
    public  String getMessage(int i){
        return notifikacija.get(i).getMessage();
    }
}
