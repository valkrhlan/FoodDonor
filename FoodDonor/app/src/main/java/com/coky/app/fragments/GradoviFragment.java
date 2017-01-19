package com.coky.app.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
 */
public class GradoviFragment extends Fragment implements WsDataLoadedListener{

    @BindView(R.id.gradList)
    ListView gradList;

    private ArrayList<Gradovi> gradovi;
    private GradoviAdapter gradoviAdapter;
    private FragmentManager fragmentManager = getFragmentManager();
    private FragmentTransaction fragmentTransaction;


    public GradoviFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getActivity().setTitle("Odaberite grad");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gradovi, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    private void getGradovi(){
        WsDataLoader wdl = new WsDataLoader();

    }


    private void addGradoviToArray(Gradovi grad){
        if(grad == null){
            gradovi = new ArrayList<Gradovi>();
        }
        gradovi.add(grad);
    }

    private void setGradoviAdapter(){
        gradoviAdapter = new GradoviAdapter(getActivity(), gradovi);
        gradList.setAdapter(gradoviAdapter);
        gradList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("gradKorisnik", gradovi.get(i).getNaziv() );
                editor.apply();

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(GradoviFragment.this);
                fragmentManager.popBackStack();
                fragmentTransaction.commit();
            }
        });
    }


    @Override
    public void onWsDataLoaded(Object message, int tip) {
        List<Gradovi> gradoviPomocnaLista = (List<Gradovi>) message;
        for(Gradovi grad : gradoviPomocnaLista){

            //paket.setId(Integer.toString(++brojPaketa));
            addGradoviToArray(grad);
        }
        setGradoviAdapter();
    }

}
