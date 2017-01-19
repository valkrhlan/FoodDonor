package com.coky.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.coky.app.GlavnaAktivnost;
import com.coky.app.R;
import com.coky.app.adapters.PaketAdapter;
import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;
import com.coky.core.entities.Paket;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopisPaketa extends Fragment implements WsDataLoadedListener {

    private View fragmentView;
    private ListView listView;

    @BindView(R.id.btnDesno)
    FloatingActionButton btnDesno;

    private String email;
    private int tipKorisnika;

    private ArrayList<Paket> paketi = new ArrayList<Paket>();
    private PaketAdapter paketAdapter;

    private FragmentManager fragmentManager;
    private WsDataLoader wsDataLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_donor_popis_paketa, container, false);
        listView = (ListView) fragmentView.findViewById(R.id.popisPaketa);
        ButterKnife.bind(this, fragmentView);
        fragmentManager = getActivity().getSupportFragmentManager();
        return fragmentView;
    }

    @Override
    public void onStart(){
        super.onStart();
        setFloatingButtonIcons();
        email=((GlavnaAktivnost)getActivity()).getEmailKorisnika();
        tipKorisnika = ((GlavnaAktivnost)getActivity()).getTipKorisnika();
        ((GlavnaAktivnost)getActivity()).isNetworkAvailable();
        paketi.clear();
        wsDataLoader = new WsDataLoader();
        wsDataLoader.preuzmiPakete(email, "ne", this);
    }

    private void setFloatingButtonIcons(){
        if(tipKorisnika == 1){   //DONOR
            btnDesno.setImageResource(R.drawable.ic_action_adding);
            btnDesno.setVisibility(View.VISIBLE);
        }else if(tipKorisnika == 3){   //POTREBITI
            btnDesno.setImageResource(R.drawable.ic_action_odabrani);
            btnDesno.setVisibility(View.VISIBLE);
        }
    }

    private void addPaketToArray(Paket paket){
        if(paketi == null){
            paketi = new ArrayList<Paket>();
        }
        paketi.add(paket);
    }

    private void setPaketAdapter(ArrayList<Integer> brojPaketa){
        setFloatingButtonIcons();
        paketAdapter = new PaketAdapter(getActivity(), paketi, brojPaketa);
        listView.setAdapter(paketAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment noviPaket = new DetaljiPaketa();
                Bundle args = new Bundle();
                args.putParcelable("paket",paketi.get(i));
                args.putBoolean("pogledIzListeOdabranih", false);
                noviPaket.setArguments(args);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.activity_popis_paketa, noviPaket);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @OnClick(R.id.btnDesno)
    public void btnDesnoOnClick(){
        if(tipKorisnika == 1){  //DONOR
            Fragment noviPaket = new DonorNoviPaket();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.activity_popis_paketa, noviPaket);
            transaction.addToBackStack(null);
            transaction.commit();
        }else if(tipKorisnika == 3) {  //POTREBITI
            Fragment odabraniPaketi = new PotrebitiOdabraniPaketi();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.activity_popis_paketa, odabraniPaketi);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onWsDataLoaded(Object message, int tip) {
        List<Paket> paketiPomocnaLista = (List<Paket>) message;
        ArrayList<Integer> brojPaketa = new ArrayList<Integer>();
        int trenutni = 0;
        for(Paket paket : paketiPomocnaLista){
            brojPaketa.add(++trenutni);
            //paket.setId(Integer.toString(++brojPaketa));
            addPaketToArray(paket);
        }
        setPaketAdapter(brojPaketa);
    }


}
