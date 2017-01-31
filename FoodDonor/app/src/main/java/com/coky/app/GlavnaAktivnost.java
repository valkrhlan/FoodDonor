package com.coky.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.coky.app.fragments.PopisPaketa;


public class GlavnaAktivnost extends AppCompatActivity {
    
    public Boolean fragmentCreated = false;

    private int tipKorisnika;
    private String emailKorisnika;
    private String grad;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onStart(){
        super.onStart();
        getSharedPrefs();
    }

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
            createInitialFragment(new PopisPaketa(),"popisPaketa");
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
            odjava();
            return true;
        }else{
            if(id==R.id.notifikacije){
                opcije();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("fragmentCreated", fragmentCreated);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            Intent returnIntent = new Intent();
            setResult(RESULT_CANCELED,returnIntent);
            finish();
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
        setGrad(prefs.getString("grad","Zagreb"));
    }

    private void odjava(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("brisi", emailKorisnika);
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    private void opcije(){
        Intent intent = new Intent(GlavnaAktivnost.this,NotifikacijeOpcije.class);
        startActivityForResult(intent, 2); //Ovo naredbom se "čeka" na dva podatka iz aktivnosti NotifikacijeOpcije. Ako nije potrebno preuzeti nikakve podake, nek se ova naredba promjeni u startActivity(intent);

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

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public void isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        Boolean connection = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if(connection == false){
            AlertDialog alertDialog = new AlertDialog.Builder(GlavnaAktivnost.this).create();
            alertDialog.setTitle("Pogreška u internetskoj vezi");
            alertDialog.setMessage("Molimo Vas, omogućite internetsku vezu kako bi ste nastavili sa daljnjim radom u aplikaciji.");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Odjavi me",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            odjava();
                            dialog.dismiss();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Nastavi dalje",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            isNetworkAvailable();
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }


}
