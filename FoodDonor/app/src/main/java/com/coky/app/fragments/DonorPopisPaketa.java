package com.coky.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coky.app.R;
import com.coky.app.adapters.PaketAdapter;
import com.coky.core.entities.Paket;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonorPopisPaketa extends ListFragment {
    //-----Ovo neznam jel uopće potrebno, budući da bi moglo stvarati problema, zakomentiral sam
    /*public DonorPopisPaketa() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_donor_popis_paketa, container, false);
    }*/

    private ArrayList<Paket> paketi;
    private PaketAdapter paketAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        paketi = new ArrayList<Paket>();
        Paket paket1 = new Paket(1,"1.12.2016","Dostupno");
        Paket paket2 = new Paket(2,"29.11.2016","Preuzeto");
        Paket paket3 = new Paket(1337, "11.9.2020.", "Dostupno");
        paketi.add(paket1);
        paketi.add(paket2);
        paketi.add(paket3);
        paketAdapter = new PaketAdapter(getActivity(), paketi);
        setListAdapter(paketAdapter);
        registerForContextMenu(getListView());
    }



}
