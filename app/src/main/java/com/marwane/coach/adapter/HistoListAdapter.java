package com.marwane.coach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.marwane.coach.R;
import com.marwane.coach.controler.Controle;
import com.marwane.coach.model.Profil;
import com.marwane.coach.tools.MyTools;

import java.util.ArrayList;

/**
 * created by Marwane El Aissati on 02/08/2018
 * Bronnen:
 * https://stackoverflow.com/questions/20564519/using-an-xml-string-within-a-baseadapter
 * https://stackoverflow.com/questions/16333754/how-to-customize-listview-using-baseadapter
 * https://github.com/codepath/android_guides/wiki/Using-a-BaseAdapter-with-ListView
 * https://developer.android.com/reference/android/widget/Adapter
 */
public class HistoListAdapter extends BaseAdapter {

    private ArrayList<Profil> listProfil;
    private LayoutInflater inflater;
    private HistoListAdapter adapter;
    private Context contexte;
    Controle controle;
    String sexe;

    public HistoListAdapter(Context contexte, ArrayList<Profil> listProfil) {
        this.listProfil = listProfil;
        this.inflater = LayoutInflater.from(contexte);
        this.controle = Controle.getInstance(null);
        this.adapter = this;
        this.contexte = contexte;

    }

    /**
     * return le nombre de ligne
     * @return
     */
    @Override
    public int getCount() {
        return listProfil.size();
    }

    /**
     * retourn l'item de la ligne actuelle
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return listProfil.get(i);
    }

    /**
     * retourne un indice par apport a la ligne actuelle
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * reotourne la ligne (view) formaté
     * Bron:
     * https://www.youtube.com/watch?v=oojJR_x04vI&t=1282s
     * https://stackoverflow.com/questions/24660343/how-to-reload-activity-from-onclicklistener-in-arrayadapter
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        //si la ligne existe pas encore
        if(view == null){
            holder = new ViewHolder();
            //la ligne est contruite avec un formatage (inflater) relié a layout_liste_histo
            view = inflater.inflate(R.layout.layout_list_histo,null);
            //chaque proprieté du holder est relié a une propriete graphique
            holder.txtDate = (TextView)view.findViewById(R.id.txtDate);
            holder.txtInfo = (TextView)view.findViewById(R.id.txtInfo);
            holder.txtIMG = (TextView)view.findViewById(R.id.txtIMG);
            holder.btnDelete = (ImageButton)view.findViewById(R.id.btnDelete);
            //affecter le holder a la vue
            view.setTag(holder);


        }else{
            //recupere du holder dans la ligne existante
            holder = (ViewHolder)view.getTag();
        }
        //valorisation du contenu du holder (donc de la lgine)
        if(listProfil.get(i).getSexe() == 0){
            sexe = contexte.getResources().getString(R.string.rdFemme);
        }
        else
        {
            sexe = contexte.getResources().getString(R.string.rdHomme);
        }

        holder.txtDate.setText(MyTools.convertDateToString(listProfil.get(i).getDateMesure()));
        holder.txtInfo.setText(contexte.getResources().getString(R.string.txtWeight) + listProfil.get(i).getPoids() +  "kg\n"
                                            + contexte.getResources().getString(R.string.txtAge) + listProfil.get(i).getAge() + "\n"
                                            + contexte.getResources().getString(R.string.txtSize) + listProfil.get(i).getTaille() + "cm\n"
                                            + "Sexe: " + sexe );
        holder.txtIMG.setText(String.format("%.01f",listProfil.get(i).getImg()));
        holder.btnDelete.setTag(i);

        //clic sur la croix pour supprimer le profil enrengistré
        holder.btnDelete.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                int position = (int)view.getTag();
                //vraag aan de controler om te deleten
                controle.deleteProfiel(listProfil.get(position));
                listProfil.remove(listProfil.get(position));
                adapter.notifyDataSetChanged();
                //Raffraichir la liste
               // notifyDataSetChanged();








            }
        });
        return view;
    }

    private class ViewHolder{
        TextView txtDate;
        TextView txtInfo;
        TextView txtIMG;
        ImageButton btnDelete;
    }
}
