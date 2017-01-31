package com.coky.app.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.coky.app.GlavnaAktivnost;
import com.coky.app.R;
import com.coky.app.adapters.GradoviAdapter;
import com.coky.app.adapters.PaketAdapter;
import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;
import com.coky.core.entities.Gradovi;
import com.coky.core.entities.Paket;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Klasa za prikaz paketa čiji donori se nalaze u odabranog gradu
 */
public class GradoviFragment extends Fragment implements WsDataLoadedListener{

    @BindView(R.id.gradList)
    ListView gradList;

    private ArrayList<Gradovi> gradovi;
    private GradoviAdapter gradoviAdapter;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gradovi, container, false);
        ButterKnife.bind(this,view);
        fragmentManager = getActivity().getSupportFragmentManager();
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d("grad", "1. onStart");
        getGradovi();
    }

    /**
     * metoda za dohvaćanje svih gradova iz baze pomoću ws-a
     */
    private void getGradovi(){
        Log.d("grad", "2. getGradovi");
        WsDataLoader wdl = new WsDataLoader();
        wdl.odaberiGrad(this);
    }

    /**
     * Metoda koja u listu gradova dodaje novi grad
     * @param grad novi grad koji se dodaje u listu gradova
     */
    private void addGradoviToArray(Gradovi grad){
        if(gradovi == null){
            gradovi = new ArrayList<Gradovi>();
        }
        gradovi.add(grad);
    }

    /**
     * Metoda koja pokreće glavnu aktivnost s novim odabranim gradom
     * @param gradIndex indeks grada koji je odabran
     */
    private void azurirajGrad(int gradIndex){
        String grad = gradovi.get(gradIndex).getNaziv();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("grad", grad);
        editor.apply();
        ((GlavnaAktivnost)getActivity()).setGrad(grad);
    }

    private void setGradoviAdapter(){
        gradoviAdapter = new GradoviAdapter(getActivity(), gradovi);
        gradList.setAdapter(gradoviAdapter);
        gradList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                azurirajGrad(i);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(GradoviFragment.this);
                fragmentManager.popBackStack();
                fragmentTransaction.commit();
            }
        });
    }


    @Override
    public void onWsDataLoaded(Object message, int tip) {
        Log.d("grad", "3. response");
        List<Gradovi> gradoviPomocnaLista = (List<Gradovi>) message;
        for(Gradovi grad : gradoviPomocnaLista){

            //paket.setId(Integer.toString(++brojPaketa));
            addGradoviToArray(grad);
        }
        setGradoviAdapter();
    }

}
