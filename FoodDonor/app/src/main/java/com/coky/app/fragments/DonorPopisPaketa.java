package com.coky.app.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coky.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonorPopisPaketa extends Fragment {


    public DonorPopisPaketa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donor_popis_paketa, container, false);
    }

}
