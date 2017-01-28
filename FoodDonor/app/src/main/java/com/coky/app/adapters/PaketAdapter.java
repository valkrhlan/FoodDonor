package com.coky.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;
import android.util.Log;
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

    private ArrayList<Integer> brojPaketa;
    public static class ViewHolder{
        TextView paketId;
        TextView paketVrijemeKreiranja;
        TextView paketPreuzimanje;
        TextView paketVrijemePreuzimanja;
    }

    public PaketAdapter(Context context, ArrayList<Paket> paketi, ArrayList<Integer> brojPaketa){
        super(context, 0, paketi);
        this.brojPaketa = brojPaketa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Paket paket = getItem(position);
        //Log.d("paket",paket.toString());
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.paket_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.paketId = (TextView) convertView.findViewById(R.id.idPaket);
            viewHolder.paketVrijemeKreiranja = (TextView) convertView.findViewById(R.id.v_kreiranjaPaket);
            viewHolder.paketPreuzimanje = (TextView) convertView.findViewById(R.id.preuzimanjePaket);
            viewHolder.paketVrijemePreuzimanja = (TextView) convertView.findViewById(R.id.v_preuzetoPaket);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.paketId.setText(brojPaketa.get(position).toString());
        viewHolder.paketVrijemeKreiranja.setText(paket.getV_kreiranja());

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String email = prefs.getString("emailKorisnika","test@test.test");
        int tipK = prefs.getInt("tipKorisnika", 0);


            if(paket.getId_volonter() != null){
                convertView.setBackgroundColor(Color.parseColor("#42f462"));
                viewHolder.paketPreuzimanje.setText("Preuzeo volonter " + paket.getId_volonter());
                viewHolder.paketVrijemePreuzimanja.setText("Preuzeto: " + paket.getV_preuzeto());
                if(tipK==2){
                    viewHolder.paketPreuzimanje.setText("Ime donora: " + paket.getNaziv_donor());
                    viewHolder.paketVrijemePreuzimanja.setText("Ime potrebitog: " + paket.getNaziv_potrebitog());
                }else{
                    viewHolder.paketPreuzimanje.setText("Preuzeo volonter " + paket.getId_volonter());
                    viewHolder.paketVrijemePreuzimanja.setText("Preuzeto: " + paket.getV_preuzeto());
                };

            }else {
                if(paket.getHitno() != null && paket.getHitno().contains("1")){
                    convertView.setBackgroundColor(Color.parseColor("#fc5f77"));
                }else{
                    convertView.setBackgroundColor(Color.parseColor("#edb544"));
                }

                viewHolder.paketPreuzimanje.setText("Čeka preuzimanje.");
                viewHolder.paketVrijemePreuzimanja.setText("");

                if(tipK==2){
                    viewHolder.paketPreuzimanje.setText("Ime donora: " + paket.getNaziv_donor());
                    viewHolder.paketVrijemePreuzimanja.setText("Ime potrebitog: " + paket.getNaziv_potrebitog());
                }else{
                    viewHolder.paketPreuzimanje.setText("Čeka preuzimanje.");
                    viewHolder.paketVrijemePreuzimanja.setText("");
                };
            }



        return convertView;
    }
}
