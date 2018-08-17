package com.marwane.coach.controler;

import android.content.Context;
import android.util.Log;

import com.marwane.coach.model.AccesLocal;
import com.marwane.coach.model.Profil;
import com.marwane.coach.tools.Serializer;

import java.util.Date;
import java.util.List;

/**
 * Created byh Marwane El Aissati on 01/05/2018
 * Bronnen:
 * https://www.youtube.com/watch?v=BX_0RItiwcc&t=72s
 * https://developer.android.com/training/data-storage/sqlite
 *
 */
public final class Controle {

    //instance unique pour l'appli --> sinlgeton(un seul objet)
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFichier = "saveprofil";
    private static AccesLocal accesLocal;
    //peut pas faire new car prive
    //private static AccesDistant accesDistant;



    /**
     * constructeurr private
     */
    private Controle() {
        super();
    }

    // a la place new

    /**
     * creation de l'instance
     *
     * @return instance
     */
    public static final Controle getInstance(Context contexte) {
        if (Controle.instance == null) {
            Controle.instance = new Controle();
            accesLocal = new AccesLocal(contexte);
            profil = accesLocal.recupDernier();
            //recupSerialize(contexte);
        }
        return Controle.instance;
    }

    public List<Profil> getListProfil(){
        return accesLocal.recupTout();
    }


    /**
     * creation du profiel
     *
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe   1 pour homme et 0 pour femme
     */
    public void createProfiel(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte) {
        profil = new Profil(new Date(), poids, taille, age, sexe);
        Log.d("date", "*******************************" + (new Date()));
        accesLocal.ajout(profil);
        //Serializer.serialize(nomFichier, profil, contexte);
    }

    public void deleteProfiel(Profil profil){
        accesLocal.delete(profil);

    }

    /**
     * recuperation img de profil
     *
     * @return img
     */
    public float getImg() {
        return profil.getImg();
    }

    /**
     * recupêration  message du profiel
     *
     * @return message
     */
    public String getMessage() {
        return profil.getMessage();
    }

    /**
     * recuperation de l'objet serialisé(le profiel)
     * @param contexte
     */
    private static void recupSerialize(Context contexte) {
        profil = (Profil) Serializer.deSerialize(nomFichier, contexte);//objet
    }

    public Integer getPoids(){
        if(profil == null){
            return null;
        }
        else {
            return profil.getPoids();
        }
    }

    public Integer getTaille(){
        if(profil == null){
            return null;
        }
        else {
            return profil.getTaille();
        }
    }

    public Integer getAge(){
        if(profil == null){
            return null;
        }
        else {
            return profil.getAge();
        }
    }

    public Integer getSexe(){
        if(profil == null){
            return null;
        }
        else {
            return profil.getSexe();
        }
    }
}