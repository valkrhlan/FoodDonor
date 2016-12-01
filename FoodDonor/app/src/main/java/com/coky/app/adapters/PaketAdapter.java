package com.coky.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.coky.app.R;
import com.coky.core.entities.Paket;

import java.util.ArrayList;

/**
 * Created by Čoky on 1.12.2016..
 */

public class PaketAdapter extends ArrayAdapter<Paket> {

    public static class ViewHolder{
        TextView paketBroj;
        TextView paketDatum;
        TextView paketStatus;
    }

    public PaketAdapter(Context context, ArrayList<Paket> paketi){
        super(context, 0, paketi);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Paket paket = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.paket_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.paketBroj = (TextView) convertView.findViewById(R.id.paketBroj);
            viewHolder.paketDatum = (TextView) convertView.findViewById(R.id.paketDatum);
            viewHolder.paketStatus = (TextView) convertView.findViewById(R.id.paketStatus);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.paketBroj.setText(Long.toString(paket.getBroj()));
        viewHolder.paketDatum.setText(paket.getDatum());
        viewHolder.paketStatus.setText(paket.getStatus());

        return convertView;
    }
}
