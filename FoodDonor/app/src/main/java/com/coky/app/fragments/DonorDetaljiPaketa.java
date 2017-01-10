package com.coky.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coky.app.R;
import com.coky.core.entities.Paket;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonorDetaljiPaketa extends Fragment {

    TextView testni;

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
        /*testni = (TextView) getView().findViewById(R.id.testni);
        Bundle data = getArguments();
        Paket paket = (Paket) data.getParcelable("paket");
        testni.setText(paket.getNaziv_donor());*/
    }

}
