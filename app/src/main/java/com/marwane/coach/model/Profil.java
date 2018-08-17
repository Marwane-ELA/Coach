package com.marwane.coach.model;

import android.support.annotation.NonNull;

import org.json.JSONArray;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Marwane El Aissati on 1/5/2018
 */
public class Profil implements Serializable, Comparable {

    //constantes
    private static final Integer minFemme = 15;
    private static final Integer maxFemme = 30;
    private static final Integer minHomme = 10;
    private static final Integer maxHomme = 25;

    //final --> pas modifier
    //

    //Variables
    private Date dateMesure;
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img;
    private String message;


    public Profil(Date dataMesure, Integer poids, Integer taille, Integer age, Integer sexe) {
        this.dateMesure = dataMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculIMG();
        this.resultIMG();
    }

    public Integer getPoids() { return poids; }

    public Integer getTaille() { return taille; }

    public Integer getAge() { return age; }

    public Integer getSexe() { return sexe; }

    public Float getImg() { return img; }

    public String getMessage() { return message; }

    public Date getDateMesure() {
        return dateMesure;
    }

    private void calculIMG(){
        float tailleM = (float)taille/100;
        this.img = (float)((1.2*poids / (tailleM*tailleM)) + (0.23*age) - (10.83*sexe) - 5.4); // berekening IMG
    }

    private void resultIMG(){
        Integer min;
        Integer max;
        if(sexe==0){ //femme
            min = minFemme;
            max = maxFemme;
        }
        else { //homme
            min = minHomme;
            max = maxHomme;
        }
        //message correspondant
        message = "normal";
        if(img<min){
            message = "trop faible";
        }else{
            if(img> max){
                message = "trop élevé";
            }
        }

    }

    @Override
    public String toString() {
        return "Profil{" +
                "dateMesure=" + dateMesure +
                ", poids=" + poids +
                ", taille=" + taille +
                ", age=" + age +
                ", sexe=" + sexe +
                ", img=" + img +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return dateMesure.compareTo(((Profil)o).getDateMesure());
    }
}
