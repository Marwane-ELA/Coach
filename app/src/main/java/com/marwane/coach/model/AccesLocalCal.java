package com.marwane.coach.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListView;

import com.marwane.coach.tools.MySQLiteOpenHelperCal;

import java.util.ArrayList;
import java.util.List;

/**
 *  Bronnen:
 * https://www.youtube.com/watch?v=BX_0RItiwcc&t=70s
 * https://www.youtube.com/watch?v=neaCUaHa2Ek
 * http://www.sqlitetutorial.net/sqlite-delete/
 * + Werkcollege
 */
public class AccesLocalCal   {

    private String nomBase = "bdkcal.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelperCal accessBD;
    private SQLiteDatabase bd;
    private static final String COLUMN_NAME = "food";
    private static final String COLUMN_NAME2 = "calorie";

    public AccesLocalCal(Context contexte) {
        this.accessBD = new MySQLiteOpenHelperCal(contexte,nomBase,null,versionBase);
    }

    public void create(Item item){
        bd = accessBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,item.getItem());
        values.put(COLUMN_NAME2, item.getCalorie());
        bd.insertWithOnConflict("kcal",null,values,SQLiteDatabase.CONFLICT_REPLACE);
        bd.close();

    }

    public void delete(String item){
        bd = accessBD.getWritableDatabase();
        Log.d("delete ligne","***************");
        bd.delete("kcal",COLUMN_NAME +" = ?",new String[]{item});
        bd.close();
    }

    public List<Item>getAll(){
        bd = accessBD.getReadableDatabase();
        Item item = null;
        String req = "select * from kcal";
        Cursor curseur = bd.rawQuery(req, null);
        List<Item> list = new ArrayList<Item>();
        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            String food = curseur.getString(1);
            int cal = curseur.getInt(2);
            item = new Item(food,cal);
            list.add(item);
            curseur.moveToNext();
        }
        curseur.close();
        return list;
    }
}
