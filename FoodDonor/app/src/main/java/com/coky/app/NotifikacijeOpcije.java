package com.coky.app;

import android.content.Context;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotifikacijeOpcije extends AppCompatActivity {

    private RadioGroup radioGroupInterval;
    private RadioGroup notifOption;

    @BindView(R.id.btnPohraniPromjeneNO)
    Button btnPohraniPromijene;

    @OnClick(R.id.btnPohraniPromjeneNO)
    public void btnPohraniPromijeneNOClick(View view){
        int idNotif=notifOption.getCheckedRadioButtonId();
        if(idNotif==-1){
            Toast.makeText(this,"Odaberite neku od mogućnosti!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Ok je!",Toast.LENGTH_SHORT).show();

        }
      }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikacije_opcije);
        ButterKnife.bind(this);
        final RadioButton konfOption = (RadioButton)findViewById(R.id.konfigurabilniOption);
        radioGroupInterval = (RadioGroup)findViewById(R.id.radioGroupKonfigurabilnoIntervalNO);
        if(konfOption.isChecked()){
            radioGroupInterval.setVisibility(View.VISIBLE);
        }
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
