package com.coky.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;
import com.coky.core.entities.GoogleMapa;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaPaket extends AppCompatActivity implements OnMapReadyCallback, WsDataLoadedListener {

    public MapaPaket() {
    }

    private String paketID;

    private GoogleMap mMap;

    private double lat1 /*= 46.3076267*/;
    private double lat2 /*= 46.3097705*/;
    private double lon1 /*= 16.3382566*/;
    private double lon2 /*= 16.3468148*/;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_paket);
        paketID = getIntent().getExtras().getString("paketID");
        WsDataLoader wsDataLoader = new WsDataLoader();
        wsDataLoader.preuzmiKoordinate(paketID,this);
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
        Log.d("mapa", "AddMarkers");
        if (mMap != null) {
            AddMarker(lat1, lon1);
            AddMarker(lat2, lon2);
        }

    }

    /**
     * Metoda a obradu podataka vraćenih sa web servisa
     * @param message atribut tipa GoogleMapa koji vraća web servis
     * @param tip atribut koji govori da li je uspješno dohvaceno ili ne, 1 uspjesno, a 0 neuspjesno
     */

    @Override
    public void onWsDataLoaded(Object message, int tip) {
        GoogleMapa koordinate = (GoogleMapa) message;
        lat1 = koordinate.getLat_donor();
        lon1 = koordinate.getLng_donor();
        lat2 = koordinate.getLat_potrebiti();
        lon2 = koordinate.getLng_potrebiti();
        Log.d("mapa", "onWsDataLoaded");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        MapFragment mMapFragment = (com.google.android.gms.maps.MapFragment) this.getFragmentManager().findFragmentById(R.id.mapfragment);
        mMapFragment.getMapAsync(this);
    }
}
