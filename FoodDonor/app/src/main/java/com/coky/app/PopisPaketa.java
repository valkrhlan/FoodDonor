package com.coky.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class PopisPaketa extends AppCompatActivity {

    public int tipKorisnika = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popis_paketa);
        getSupportActionBar().setTitle("Popis paketa");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        tipKorisnika = prefs.getInt("tipKorisnika", 0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //-----Ovo se može maknuti ako će se napraviti floating button koji služi za dodavanje paketa
        MenuItem dodajPaketItem = menu.findItem(R.id.dodajPaket);
        if(tipKorisnika == 1){
            dodajPaketItem.setVisible(true);
        }
        //-----
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.odjava) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
