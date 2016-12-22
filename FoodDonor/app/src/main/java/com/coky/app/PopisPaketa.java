package com.coky.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.coky.app.firebase.SharedPrefManager;
import com.coky.app.fragments.DonorPopisPaketa;


public class PopisPaketa extends AppCompatActivity {
    
    public Boolean fragmentCreated = false;

    private int tipKorisnika;
    private String emailKorisnika;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popis_paketa);
        getSupportActionBar().setTitle("Popis paketa");
        getSharedPrefs();
        if(savedInstanceState != null) {
            fragmentCreated = savedInstanceState.getBoolean("fragmentCreated", false);
        }
        if(fragmentCreated == false){
            chooseInitialFragment();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.odjava) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("brisi", emailKorisnika);
            setResult(RESULT_OK,returnIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("fragmentCreated", fragmentCreated);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void chooseInitialFragment(){
        switch(getTipKorisnika()){
            case 1: //DONOR
                createInitialFragment(new DonorPopisPaketa(),"popisPaketa");
                break;
            case 2: //POTREBITI
                Toast.makeText(this,"Potrebiti korisnik u implementaciji!", Toast.LENGTH_LONG).show();
                finish();
                break;
            case 3: //VOLONTER
                Toast.makeText(this,"Volonter u implementaciji!", Toast.LENGTH_LONG).show();
                finish();
                break;
            default:
                Toast.makeText(this,"Desila se greška: tip korisnika je nula!", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }

    private void createInitialFragment(Fragment fragment, String tag){
        fragmentCreated = true;
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.activity_popis_paketa, fragment, tag);
        fragmentTransaction.commit();

    }

    private void getSharedPrefs(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        setEmailKorisnika(prefs.getString("emailKorisnika","test@test.test"));
        setTipKorisnika(prefs.getInt("tipKorisnika", 0));
    }

    public String getEmailKorisnika() {
        return emailKorisnika;
    }

    public void setEmailKorisnika(String emailKorisnika) {
        this.emailKorisnika = emailKorisnika;
    }

    public int getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(int tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }
}
