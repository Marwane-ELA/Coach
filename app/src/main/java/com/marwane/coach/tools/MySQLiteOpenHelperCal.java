package com.marwane.coach.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * /**
 * Bronnen:
 * https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
 * https://stackoverflow.com/questions/5623624/problems-with-my-sqliteopenhelper
 * https://www.youtube.com/watch?v=BX_0RItiwcc&t=36s
 */

public class MySQLiteOpenHelperCal extends SQLiteOpenHelper {

    //Propriétés
    private String creation = "create table kcal("
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "food TEXT NOT NULL,"
            + "calorie INTEGER NOT NULL);";



    public MySQLiteOpenHelperCal(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(creation);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
