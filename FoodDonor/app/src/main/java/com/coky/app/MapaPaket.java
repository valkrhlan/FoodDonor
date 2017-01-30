package com.coky.app;

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
    private GoogleMap mMap;
    private ArrayList<Paket> paketi;
    private double lat1 = 45.52811759999999;
    private double lat2 = 31.2354;
    private double lon1 = 17.2437493;
    private double lon2 = 77.7784;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_paket);
        MapFragment mMapFragment = (com.google.android.gms.maps.MapFragment) this.getFragmentManager().findFragmentById(R.id.mapfragment);
        mMapFragment.getMapAsync(this);

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
        try {

            mMap = map;
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mMap.setMyLocationEnabled(true);
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

        } catch (SecurityException e) {
            Toast.makeText(this, "Molimo vas Uključite GPS opciju i ponovno posjetite ovaj modul!", Toast.LENGTH_SHORT).show();
        }
    }
}
