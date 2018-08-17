package com.marwane.coach.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.marwane.coach.R;
import com.marwane.coach.controler.Controle;

public class ImgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        init();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //variables
    private TextInputLayout txtPoids;
    private TextInputLayout txtTaille;
    private TextInputLayout txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Controle controle;

    /**
     * initialiseren van de variables via de view objects
     *
     * bronnen:
     * https://www.youtube.com/watch?v=6nmnWi_hfC0&list=PLBNheBxhHLQxmCCiHGkXBAIsC1VKpZkSe&index=2
     *
     */
    private void init(){
        txtPoids = (TextInputLayout) findViewById(R.id.txtPoids);
        txtTaille = (TextInputLayout) findViewById(R.id.txtTaille);
        txtAge = (TextInputLayout) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        this.controle = Controle.getInstance(this);
        ecouteCalcul();
        ecouteRetourMenu();
        recupProfil();

    }

    /**
     * ecoute evenement sur bouton de calcul
     */
    private void ecouteCalcul(){
        ((Button) findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
               // Toast.makeText(ImgActivity.this,"salut salut mes petits cornichon", Toast.LENGTH_SHORT).show();
               // Log.d("message","clic oke sur le button calcul ********************************************");
                Integer poids = 0;
                Integer age = 0;
                Integer taille = 0;
                Integer sexe = 0;
                //recuperation des donnees saisies
                try {
                    poids = Integer.parseInt(txtPoids.getEditText().getText().toString().trim());
                    age = Integer.parseInt(txtAge.getEditText().getText().toString().trim());
                    taille = Integer.parseInt(txtTaille.getEditText().getText().toString().trim());
                }catch (Exception e){

                }

                if(rdHomme.isChecked()){
                    sexe = 1;
                }

                //COntrole des donnees saisies
                if(poids==0 || taille==0 || age==0){
                    Toast.makeText(ImgActivity.this,"Saisi incorrecte", Toast.LENGTH_SHORT).show();
                }else{
                    afficheResult(poids,age,taille,sexe);
                }


            }
        });
    }

    /**
     * Affichage de IMG et du  message et de l'image
     * @param poids
     * @param age
     * @param taille
     * @param sexe
     */
    private void afficheResult(Integer poids, Integer age, Integer taille, Integer sexe){
        //creation du profiel et recuperation des information
        this.controle.createProfiel(poids,taille,age,sexe, this);
        float img = controle.getImg();
        String message = this.controle.getMessage();
        //affichage
         if(message == "normal"){
             imgSmiley.setImageResource(R.drawable.good);
                lblIMG.setTextColor(Color.GREEN);
                message = this.getString(R.string.ImgResponseNormal);
         }
         else {
             lblIMG.setTextColor(Color.RED);
             if (message == "trop faible") {
                 message = this.getString(R.string.ImgResponseToWeak);
                 imgSmiley.setImageResource(R.drawable.bad);
             } else {
                 imgSmiley.setImageResource(R.drawable.bad);
                 message = this.getString(R.string.ImgResponseToHigh);
             }
         }

        lblIMG.setText(String.format("%.01f", img) + " : IMG  " +message);
    }

    /**
     * recuperation du profiel si il a etait serialisé
     */
    private void recupProfil(){
        if(controle.getPoids() != null){
            txtPoids.getEditText().setText(controle.getPoids().toString());
            txtTaille.getEditText().setText(controle.getTaille().toString());
            txtAge.getEditText().setText(controle.getAge().toString());
            if(controle.getSexe() == 1){
                rdHomme.setChecked(true);
            }
            else{
                rdFemme.setChecked(true);
            }
            //simule le clic sur le bouton calcul
            //((Button)findViewById(R.id.btnCalc)).performClick();
        }
    }

    private void ecouteRetourMenu(){
        ((Button)findViewById(R.id.btnHome)).setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ImgActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
    }
}
