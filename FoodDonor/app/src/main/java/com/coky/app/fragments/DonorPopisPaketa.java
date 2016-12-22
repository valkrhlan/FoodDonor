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
import android.widget.Toast;

import com.coky.app.PopisPaketa;
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
public class DonorPopisPaketa extends Fragment implements WsDataLoadedListener {

    private View fragmentView;
    private ListView listView;

    @BindView(R.id.btnNoviPaket)
    Button btnNoviPaket;

    private String email;

    private ArrayList<Paket> paketi;
    private PaketAdapter paketAdapter;

    private FragmentManager fragmentManager;
    private WsDataLoader wsDataLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_donor_popis_paketa, container, false);
        listView = (ListView) fragmentView.findViewById(R.id.popisPaketa);
        btnNoviPaket = (Button) fragmentView.findViewById(R.id.btnNoviPaket);
        ButterKnife.bind(this, fragmentView);
        fragmentManager = getActivity().getSupportFragmentManager();
        return fragmentView;
    }

    @Override
    public void onStart(){
        super.onStart();
        email=((PopisPaketa)getActivity()).getEmailKorisnika();
        paketi = null;
        ((PopisPaketa)getActivity()).isNetworkAvailable();
        wsDataLoader = new WsDataLoader();
        wsDataLoader.preuzmiPakete(email, this);
    }

    /*private void mockPaketi(){
        addPaketToArray(new Paket(1,"1.12.2016","Dostupno"));
        addPaketToArray(new Paket(2,"29.11.2016","Preuzeto"));
        addPaketToArray(new Paket(1337, "11.9.2020.", "Dostupno"));
    }*/

    private void addPaketToArray(Paket paket){
        if(paketi == null){
            paketi = new ArrayList<Paket>();
        }
        paketi.add(paket);
    }

    private void setPaketAdapter(){
        //----------------- MOCK DATA --------------------------
        //mockPaketi();
        //------------------------------------------------------
        paketAdapter = new PaketAdapter(getActivity(), paketi);
        listView.setAdapter(paketAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"Kliknut paket - pozicija: " + i + ", row ID: " + l,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnNoviPaket)
    public void btnNoviPaketClick(){
        Fragment noviPaket = new DonorNoviPaket();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_popis_paketa, noviPaket);
        transaction.addToBackStack(null);
        /*fragmentManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        wsDataLoader.preuzmiPakete(email, DonorPopisPaketa.this);
                    }
                });*/
        transaction.commit();
    }

    @Override
    public void onWsDataLoaded(Object message, int tip) {
        List<Paket> paketi = (List<Paket>) message;
        for(Paket paket : paketi){
            addPaketToArray(paket);
        }
        setPaketAdapter();
    }


}
