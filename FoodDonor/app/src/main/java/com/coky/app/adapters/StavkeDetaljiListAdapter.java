package com.coky.app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.coky.app.R;
import com.coky.core.entities.Stavka;

import java.util.List;

/**
 * Created by Čoky on 10.1.2017..
 */

public class StavkeDetaljiListAdapter extends ArrayAdapter<Stavka> {

    private Context context;
    private List<Stavka> stavkeList;
    private int resource;

    public StavkeDetaljiListAdapter(Context context, int resource, List<Stavka> stavkeList){
        super(context, resource,stavkeList);
        this.context=context;
        this.stavkeList=stavkeList;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View itemView = convertView;
        if(convertView==null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.stavka_detalji, parent, false);
        }
        Stavka trenutnaSavka = stavkeList.get(position);
        TextView naziv = (TextView) itemView.findViewById(R.id.detalj_naziv);
        TextView vrsta = (TextView)itemView.findViewById(R.id.detalj_vrsta);
        TextView kolicina_jedinica = (TextView)itemView.findViewById(R.id.detalj_kolicina_s_jedinicom);
        naziv.setText(trenutnaSavka.getNaziv());
        vrsta.setText(trenutnaSavka.getVrsta());
        kolicina_jedinica.setText(trenutnaSavka.getKolicina() + " " + trenutnaSavka.getJedinica());
        return itemView;
    }
}
