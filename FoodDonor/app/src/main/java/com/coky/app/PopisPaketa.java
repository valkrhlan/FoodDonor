package com.coky.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.coky.app.fragments.DonorNoviPaket;
import com.coky.app.fragments.DonorPopisPaketa;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopisPaketa extends AppCompatActivity {
    

    public int tipKorisnika = 0;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction1, fragmentTransaction2, fragmentTransaction3;
    Fragment popisPaketa, noviPaket;

    @BindView(R.id.btnNoviPaket)
    Button buttonNoviPaket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popis_paketa);
        getSupportActionBar().setTitle("Popis paketa");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        tipKorisnika = prefs.getInt("tipKorisnika", 0);
        popisPaketa = new DonorPopisPaketa();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.add(R.id.activity_popis_paketa, popisPaketa,"popisPaketa");
        fragmentTransaction1.commit();
        ButterKnife.bind(this);


    }


    @OnClick(R.id.btnNoviPaket)
    public void btnNoviPaketClick(View view){
        buttonNoviPaket.setVisibility(view.GONE);
        noviPaket = fragmentManager.findFragmentByTag("noviPaket");
        if(noviPaket == null){
            noviPaket = new DonorNoviPaket();
        }
        fragmentTransaction2 = fragmentManager.beginTransaction();
        fragmentTransaction2.replace(R.id.activity_popis_paketa, noviPaket, "noviPaket");
        fragmentTransaction2.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //-----Ovo se može prebaciti u floating button (ako ćemo to uzeti kao element dizajna) koji služi za dodavanje paketa #1
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
        //-----Ovo se može prebaciti u floating button (ako ćemo to uzeti kao element dizajna) koji služi za dodavanje paketa #2
        //ovo treba obrisat ak neće bit u izborniku
        else if (id == R.id.dodajPaket){
            noviPaket = fragmentManager.findFragmentByTag("noviPaket");
            if(noviPaket == null){
                noviPaket = new DonorNoviPaket();
            }
            fragmentTransaction2 = fragmentManager.beginTransaction();
            fragmentTransaction2.replace(R.id.activity_popis_paketa, noviPaket, "noviPaket");
            fragmentTransaction2.commit();
        }
        //-----
        return super.onOptionsItemSelected(item);
    }

  public void postaviVidljivost(){
      buttonNoviPaket.setVisibility(findViewById(R.id.btnNoviPaket).VISIBLE);
  }



}
