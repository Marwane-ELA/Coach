package com.marwane.coach.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.marwane.coach.tools.MySQLiteOpenHelper;
import com.marwane.coach.tools.MyTools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Bronnen:
 * https://www.youtube.com/watch?v=BX_0RItiwcc&t=70s
 * https://www.youtube.com/watch?v=neaCUaHa2Ek
 * http://www.sqlitetutorial.net/sqlite-delete/
 * + Werkcollege
 *
 */
public class AccesLocal {

    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accessBD;
    private SQLiteDatabase bd;

    /**
     * Constructor
     */
    public AccesLocal(Context contexte) {
        this.accessBD = new MySQLiteOpenHelper(contexte,nomBase,null,versionBase);
    }

    /**
     * ajoute dun profiel dans la bd
     * @param profil
     */
    public void ajout(Profil profil){
        Date date = profil.getDateMesure();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Belgium"));
        bd = accessBD.getWritableDatabase();
        String req = "insert into profil (datemesure, poids, taille, age, sexe) values ";
        req += "(\""+dateFormat.format(date)+"\","+profil.getPoids()+","+profil.getTaille()+","+profil.getAge()+","+profil.getSexe()+")";
        bd.execSQL(req);
    }

    /**
     * suppresion base de donnees
     * @param profil
     */
    public void delete(Profil profil){

        bd = accessBD.getWritableDatabase();
        Log.d("delete ligne","************");
        bd.delete("profil","datemesure = ?",new String[]{MyTools.convertDateToString(profil.getDateMesure())} );
    }

    /**
     * recuperation du dernier profil
     * @return
     */
    public Profil recupDernier(){
        bd = accessBD.getReadableDatabase();
        Profil profil = null;
        String req = "select * from profil";
        Cursor curseur = bd.rawQuery(req,null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Date date = MyTools.convertStringToDate(curseur.getString(0)); //new Date();
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profil = new Profil(date, poids, taille, age, sexe);
        }
        curseur.close();
        return profil;
    }

    public List<Profil> recupTout(){
        bd = accessBD.getReadableDatabase();
        Profil profil = null;
        String req = "select * from profil";
        Cursor curseur = bd.rawQuery(req,null);
        List<Profil> list = new ArrayList<Profil>();
        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            Date date = MyTools.convertStringToDate(curseur.getString(0)); //new Date();
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profil = new Profil(date, poids, taille, age, sexe);
            list.add(profil);
            curseur.moveToNext();
        }
        curseur.close();
        return list;
    }
}
