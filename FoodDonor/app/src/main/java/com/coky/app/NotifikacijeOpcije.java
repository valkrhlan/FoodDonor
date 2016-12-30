package com.coky.app;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.coky.app.R;
import com.coky.app.klase.ItemNotifikacijaMoguceOpcije;
import com.coky.app.klase.NotifikacijaMoguceOpcije;

public class NotifikacijeOpcije extends AppCompatActivity {

    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikacije_opcije);
        final RadioButton konfOption = (RadioButton)findViewById(R.id.konfigurabilniOption);
        //konfOption.setOnClickListener();
        final RadioGroup radioGroupInterval = (RadioGroup)findViewById(R.id.radioGroupKonfigurabilnoIntervalNO);
        if(konfOption.isChecked()){
            radioGroupInterval.setVisibility(View.VISIBLE);
        }
        RadioGroup notifOption = (RadioGroup)findViewById(R.id.radioGroupNotifikacijeNO);
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

        //mozda bude kasnije trebalo - >ak nebude treblo obrisala budem klase viška to su Notifikacija Moguće Opcije i itemNnotifikacije Ocije
       /*  radioGroup=(RadioGroup)findViewById(R.id.radioGroupNotifikacijeNO);
        RadioButton radioButton;
        NotifikacijaMoguceOpcije notifikacijaMoguceOpcije=new NotifikacijaMoguceOpcije();
       for(int i=0;i<notifikacijaMoguceOpcije.size();i++){
            radioButton=new RadioButton(this);
            radioButton.setText(notifikacijaMoguceOpcije.moguceOpcije.get(i).getTitle());
            radioGroup.addView(radioButton);
          }
          */
     }
}
