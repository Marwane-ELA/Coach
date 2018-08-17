package com.marwane.coach.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Bronnen:
 * https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
 * https://stackoverflow.com/questions/5623624/problems-with-my-sqliteopenhelper
 * https://www.youtube.com/watch?v=BX_0RItiwcc&t=36s
 *
 *
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    //Propriétés
    private String creation = "create table profil("
            + "datemesure TEXT PRIMARY KEY,"
            + "poids INTEGER NOT NULL,"
            + "taille INTEGER NOT NULL,"
            + "age INTEGER NOT NULL,"
            + "sexe INTEGER NOT NULL);";

    //sqllite type --> text, numerique, integer et real
    /**
     * constructor
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * si changement de BD
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(creation);
    }

    /**
     * si changement de version
     * @param sqLiteDatabase
     * @param i ancienne version
     * @param i1 nouvelle version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
