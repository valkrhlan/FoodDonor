package com.coky.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.coky.app.R;
import com.coky.app.adapters.StavkeDetaljiListAdapter;
import com.coky.app.adapters.StavkePaketaListAdapter;
import com.coky.app.klase.StavkaPaketa;
import com.coky.core.entities.Paket;
import com.coky.core.entities.Stavka;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonorDetaljiPaketa extends Fragment {

    TextView testni;
    private ArrayAdapter<Stavka> stavkeDetaljiListAdapter;
    private List<Stavka> stavke = new ArrayList<Stavka>();
    private List<Stavka> stavke2 = new ArrayList<Stavka>();

    public DonorDetaljiPaketa() {
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_donor_detalji_paketa, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        //testni = (TextView) getView().findViewById(R.id.testni);
        Bundle data = getArguments();
        Paket paket = (Paket) data.getParcelable("paket");
        //testni.setText(paket.getNaziv_donor());
        Gson gson = new Gson();
        //stavke = (ArrayList<Stavka>) paket.getStavke();
        //Log.d("stavke",stavke.toString());
        Type typeOfListOfIdea = new TypeToken<ArrayList<Stavka>>(){}.getType();
        stavke = gson.fromJson(paket.getStavke().toString(), typeOfListOfIdea);
        /*for(Stavka stavka : stavke){
            Log.d("stavka",stavka.toString());
            stavke2.add(stavka);
        }*/
        stavkeDetaljiListAdapter = new StavkeDetaljiListAdapter(getActivity().getBaseContext(), R.id.DD_stavke, stavke);
        final ListView list=(ListView) getView().findViewById(R.id.DD_stavke);
        list.setAdapter(stavkeDetaljiListAdapter);

    }

}
