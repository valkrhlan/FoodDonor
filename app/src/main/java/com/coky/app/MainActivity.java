package com.coky.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.prijavaBtn)
    public void prijavaBtnClick(View view){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(0,editEmail.getText().toString());
        arrayList.add(1,editPassword.getText().toString());
        if(editEmail.getText() == null){
            return;
        }
        WsDataLoader wsDataLoader = new WsDataLoader();
        wsDataLoader.prijava(arrayList, this);
    }


    @Override
    public void onWsDataLoaded(String message, String status, boolean opSuccessful) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("rezultat prijave");
        alertDialog.setMessage("Status: " + status + "\nMessage: " + message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
