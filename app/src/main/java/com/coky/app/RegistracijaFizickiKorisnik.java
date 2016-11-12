package com.coky.app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    public static final Pattern email_check =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = email_check.matcher(emailStr);
        return matcher.find();
    }

    public static final Pattern letters_only_check =
            Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);

    public static boolean validate_letters(String letters) {
        Matcher matcher = letters_only_check.matcher(letters);
        return matcher.find();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija_fizicki);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonRegistrirajSeRF)
    public void registracijBtnClick(View view){
        ArrayList<String> arrayLisRegistracija = new ArrayList<>();
        arrayLisRegistracija.add(0,editMail.getText().toString());
        arrayLisRegistracija.add(1,editLozinka.getText().toString());
        arrayLisRegistracija.add(2,editOib.getText().toString());
        arrayLisRegistracija.add(3,editGrad.getText().toString());
        arrayLisRegistracija.add(4,editAdresa.getText().toString());
        arrayLisRegistracija.add(5,editKontakt.getText().toString());
        arrayLisRegistracija.add(6,editIme.getText().toString());
        arrayLisRegistracija.add(7,editPrezime.getText().toString());

        if(!editMail.getText().toString().isEmpty() && !editLozinka.getText().toString().isEmpty()  &&
                !editOib.getText().toString().isEmpty() && !editGrad.getText().toString().isEmpty() &&
                !editAdresa.getText().toString().isEmpty() && !editKontakt.getText().toString().isEmpty() &&
                !editIme.getText().toString().isEmpty() && !editPrezime.getText().toString().isEmpty())
        {
            if(!validate(editMail.getText().toString()) || !validate_letters(editGrad.getText().toString()) ||
                    !validate_letters(editIme.getText().toString()) || !validate_letters(editPrezime.getText().toString()) ||
                    !editLozinka.getText().toString().matches(editPonovljena.getText().toString()))
                Toast.makeText(this,"Jedan ili više unosa ne prati uzorak ili lozinke nisu iste!",Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(this,"Uspješan unos!",Toast.LENGTH_SHORT).show();
                WsDataLoader wsDataLoader = new WsDataLoader();
                wsDataLoader.registracijaFizicka(arrayLisRegistracija,this);
            }
    }
        else{
            Toast.makeText(this,"Popunite sva polja za unos!",Toast.LENGTH_LONG).show();
                    }



}

    @Override
    public void onWsDataLoaded(String message, String status, boolean opSuccessful) {
        Toast.makeText(this,"Uspješna registracija!",Toast.LENGTH_SHORT).show();
        finish();
    }
}