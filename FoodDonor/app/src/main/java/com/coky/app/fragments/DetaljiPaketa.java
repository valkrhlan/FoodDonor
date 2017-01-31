package com.coky.app.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.coky.app.GlavnaAktivnost;
import com.coky.app.MapaPaket;
import com.coky.app.R;
import com.coky.app.adapters.StavkeDetaljiListAdapter;
import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;
import com.coky.core.entities.Paket;
import com.coky.core.entities.Stavka;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetaljiPaketa extends Fragment implements WsDataLoadedListener {

    @BindView(R.id.DD_naziv_donor)
    TextView nazivDonor;

    @BindView(R.id.DD_v_kreiranja)
    TextView vrijemeKreiranja;

    @BindView(R.id.DD_naziv_volonter)
    TextView nazivVolonter;

    @BindView(R.id.DD_v_preuzeto)
    TextView vrijemePreuzeto;

    @BindView(R.id.DD_stavke)
    ListView list;

    @BindView(R.id.DD_natrag)
    TextView btnNatrag;

    @BindView(R.id.DD_odaberi)
    TextView btnOdaberi;

    @BindView(R.id.btnLijevo)
    FloatingActionButton btnLijevo;

    @BindView(R.id.btnDesno)
    FloatingActionButton btnDesno;

    @BindView(R.id.DD_karta)
    TextView btnKarta;

    private ArrayAdapter<Stavka> stavkeDetaljiListAdapter;
    private List<Stavka> stavke = new ArrayList<Stavka>();

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private int tipKorisnika;
    private String email;
    private Paket paket;
    private Bundle data;

    public DetaljiPaketa() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_donor_detalji_paketa, container, false);
        ButterKnife.bind(this, fragmentView);
        fragmentManager = getActivity().getSupportFragmentManager();
        tipKorisnika = ((GlavnaAktivnost)getActivity()).getTipKorisnika();
        email = ((GlavnaAktivnost)getActivity()).getEmailKorisnika();
        data = getArguments();
        paket = (Paket) data.getParcelable("paket");
        setButtonVisibility();
        return fragmentView;
    }

    @Override
    public void onStart(){
        super.onStart();
        addStavkeToArray();
        setStavkeDetaljiListAdapter();
        setTextViews();
    }

    private void setButtonVisibility(){
        btnLijevo.setVisibility(View.GONE);
        btnDesno.setVisibility(View.GONE);
        if(tipKorisnika == 1 || data.getBoolean("pogledIzListeOdabranih") == true){
            btnOdaberi.setVisibility(View.GONE);
            btnKarta.setVisibility(View.GONE);
        }else{
            btnOdaberi.setVisibility(View.VISIBLE);
            btnKarta.setVisibility(View.VISIBLE);
        }
        if(tipKorisnika == 3){
            if(data.getBoolean("pogledIzListeOdabranih") == false){
                btnLijevo.setImageResource(R.drawable.ic_action_itno);
                btnLijevo.setVisibility(View.VISIBLE);
            }else{
                btnDesno.setImageResource(R.drawable.ic_action_evidentirano);
                btnDesno.setVisibility(View.VISIBLE);
                btnKarta.setVisibility(View.GONE);
            }

        }
    }

    @OnClick(R.id.DD_karta)
    public void btnKartaOnClick(){
        Intent intent = new Intent(getActivity(),MapaPaket.class);
        Bundle mBundle = new Bundle();
        mBundle.putString("paketID", paket.getId());
        intent.putExtras(mBundle);
        startActivity(intent);
    }

    private void addStavkeToArray(){
        Gson gson = new Gson();
        Type ListaStavkiJSON = new TypeToken<ArrayList<Stavka>>(){}.getType(); //ovo je drugi način dekomponiranja JSON formata i punjena liste koja je custom tipa (prvi se nalazi u fragmentu PopisPaketa)
        stavke = gson.fromJson(paket.getStavke().toString(), ListaStavkiJSON);
    }

    private void setStavkeDetaljiListAdapter(){
        stavkeDetaljiListAdapter = new StavkeDetaljiListAdapter(getActivity().getBaseContext(), R.id.DD_stavke, stavke);
        list.setAdapter(stavkeDetaljiListAdapter);
    }

    private void setTextViews(){
        nazivDonor.setText("Naziv donora: " + paket.getNaziv_donor());
        vrijemeKreiranja.setText("Paket kreiran: " + paket.getV_kreiranja());
        nazivVolonter.setText(paket.getNaziv_volonter() != "" ? "Naziv volontera: " + paket.getNaziv_volonter() : "Paket nije još preuzet!");
        vrijemePreuzeto.setText(paket.getV_preuzeto() != null ? "Paket preuzet: " +  paket.getV_preuzeto() : "");
    }

    @OnClick(R.id.DD_natrag)
    public void btnNatragOnClick(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(DetaljiPaketa.this);
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
    }

    @OnClick(R.id.DD_odaberi)
    public void btnOdaberiOnClick(){
        ((GlavnaAktivnost)getActivity()).isNetworkAvailable();
        if(tipKorisnika == 2){
            WsDataLoader wsDataLoader = new WsDataLoader();
            wsDataLoader.odaberiPaketVolonter(email,paket.getId(),this);
        }else{
            WsDataLoader wsDataLoader = new WsDataLoader();
            wsDataLoader.odaberiPaketPotrebiti(email,"ne",paket.getId(),this);
        }

    }

    @OnClick(R.id.btnLijevo)
    public void btnLijevoOnClick(){
        ((GlavnaAktivnost)getActivity()).isNetworkAvailable();
        paket.setHitno("1");
        WsDataLoader wsDataLoader = new WsDataLoader();
        wsDataLoader.odaberiPaketPotrebiti(email,"da",paket.getId(),this);
    }

    @OnClick(R.id.btnDesno)
    public void btnDesnoOnClick(){
        ((GlavnaAktivnost)getActivity()).isNetworkAvailable();
        WsDataLoader wsDataLoader = new WsDataLoader();
        wsDataLoader.evidentirajDolazak(paket.getId(),this);
    }

    @Override
    public void onWsDataLoaded(Object message, int tip) {
        WsDataLoader wsDataLoader = new WsDataLoader();
        String email=((GlavnaAktivnost)getActivity()).getEmailKorisnika();
        String titleNotif = "";
        String messageNotif = "";
        if(data.getBoolean("pogledIzListeOdabranih") == true){
            titleNotif = "Paket stigao na odredište!";
            messageNotif = "Naziv potrebitog: " + paket.getNaziv_potrebitog() + ", Naziv volontera: " + paket.getNaziv_volonter();
        }else{
            if(paket.getHitno() != "1"){
                titleNotif = "Novi paket spreman za prijevoz!";
            }else{
                titleNotif = "HITNO! Novi paket spreman za prijevoz!";
            }
            messageNotif = "Naziv donora: " + paket.getNaziv_donor();
        }
        Log.d("paketNoviTitle", titleNotif);
        Log.d("paketNoviMessage", messageNotif);
        wsDataLoader.posaljiNotif(email,titleNotif, messageNotif, this);
        Toast.makeText(getActivity().getBaseContext(), message.toString(), Toast.LENGTH_SHORT).show();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(DetaljiPaketa.this);
        fragmentManager.popBackStack();
        fragmentTransaction.commit();

    }
}
