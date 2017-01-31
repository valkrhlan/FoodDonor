package com.coky.core.entities;

/**
 * Created by Čoky on 31.1.2017..
 */

public class GoogleMapa {
    private int lat_donor;
    private int lng_donor;
    private int lat_potrebiti;
    private int lng_potrebiti;

    public GoogleMapa(int lat_donor, int lng_donor, int lat_potrebiti, int lng_potrebiti) {
        this.lat_donor = lat_donor;
        this.lng_donor = lng_donor;
        this.lat_potrebiti = lat_potrebiti;
        this.lng_potrebiti = lng_potrebiti;
    }

    public int getLat_donor() {
        return lat_donor;
    }

    public void setLat_donor(int lat_donor) {
        this.lat_donor = lat_donor;
    }

    public int getLng_donor() {
        return lng_donor;
    }

    public void setLng_donor(int lng_donor) {
        this.lng_donor = lng_donor;
    }

    public int getLat_potrebiti() {
        return lat_potrebiti;
    }

    public void setLat_potrebiti(int lat_potrebiti) {
        this.lat_potrebiti = lat_potrebiti;
    }

    public int getLng_potrebiti() {
        return lng_potrebiti;
    }

    public void setLng_potrebiti(int lng_potrebiti) {
        this.lng_potrebiti = lng_potrebiti;
    }
}
