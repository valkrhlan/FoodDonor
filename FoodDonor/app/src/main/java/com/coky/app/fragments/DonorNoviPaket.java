package com.coky.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.coky.app.PopisPaketa;
import com.coky.app.R;
import com.coky.app.adapters.VrstaHraneSpinnerAdapter;
import com.coky.app.loaders.WsDataLoadedListener;
import com.coky.app.loaders.WsDataLoader;
import com.coky.core.entities.SpinnerElement;
import com.coky.core.entities.VrstaJedinica;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonorNoviPaket extends Fragment implements  WsDataLoadedListener{

    Button btnNatragNoviPaket;
    Fragment popisPaketa;
    FragmentTransaction fragmentTransaction;

    private Spinner vrstaHraneSpinner;
    private VrstaHraneSpinnerAdapter vrstaHraneSpinnerAdapter;

    public DonorNoviPaket(){
        // Required empty public constructor
    }

    @BindView(R.id.btnNoviPaket)
    Button buttonNoviPaket;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View fragmentView = inflater.inflate(R.layout.fragment_donor_novi_paket, container, false);
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


        //mock up data
        SpinnerElement[] SpinnerElement = new SpinnerElement[2];
        SpinnerElement[0] = new SpinnerElement("1","riba");
        SpinnerElement[1] = new SpinnerElement("2","povrće");

        vrstaHraneSpinnerAdapter = new VrstaHraneSpinnerAdapter(getActivity().getBaseContext(),R.id.spinnerNazivHraneNP, SpinnerElement);
        vrstaHraneSpinner = (Spinner)fragmentView.findViewById(R.id.spinnerNazivHraneNP);

        vrstaHraneSpinner.setAdapter(vrstaHraneSpinnerAdapter);

        return  fragmentView;
    }



 public void dohvatiVrsteHraneJedinice(){
    WsDataLoader wsDataLoader = new WsDataLoader();
     wsDataLoader.dohvatiVrstaJedinica(this);


 }

    @Override
    public void onWsDataLoaded(Object message, int tip, boolean opSuccessful) {
        Toast.makeText(getActivity(),"wohoooo",Toast.LENGTH_SHORT).show();
    }
}
