package com.coky.app.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.coky.core.entities.VrstaHrane;

import java.util.List;

/**
 * Created by Valentina on 2.12.2016..
 */

public class VrstaHraneSpinnerAdapter extends ArrayAdapter<VrstaHrane> {

    private Context context;
    private VrstaHrane[] values;

    public VrstaHraneSpinnerAdapter(Context context, int textViewResourceId, VrstaHrane[] values) {
        super(context, textViewResourceId,values);
        this.context = context;
        this.values = values;
    }

   public int getCount(){
       return values.length;
   }
    public VrstaHrane getItem(int position){
        return  values[position];
    }
    //int nemre umjesto long?? pogledat zakaj nije oke
    public long getItemId(int position){
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values[position].getNaziv());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values[position].getNaziv());
        return label;
    }
}
