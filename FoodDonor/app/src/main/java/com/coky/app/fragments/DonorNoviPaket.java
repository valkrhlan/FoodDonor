package com.coky.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.coky.app.PopisPaketa;
import com.coky.app.R;
import com.coky.app.adapters.VrstaHraneSpinnerAdapter;
import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;
import com.coky.core.entities.SpinnerElement;
import com.coky.core.entities.VrstaJedinica;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonorNoviPaket extends Fragment implements  WsDataLoadedListener{

    Button btnNatragNoviPaket;
    Button btnDodajStvaku;

    Fragment popisPaketa;
    FragmentTransaction fragmentTransaction;

    private Spinner vrstaHraneSpinner;
    private Spinner jedinicaHraneSpinner;
    private VrstaHraneSpinnerAdapter vrstaHraneSpinnerAdapter;
    private VrstaHraneSpinnerAdapter jedinicaSpinnerAdapter;
    private View pomFragmentView;


    public DonorNoviPaket(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View fragmentView = inflater.inflate(R.layout.fragment_donor_novi_paket, container, false);
        pomFragmentView=fragmentView;

        dohvatiVrsteHraneJedinice();
        btnNatragNoviPaket = (Button) fragmentView.findViewById(R.id.btnNatragNoviPaket);
        btnNatragNoviPaket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popisPaketa = getActivity().getSupportFragmentManager().findFragmentByTag("popisPaketa");
                if(popisPaketa == null){
                    popisPaketa = new DonorPopisPaketa();
                }
                ((PopisPaketa)getActivity()).postaviVidljivost();
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.activity_popis_paketa, popisPaketa,"popisPaketa");
                fragmentTransaction.commit();
              //------- Neuspjeli pokušaju handlanja s fragmentom. Bum ostavil valjda bude kaj trebalo da dalje
                //fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                //fragmentTransaction.remove(getActivity().getSupportFragmentManager().findFragmentByTag("noviPaket"));
                //getActivity().getSupportFragmentManager().popBackStack();
                //fragmentTransaction.addToBackStack(null);
                //fragmentTransaction.commit();
                //getActivity().getSupportFragmentManager().popBackStack();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_popis_paketa, getFragmentManager().findFragmentByTag("popisPaketa"), "popisPaketa");
                //-------
            }

        });
        btnDodajStvaku = (Button)fragmentView.findViewById(R.id.btnDodajStavkuNP);
        btnDodajStvaku.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(validacija()){
                    popuniListu();
                }
            }
        });

        return  fragmentView;
    }



 public void dohvatiVrsteHraneJedinice(){
    WsDataLoader wsDataLoader = new WsDataLoader();
     wsDataLoader.dohvatiVrstaJedinica(this);
 }

    @Override
    public void onWsDataLoaded(Object message, int tip, boolean opSuccessful) {

        VrstaJedinica vrstaJedinica = (VrstaJedinica) message;
        List<SpinnerElement> pom=vrstaJedinica.getVrsta();

       SpinnerElement[] vrstaSpinnerElement = new SpinnerElement[pom.size()];
        for (int i=0;i<pom.size();i++){
            vrstaSpinnerElement[i]=pom.get(i);
        }

        vrstaHraneSpinnerAdapter = new VrstaHraneSpinnerAdapter(getActivity().getBaseContext(),R.id.spinnerNazivHraneNP, vrstaSpinnerElement);
        vrstaHraneSpinner = (Spinner)pomFragmentView.findViewById(R.id.spinnerNazivHraneNP);

        vrstaHraneSpinner.setAdapter(vrstaHraneSpinnerAdapter);

        List<SpinnerElement> pomJedinica=vrstaJedinica.getJedinica();
        SpinnerElement[] jedinicaSpinnerElement = new SpinnerElement[pomJedinica.size()];
        for (int i=0;i<pomJedinica.size();i++){
            jedinicaSpinnerElement[i]=pomJedinica.get(i);
        }

        jedinicaSpinnerAdapter = new VrstaHraneSpinnerAdapter(getActivity().getBaseContext(),R.id.spinnerJedinicaKolicineNP,jedinicaSpinnerElement);
        jedinicaHraneSpinner=(Spinner)pomFragmentView.findViewById(R.id.spinnerJedinicaKolicineNP);
        jedinicaHraneSpinner.setAdapter(jedinicaSpinnerAdapter);

    }
    private boolean  validacija() {
        String rez="";
        EditText nazivHrane=(EditText)pomFragmentView.findViewById(R.id.editNazivHraneNP);
        EditText kolicinaHrane=(EditText)pomFragmentView.findViewById(R.id.editKolicinaNP);
        if(nazivHrane.getText().toString().isEmpty()){
            rez="Morate unijeti naziv hrane!";
        }
        if(kolicinaHrane.getText().toString().isEmpty()){
            rez+="Morate unijeti količinu hrane!";
        }else{
            if(kolicinaHrane.getText().toString().equals("0")){
                rez+="Količina mora biti veća od 0";
            }
        }

        if(rez.isEmpty()){
            return true;
        }
        else{
            Toast.makeText(getActivity(),rez,Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private void popuniListu(){
        
    }
}
