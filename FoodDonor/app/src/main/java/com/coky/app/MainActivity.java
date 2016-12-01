package com.coky.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements WsDataLoadedListener {

    private LayoutInflater mInflator;

    @BindView(R.id.prijavaBtn)
    Button btnPrijava;

    @BindView(R.id.textEmail)
    EditText editEmail;

    @BindView(R.id.textPassword)
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    public static final Pattern email_check =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = email_check.matcher(emailStr);
        return matcher.find();
    }

    @OnClick(R.id.prijavaBtn)
    public void prijavaBtnClick(View view){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(0,editEmail.getText().toString());
        arrayList.add(1,editPassword.getText().toString());
        if(!editEmail.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty()){
            if(validate(editEmail.getText().toString())){
                WsDataLoader wsDataLoader = new WsDataLoader();
                wsDataLoader.prijava(arrayList, this);
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
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Fizička osoba",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, RegistracijaFizickiKorisnik.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Odustani",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onWsDataLoaded(String message, final int tip, final boolean opSuccessful) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Rezultat prijave");
        alertDialog.setMessage(message);
        if(message.startsWith("U")){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("tipKorisnika", tip);
            editor.apply();
            Intent intent = new Intent(MainActivity.this, PopisPaketa.class);
            startActivity(intent);
            editPassword.setText("");
            editEmail.setText("");
        }
        else{
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }
}
