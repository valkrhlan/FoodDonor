package com.coky.app;

import android.*;
import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.coky.core.entities.Paket;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapaPaket extends AppCompatActivity implements OnMapReadyCallback {

    public MapaPaket() {
    }

    private Paket p;

    public MapaPaket(Paket paket) {
        p = paket;
    }


    private GoogleMap mMap;
    private ArrayList<Paket> paketi;
    private double lat1 = 46.3076267;
    private double lat2 = 46.3097705;
    private double lon1 = 16.3382566;
    private double lon2 = 16.3468148;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_paket);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        if(p != null){


        };
        MapFragment mMapFragment = (com.google.android.gms.maps.MapFragment) this.getFragmentManager().findFragmentById(R.id.mapfragment);
        mMapFragment.getMapAsync(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission denied to read your ACCESS FINE LOCATION", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void AddMarker(double lat, double lon) {
        LatLng pos1 = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions()
                .position(pos1)
                .title("Pin Marker"));
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        try {
            mMap.setMyLocationEnabled(true);

        } catch (SecurityException e) {
            Toast.makeText(this, "Uključite dozvolu za GPS i storage! (Settings > Apps > Food donor > Permissions", Toast.LENGTH_SHORT).show();
        }
        mMap.setTrafficEnabled(true);
        mMap.setIndoorEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        //LocationManager Lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        //Location l = Lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double zoom = 13.6;
        //LatLng location = new LatLng(l.getLatitude(),l.getLongitude() );
        LatLng pos1 = new LatLng(lat1, lon1);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos1, (float) zoom));
        if (mMap != null) {
            AddMarker(lat1, lon1);
            AddMarker(lat2, lon2);
        }

    }
}
