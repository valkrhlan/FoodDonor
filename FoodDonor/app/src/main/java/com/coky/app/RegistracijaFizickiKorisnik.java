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
import android.widget.Toast;

import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;
import com.coky.core.entities.Korisnik;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistracijaFizickiKorisnik extends AppCompatActivity implements WsDataLoadedListener {

    @BindView(R.id.buttonRegistrirajSeRF)
    Button btnRRF;

    @BindView(R.id.editAdresaRF)
    EditText editAdresa;

    @BindView(R.id.editMailRF)
    EditText editMail;

    @BindView(R.id.editGradRF)
    EditText editGrad;

    @BindView(R.id.editImeRF)
    EditText editIme;

    @BindView(R.id.editPrezimeRF)
    EditText editPrezime;

    @BindView(R.id.editKontaktRF)
    EditText editKontakt;

    @BindView(R.id.editLozinkaRF)
    EditText editLozinka;

    @BindView(R.id.editPonovljenaRF)
    EditText editPonovljena;

    @BindView(R.id.editOibRF)
    EditText editOib;

    @BindView(R.id.buttonOdustaniRF)
    Button odustani;

    public static final Pattern email_check =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern letters_only_check =
            Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija_fizicki);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Registracija fizičkog korisnika");
    }

    @OnClick(R.id.buttonRegistrirajSeRF)
    public void registracijBtnClick(View view){
        Korisnik korisnik = new Korisnik(
                editMail.getText().toString(),
                editLozinka.getText().toString(),
                editOib.getText().toString(),
                editGrad.getText().toString(),
                editAdresa.getText().toString(),
                editKontakt.getText().toString(),
                "",
                editIme.getText().toString(),
                editPrezime.getText().toString(),
                ""
        );
        if(!editMail.getText().toString().isEmpty() && !editLozinka.getText().toString().isEmpty()  &&
                !editOib.getText().toString().isEmpty() && !editGrad.getText().toString().isEmpty() &&
                !editAdresa.getText().toString().isEmpty() && !editKontakt.getText().toString().isEmpty() &&
                !editIme.getText().toString().isEmpty() && !editPrezime.getText().toString().isEmpty()) {

            if(!validate(editMail.getText().toString()) || !validate_letters(editGrad.getText().toString()) ||
                    !validate_letters(editIme.getText().toString()) || !validate_letters(editPrezime.getText().toString()) ||
                    !editLozinka.getText().toString().matches(editPonovljena.getText().toString())){
                Toast.makeText(this,"Jedan ili više unosa ne prati uzorak ili lozinke nisu iste!",Toast.LENGTH_LONG).show();

            }
            else {
                if(isNetworkAvailable() == true){
                    Toast.makeText(this,"Uspješan unos!",Toast.LENGTH_SHORT).show();
                    WsDataLoader wsDataLoader = new WsDataLoader();
                    wsDataLoader.registracijaFizicka(korisnik ,this);
                }
            }
    }
        else {
            Toast.makeText(this, "Popunite sva polja za unos!", Toast.LENGTH_LONG).show();
        }

}

    @OnClick(R.id.buttonOdustaniRF)
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
            AlertDialog alertDialog = new AlertDialog.Builder(RegistracijaFizickiKorisnik.this).create();
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