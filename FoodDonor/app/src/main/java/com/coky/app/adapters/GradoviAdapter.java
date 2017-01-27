package com.coky.app.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.coky.app.R;
import com.coky.core.entities.Gradovi;
import com.coky.core.entities.Paket;

import java.util.ArrayList;

/**
 * Created by Aleksandar on 19.1.2017..
 */

public class GradoviAdapter extends ArrayAdapter<Gradovi> {



    public static class ViewHolder{
        TextView naziv;
    }

    public GradoviAdapter(Context context, ArrayList<Gradovi> gradovi){
        super(context, 0, gradovi);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Gradovi grad = getItem(position);
        Log.d("grad",grad.toString());
        GradoviAdapter.ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.paket_item, parent, false);
            viewHolder = new GradoviAdapter.ViewHolder();
            viewHolder.naziv = (TextView) convertView.findViewById(R.id.gradItem);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (GradoviAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.naziv.setText(grad.getNaziv().toString());

        return convertView;
    }
}
