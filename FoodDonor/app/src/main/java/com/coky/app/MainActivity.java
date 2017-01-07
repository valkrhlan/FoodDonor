package com.coky.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coky.app.firebase.SharedPrefManager;
import com.coky.app.konfigurabilno.Alarm;
import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;
import com.coky.core.entities.RegistriraniKorisnik;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements WsDataLoadedListener {

    @BindView(R.id.prijavaBtn)
    Button btnPrijava;

    @BindView(R.id.textEmail)
    EditText editEmail;

    @BindView(R.id.textPassword)
    EditText editPassword;

    private Pattern email_check =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private boolean prijavljen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Prijava");
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        editPassword.setText("");
        editEmail.setText("");
        if(checkLoginPersistence() == true) {
            startNextActivity();
        }
    }

    @Override
    public void onWsDataLoaded(Object message, final int tip) {
        if(tip != 0 && message.toString().startsWith("U")){
            String token = SharedPrefManager.getInstance(this).getDeviceToken();
            //Log.d("token","token: "+ token);
            setSharedPrefs(tip, editEmail.getText().toString());
            WsDataLoader wsDataLoader = new WsDataLoader();
            wsDataLoader.slanjeTokena(editEmail.getText().toString(), token, this);
        }
        else if(message.toString().startsWith("D") || message.toString().startsWith("I")){
                     startNextActivity();
        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Rezultat prijave");
            alertDialog.setMessage(message.toString());
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    @OnClick(R.id.prijavaBtn)
    public void prijavaBtnClick(View view){
        if(!editEmail.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty()){
            if(validateMail(editEmail.getText().toString())){
                if(isNetworkAvailable() == true) {
                    RegistriraniKorisnik korisnik = new RegistriraniKorisnik(editEmail.getText().toString(), editPassword.getText().toString());
                    WsDataLoader wsDataLoader = new WsDataLoader();
                    wsDataLoader.prijava(korisnik, this);
                }
            }
            else Toast.makeText(this,"Nije dobra struktura emaila!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Popunite sva polja!",Toast.LENGTH_SHORT).show();
        }

    }


    @OnClick(R.id.registracijaBtn)
    public void registracijaBtnClick(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Način registracije");
        alertDialog.setMessage("Odaberite način registracije");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Pravna osoba",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, RegistracijaPravniKorisnik.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Fizička osoba",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, RegistracijaFizickiKorisnik.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Odustani",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    /*Nakon korisnikove odjave, briše se njegov firebase token u našem web servisu, a ujedno se brišu i podaci o korisniku*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String email = data.getStringExtra("brisi");
                WsDataLoader wsDataLoader = new WsDataLoader();
                wsDataLoader.brisanjeTokena(email, this);
                deleteSharedPrefs();
            }
            if (resultCode == RESULT_CANCELED) {
                finish();
            }
        }
    }

    private boolean validateMail(String emailStr) {
        Matcher matcher = email_check.matcher(emailStr);
        return matcher.find();
    }

    private void setSharedPrefs(int tip, String email){
        long ts=System.currentTimeMillis()/1000;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("tipKorisnika", tip);
        editor.putString("emailKorisnika", email);
        editor.putBoolean("prijavljen", true);
        editor.putLong("timestamp",ts);
        editor.apply();
    }

    private void deleteSharedPrefs(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("prijavljen", false);
        editor.remove("tipKorisnika");
        editor.remove("emailKorisnika");
        editor.remove("notifikacije");
        editor.remove("interval");
        editor.remove("timestamp");
        editor.apply();
    }

    /*Provjera je li korisnik pritisnuo 'natrag' ili odabrao opciju 'odjava'*/
    private Boolean checkLoginPersistence(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prijavljen = prefs.getBoolean("prijavljen",false);
        return prijavljen;
    }

    private void startNextActivity(){
        Intent intent = new Intent(MainActivity.this, PopisPaketa.class);
        startActivityForResult(intent, 1);
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        Boolean connection = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if(connection == false){
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Pogreška u internet vezi");
            alertDialog.setMessage("Molimo Vas, omogućite internetsku vezu kako bi ste se prijavili u aplikaciju.");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        return connection;
    }


}
