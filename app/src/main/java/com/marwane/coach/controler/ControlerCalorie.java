package com.marwane.coach.controler;

import android.content.Context;

import com.marwane.coach.model.AccesLocal;
import com.marwane.coach.model.AccesLocalCal;
import com.marwane.coach.model.Item;

import java.util.List;

/***
 * Bronnen:
 * https://www.youtube.com/watch?v=BX_0RItiwcc&t=72s
 * https://developer.android.com/training/data-storage/sqlite
 */
public final class ControlerCalorie {

    private static ControlerCalorie instance = null;
    private static Item item;
    private static AccesLocalCal accesLocal;


    public ControlerCalorie() { super(); }


    /**
     * creation de l'instance
     * un seule instance possible
     * @return instance
     */
    public static final ControlerCalorie getInstance(Context contexte) {
        if (ControlerCalorie.instance == null) {
            ControlerCalorie.instance = new ControlerCalorie();
            accesLocal = new AccesLocalCal(contexte);

        }
        return ControlerCalorie.instance;
    }

    public List<Item>getListItem(){return accesLocal.getAll();}

    public void createProfiel(String food,Integer calorie){
        item = new Item(food,calorie);
        accesLocal.create(item);
    }

    public void deleteItem(String food){
        accesLocal.delete(food);
    }








}
