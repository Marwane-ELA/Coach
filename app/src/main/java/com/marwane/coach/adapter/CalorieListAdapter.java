package com.marwane.coach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.marwane.coach.R;
import com.marwane.coach.controler.ControlerCalorie;
import com.marwane.coach.model.Item;

import java.util.ArrayList;


/**
 * Bronnen:
 * https://developer.android.com/reference/android/widget/Adapter
 * https://www.youtube.com/playlist?list=PLRR7wjtXb1cB-jibndUw-qv79O2KQkG6U
 */
public class CalorieListAdapter extends BaseAdapter {

    private ArrayList<Item> listItem;
    private LayoutInflater inflater;
    private CalorieListAdapter adapter;
    private Context contexte;
    ControlerCalorie controle;
    private TextView totaal;

    public CalorieListAdapter(Context contexte, ArrayList<Item> listItem,TextView totaal ) {
        this.listItem = listItem;
        this.inflater = LayoutInflater.from(contexte);
        this.controle = ControlerCalorie.getInstance(null);
        this.adapter = this;
        this.contexte = contexte;
        this.totaal = totaal;

    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();

            view = inflater.inflate(R.layout.rowcal,null);

            holder.txtFood = (TextView)view.findViewById(R.id.txtCalfood);
            holder.txtCalorie = (TextView)view.findViewById(R.id.txtCalCalorie);
            holder.btnDelete = (ImageButton)view.findViewById(R.id.btnCalDelete);

            view.setTag(holder);

        }else{
            holder = (ViewHolder)view.getTag();
        }

        holder.txtFood.setText(listItem.get(i).getItem());
        holder.txtCalorie.setText(listItem.get(i).getCalorie() + "");
        holder.btnDelete.setTag(i);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (int)view.getTag();
                controle.deleteItem(listItem.get(position).getItem());
                listItem.remove(listItem.get(position));
                int sum = 0;
                for(int i = 0;i < listItem.size();i++){
                    sum += listItem.get(i).getCalorie();
                }
                totaal.setText(sum + " Kcal");
                adapter.notifyDataSetChanged();

            }
        });


        return view;
    }

    private class ViewHolder{
        TextView txtFood;
        TextView txtCalorie;
        ImageButton btnDelete;
    }
}
