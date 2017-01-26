package com.coky.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.coky.app.R;
import com.coky.app.klase.ItemNotifikacijaMoguceOpcije;
import com.coky.app.klase.NotifikacijaMoguceOpcije;
import com.coky.app.klase.UpraviteljNotifikacija;
import com.coky.app.konfigurabilno.Alarm;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotifikacijeOpcije extends AppCompatActivity {

    private RadioGroup radioGroupOpcije;
    private RadioGroup radioGroupIntervali;
    private boolean postaviIntervalIzsSheredPrefs=false;
    private int brojac;


    @BindView(R.id.btnPohraniPromjeneNO)
    Button btnPohraniPromijene;

    @OnClick(R.id.btnPohraniPromjeneNO)
    public void btnPohraniPromijeneNOClick(View view){
        if(radioGroupOpcije.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Odaberite opciju!",Toast.LENGTH_SHORT).show();

        }else{
            if (radioGroupIntervali.getCheckedRadioButtonId()==-1){
                Toast.makeText(this,"Odaberite interval!",Toast.LENGTH_SHORT).show();
            }else{
                RadioButton odabranaOpcija=(RadioButton)findViewById(radioGroupOpcije.getCheckedRadioButtonId());
                RadioButton odabraniInterval=(RadioButton)findViewById(radioGroupIntervali.getCheckedRadioButtonId());
                String opcija =odabranaOpcija.getText().toString();
                String pom=odabraniInterval.getText().toString();
                int interval=Integer.parseInt(pom);
                setSharedPrefs(opcija,interval);
                Toast.makeText(this.getApplicationContext(),"Promijene evidentirane!",Toast.LENGTH_SHORT).show();
                UpraviteljNotifikacija un=new UpraviteljNotifikacija();
                un.pohraniPromjene(this.getApplicationContext(),opcija,interval);

                finish();
            }
        }
       /* int idNotif=notifOption.getCheckedRadioButtonId();
        if(idNotif==-1){
            Toast.makeText(this,"Odaberite neku od mogućnosti!",Toast.LENGTH_SHORT).show();
        }else{
            UpraviteljNotifikacija un=new UpraviteljNotifikacija();
           // Toast.makeText(this,"Ok je!",Toast.LENGTH_SHORT).show();
            RadioButton btnOpcija=(RadioButton)findViewById(idNotif);
            if(btnOpcija.getText().toString().equals("Firebase")){
                un.pohraniPromjene(this.getApplicationContext(),"Firebase",0);
                setSharedPrefs("Firebase",0);
                Toast.makeText(this,"Firebase je",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"Promijene evidentirane!",Toast.LENGTH_SHORT).show();

            }else{
               int idRadioGroupInterval =radioGroupInterval.getCheckedRadioButtonId();
                if (idRadioGroupInterval==-1){
                    Toast.makeText(this,"Odaberite neki od intervala!",Toast.LENGTH_SHORT).show();
                }else{
                    spremiInterval(idRadioGroupInterval);
                   // UpraviteljNotifikacija un=new UpraviteljNotifikacija();
                    un.pohraniPromjene(this,"Konfigurabilno",10);
                    Toast.makeText(this.getApplicationContext(),"Promijene evidentirane!",Toast.LENGTH_SHORT).show();


                }
            }
        }*/

      }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikacije_opcije);
        ButterKnife.bind(this);

        /*
        *dinamičko popunjavanje mogućih opcija
         */

        radioGroupOpcije= (RadioGroup)findViewById(R.id.radioGroupOpcijeNO);
        radioGroupIntervali=(RadioGroup)findViewById(R.id.radioGroupIntervalNO);
        final NotifikacijaMoguceOpcije moguceOpcije=new NotifikacijaMoguceOpcije();
        int size=moguceOpcije.size();
        for (int i=0; i<size;i++){
                RadioButton radioButton=new RadioButton(this);
                String tekst=moguceOpcije.getOpcija(i);
                radioButton.setText(tekst);
                radioButton.setId(i+1);
                radioGroupOpcije.addView(radioButton);
        }
        radioGroupOpcije.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                postaviIntervale(moguceOpcije);
            }
        });
        brojac=moguceOpcije.size()+1;
        postaviOpcije();
        //postaviIntervale(moguceOpcije);


        //kako obrisati sve elemente djece,jednostano-> rg.removeAllViews();
/*


        final RadioButton konfOption = (RadioButton)findViewById(R.id.konfigurabilniOption);
        radioGroupInterval = (RadioGroup)findViewById(R.id.radioGroupKonfigurabilnoIntervalNO);
        notifOption = (RadioGroup)findViewById(R.id.radioGroupNotifikacijeNO);
        notifOption.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(konfOption.isChecked()){
                    radioGroupInterval.setVisibility(View.VISIBLE);
                }else{
                    radioGroupInterval.setVisibility(View.GONE);
                }
            }
        });

        if(konfOption.isChecked()){
            radioGroupInterval.setVisibility(View.VISIBLE);
        }
        oznaciRadioButtone();

     }

    //postavljanje već ranije spremljenih vrijednosti u shared preferences
private void oznaciRadioButtone(){
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    final String notifikacije=prefs.getString("notifikacije",null);
    if(notifikacije!=null) {
        for(int i=0;i<notifOption.getChildCount();i++){
            RadioButton child=(RadioButton)notifOption.getChildAt(i);
            String pom=child.getText().toString();
            if(pom.equals(notifikacije)){
                ((RadioButton) notifOption.getChildAt(i)).setChecked(true);
            }
        }
    }else{
        ( (RadioButton)notifOption.getChildAt(0)).setChecked(true);
    }
    final int interval=prefs.getInt("interval",-1);
    if(interval!=-1){
        switch (interval){
            case 0: break;
            case 30:
                ((RadioButton)radioGroupInterval.getChildAt(0)).setChecked(true);
                break;
            case 60:
                ((RadioButton)radioGroupInterval.getChildAt(1)).setChecked(true);
                break;
            case 300:
                ((RadioButton)radioGroupInterval.getChildAt(2)).setChecked(true);
                break;
        }
    }
    */
}

    private void setSharedPrefs(String notifikacije, int interval){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("notifikacije", notifikacije);
            editor.putInt("interval", interval);
            editor.apply();
        }

    private void postaviOpcije() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String notifikacije = prefs.getString("notifikacije", null);
        if (notifikacije == null) {
            ((RadioButton)radioGroupOpcije.getChildAt(0)).setChecked(true);
            postaviIntervalIzsSheredPrefs=false;
        }else{
            int i=0;
            boolean pronaden=false;
            while(pronaden==false){
                RadioButton rb=(RadioButton)radioGroupOpcije.getChildAt(i);
                if(rb.getText().equals(notifikacije)){
                    pronaden=true;
                    postaviIntervalIzsSheredPrefs=true;
                    rb.setChecked(true);
                }
                i++;
            }
        }
    }
    private void postaviIntervale(NotifikacijaMoguceOpcije moguceOpcije){
        if(radioGroupIntervali.getChildCount()>0){
            radioGroupIntervali.removeAllViews();
        }

        List<Integer> listaIntervala=moguceOpcije.getInterval(radioGroupOpcije.getCheckedRadioButtonId()-1);
        for (int i=0; i<listaIntervala.size();i++){
            RadioButton radioButton=new RadioButton(this);
            String tekst=String.valueOf(listaIntervala.get(i));
            radioButton.setText(tekst);
            radioButton.setId(brojac);
            radioGroupIntervali.addView(radioButton);
            brojac++;
        }
        if(postaviIntervalIzsSheredPrefs){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            int interval=prefs.getInt("interval",-1);
            if (interval==-1 ){
                RadioButton rb=(RadioButton)radioGroupIntervali.getChildAt(0);
                rb.setChecked(true);
            }
            else{
                int i=0;
                boolean pronaden=false;
                while(pronaden==false){
                    RadioButton rb=(RadioButton)radioGroupIntervali.getChildAt(i);
                    int rbInterval=Integer.parseInt(rb.getText().toString());
                    if(rbInterval==interval){
                        rb.setChecked(true);
                        pronaden=true;
                    }
                    i++;
                }

            }

        }else{

            RadioButton rb=(RadioButton)radioGroupIntervali.getChildAt(0);
            rb.setChecked(true);
        }
        postaviIntervalIzsSheredPrefs=false;

    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
