package com.coky.app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.coky.app.R;
import com.coky.app.klase.StavkaPaketa;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentina on 6.12.2016..
 */

public class StavkePaketaListAdapter extends ArrayAdapter<StavkaPaketa> {
    private Context context;
    private List<StavkaPaketa> stavkePaketaList;
    private int resource;

    public StavkePaketaListAdapter(Context context, int resource, List<StavkaPaketa> stavkePaketaList){
        super(context, resource,stavkePaketaList);
        this.context=context;
        this.stavkePaketaList=stavkePaketaList;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View itemView = convertView;
        if(convertView==null){
             itemView = LayoutInflater.from(getContext()).inflate(R.layout.stavka_item, parent, false);
        }

        StavkaPaketa trenutnaSavka=stavkePaketaList.get(position);
        TextView naslov=(TextView) itemView.findViewById(R.id.nazivHraneSI);
        TextView vrsta=(TextView)itemView.findViewById(R.id.vrstaHraneSI);
        TextView kolicina = (TextView)itemView.findViewById(R.id.kolicinaHraneSI);
        TextView jedinica = (TextView)itemView.findViewById(R.id.jedinicaHraneSI);
        final ImageButton btn = (ImageButton)itemView.findViewById(R.id.btnObisiStavkuSI);
        btn.setTag(position);
        //listener za brisanje stavke iz liste
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stavkePaketaList.remove((int)btn.getTag());
                StavkePaketaListAdapter.this.notifyDataSetChanged();

            }
        });
        naslov.setText(trenutnaSavka.getNaziv());
        vrsta.setText(trenutnaSavka.getVrsta().getNaziv());
        kolicina.setText((trenutnaSavka.getKolicina()));
        jedinica.setText(trenutnaSavka.getJedinica().getNaziv());
        return itemView;
    }

}
