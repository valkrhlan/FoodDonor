package com.coky.app.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.coky.core.entities.SpinnerElement;

/**
 * Created by Valentina on 2.12.2016..
 */

public class VrstaHraneSpinnerAdapter extends ArrayAdapter<SpinnerElement> {

    private Context context;
    private SpinnerElement[] values;

    public VrstaHraneSpinnerAdapter(Context context, int textViewResourceId, SpinnerElement[] values) {
        super(context, textViewResourceId,values);
        this.context = context;
        this.values = values;
    }

   public int getCount(){
       return values.length;
   }
    public SpinnerElement getItem(int position){
        return  values[position];
    }
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
