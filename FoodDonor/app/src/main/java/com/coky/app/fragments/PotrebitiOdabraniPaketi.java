package com.coky.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
public class PotrebitiOdabraniPaketi extends Fragment implements WsDataLoadedListener {

    @BindView(R.id.POP_natrag)
    Button btnNatrag;

    private View fragmentView;
    private ListView listView;
    private String email;

    private ArrayList<Paket> paketi = new ArrayList<Paket>();
    private PaketAdapter paketAdapter;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private WsDataLoader wsDataLoader;

    public PotrebitiOdabraniPaketi() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_potrebiti_odabrani_paketi, container, false);
        listView = (ListView) fragmentView.findViewById(R.id.potrebitiOdabraniPaketi);
        ButterKnife.bind(this, fragmentView);
        fragmentManager = getActivity().getSupportFragmentManager();
        return fragmentView;
    }

    @Override
    public void onStart(){
        super.onStart();
        email=((GlavnaAktivnost)getActivity()).getEmailKorisnika();
        ((GlavnaAktivnost)getActivity()).isNetworkAvailable();
        paketi.clear();
        wsDataLoader = new WsDataLoader();
        wsDataLoader.preuzmiPakete(email, "da", this);
    }

    private void addPaketToArray(Paket paket){
        if(paketi == null){
            paketi = new ArrayList<Paket>();
        }
        paketi.add(paket);
    }

    private void setPaketAdapter(){
        paketAdapter = new PaketAdapter(getActivity(), paketi);
        listView.setAdapter(paketAdapter);
    }

    @OnClick(R.id.POP_natrag)
    public void btnNatragOnClick() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(PotrebitiOdabraniPaketi.this);
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
    }

    @Override
    public void onWsDataLoaded(Object message, int tip) {
        List<Paket> paketiPomocnaLista = (List<Paket>) message;
        for(Paket paket : paketiPomocnaLista){
            addPaketToArray(paket);
        }
        setPaketAdapter();
    }
}