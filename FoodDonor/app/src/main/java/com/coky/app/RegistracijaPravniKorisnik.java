package com.coky.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;
import com.coky.core.entities.Korisnik;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistracijaPravniKorisnik extends AppCompatActivity implements WsDataLoadedListener{

    @BindView(R.id.buttonRegistrirajSeRP)
    Button btnRegistrirajSeRP;

    @BindView(R.id.editMailRP)
    EditText editMail;

    @BindView(R.id.editLozinkaRP)
    EditText editLozinka;

    @BindView(R.id.editPonovljenaRP)
    EditText editPonovljena;

    @BindView(R.id.editOibRP)
    EditText editOib;

    @BindView(R.id.editGradRP)
    EditText editGrad;

    @BindView(R.id.editAdresaRP)
    EditText editAdresa;

    @BindView(R.id.editKontaktRP)
    EditText editKontakt;

    @BindView(R.id.radioDonorRP)
    RadioButton radioDonor;

    @BindView(R.id.radioPotrebitiRP)
    RadioButton radioPotrebiti;

    @BindView(R.id.editNazivRP)
    EditText editNaziv;

    @BindView(R.id.editImeRP)
    EditText editIme;

    @BindView(R.id.editPrezimeRP)
    EditText editPrezime;

    @BindView(R.id.buttonOdustaniRP)
    Button odustani;


    public static final Pattern email_check =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern letters_only_check =
            Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);

    public static final Pattern numbers_only_check =
            Pattern.compile("^[1-9]+$", Pattern.CASE_INSENSITIVE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija_pravni);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Registracija pravnog korisnika");
    }

    @OnClick(R.id.buttonRegistrirajSeRP)
    public void registracijBtnClick(View view){
        String tip = "";

        if(radioDonor.isChecked()){
            tip = "donor";
        }
        else if (radioPotrebiti.isChecked()){
            tip="potrebiti";
        }
        Korisnik korisnik = new Korisnik(
                editMail.getText().toString(),
                editLozinka.getText().toString(),
                editOib.getText().toString(),
                editGrad.getText().toString(),
                editAdresa.getText().toString(),
                editKontakt.getText().toString(),
                editNaziv.getText().toString(),
                editIme.getText().toString(),
                editPrezime.getText().toString(),
                tip
        );
        if(!editMail.getText().toString().isEmpty() && !editLozinka.getText().toString().isEmpty()  &&
                !editOib.getText().toString().isEmpty() && !editGrad.getText().toString().isEmpty() &&
                !editAdresa.getText().toString().isEmpty() && !editKontakt.getText().toString().isEmpty() &&
                !editNaziv.getText().toString().isEmpty() && !editIme.getText().toString().isEmpty() && !editPrezime.getText().toString().isEmpty())
        {
            if(     !validate(editMail.getText().toString()) ||
                    !validate_letters(editGrad.getText().toString()) ||
                    !editLozinka.getText().toString().matches(editPonovljena.getText().toString()))
                    Toast.makeText(this,"Jedan ili više unosa ne prati uzorak ili lozinke nisu iste!",Toast.LENGTH_LONG).show();
            else {
                if(isNetworkAvailable() == true){
                    Toast.makeText(this,"Uspješan unos!",Toast.LENGTH_SHORT).show();
                    WsDataLoader wsDataLoader = new WsDataLoader();
                    wsDataLoader.registracijaPravna(korisnik, this);
                }
            }
        }else {
                Toast.makeText(this,"Popunite sva polja za unos!",Toast.LENGTH_LONG).show();

            }

}

    @OnClick(R.id.buttonOdustaniRP)
    public void odustaniClick(View view){
        finish();
    }

    @Override
    public void onWsDataLoaded(Object message, int tip) {
        Toast.makeText(this,"Uspješna registracija!",Toast.LENGTH_SHORT).show();
        finish();
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = email_check.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validate_letters(String letters) {
        Matcher matcher = letters_only_check.matcher(letters);
        return matcher.find();
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        Boolean connection = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if(connection == false){
            AlertDialog alertDialog = new AlertDialog.Builder(RegistracijaPravniKorisnik.this).create();
            alertDialog.setTitle("Pogreška u internet vezi");
            alertDialog.setMessage("Molimo Vas, omogućite internetsku vezu kako bi ste registrirali korisnika");
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