package com.marwane.coach.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.marwane.coach.R;
import com.marwane.coach.adapter.HistoListAdapter;
import com.marwane.coach.controler.Controle;
import com.marwane.coach.model.Profil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Bronnen:
 * https://developer.android.com/reference/android/widget/Adapter
 */
public class HistoryActivity extends AppCompatActivity {

    private TextView listProfil;
    private Controle controle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        this.controle = Controle.getInstance(this);
        ecouteRetourMenu();
        createList();
       // listProfil = (TextView) findViewById(R.id.txtList);
        //afficheList();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void afficheList(){
        List<Profil> list = this.controle.getListProfil();
        for(Profil profil: list){
            listProfil.append(profil.toString() + "\n\n");
        }
    }

    private void ecouteRetourMenu(){
        ((Button)findViewById(R.id.btnRetour)).setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
               startActivity(intent);


            }
        });
    }

    public void createList(){
        List<Profil> lesProfils = controle.getListProfil();
        Collections.sort(lesProfils, Collections.<Profil>reverseOrder());
        if(lesProfils != null){
            ListView lsthisto = (ListView)findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(this, (ArrayList<Profil>)lesProfils);
            lsthisto.setAdapter(adapter);
        }
    }
}
