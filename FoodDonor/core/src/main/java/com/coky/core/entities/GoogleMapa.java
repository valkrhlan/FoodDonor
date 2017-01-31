package com.coky.core.entities;

/**
 * Created by Čoky on 31.1.2017..
 */

public class GoogleMapa {
    private double lat_donor;
    private double lng_donor;
    private double lat_potrebiti;
    private double lng_potrebiti;

    public GoogleMapa(double lat_donor, double lng_donor, double lat_potrebiti, double lng_potrebiti) {
        this.lat_donor = lat_donor;
        this.lng_donor = lng_donor;
        this.lat_potrebiti = lat_potrebiti;
        this.lng_potrebiti = lng_potrebiti;
    }

    public double getLat_donor() {
        return lat_donor;
    }

    public void setLat_donor(int lat_donor) {
        this.lat_donor = lat_donor;
    }

    public double getLng_donor() {
        return lng_donor;
    }

    public void setLng_donor(int lng_donor) {
        this.lng_donor = lng_donor;
    }

    public double getLat_potrebiti() {
        return lat_potrebiti;
    }

    public void setLat_potrebiti(int lat_potrebiti) {
        this.lat_potrebiti = lat_potrebiti;
    }

    public double getLng_potrebiti() {
        return lng_potrebiti;
    }

    public void setLng_potrebiti(int lng_potrebiti) {
        this.lng_potrebiti = lng_potrebiti;
    }
}
