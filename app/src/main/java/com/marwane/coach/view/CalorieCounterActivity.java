package com.marwane.coach.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.marwane.coach.R;
import com.marwane.coach.adapter.CalorieListAdapter;
import com.marwane.coach.controler.Controle;
import com.marwane.coach.controler.ControlerCalorie;
import com.marwane.coach.dialog.Dialog;
import com.marwane.coach.model.Item;
import com.marwane.coach.service.ServiceMP;

import java.util.ArrayList;
import java.util.List;

public class CalorieCounterActivity extends AppCompatActivity implements Dialog.DialogListener {

    private ControlerCalorie controle;
    private TextView totaal;

    //drawer
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView nv;
    private Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        controle = ControlerCalorie.getInstance(this);
        totaal = (TextView)findViewById(R.id.kcelCounter);

        createList();

        //Drawer
        nv = (NavigationView)findViewById(R.id.nv9);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawcal);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case(R.id.ItemHome):
                        in = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(in);
                        break;
                    case(R.id.ItemIMG):
                        in = new Intent(getApplicationContext(),MainImgCalcActivity.class);
                        startActivity(in);
                        break;
                    case(R.id.ItemYoga):
                        in = new Intent(getApplicationContext(),YogaActivity.class);
                        startActivity(in);
                        break;
                    case(R.id.ItemTimer):
                        in = new Intent(getApplicationContext(),CounterChronoActivity.class);
                        startActivity(in);
                        break;
                    case(R.id.ItemCarolie):
                        in = new Intent(getApplicationContext(),CalorieCounterActivity.class);
                        startActivity(in);
                        break;
                    case(R.id.ItemBT):
                        in = new Intent(getApplicationContext(),BluetoothActivity.class);
                        startActivity(in);
                        break;
                    case(R.id.ItemMusic):
                        in = new Intent(getApplicationContext(),MediaPlayerActivity.class);
                        startActivity(in);
                        break;
                    case(R.id.ItemMap):
                        in = new Intent(getApplicationContext(),MapsGoogleActivity.class);
                        startActivity(in);
                        break;
                    case(R.id.ItemStepCounter):
                        in = new Intent(getApplicationContext(),StepCounterActivity.class);
                        startActivity(in);
                        break;
                    case(R.id.ItemInfo):
                        in = new Intent(getApplicationContext(),InfoActivity.class);
                        startActivity(in);
                        break;
                    case(R.id.ItemShare):
                        Intent myIntent = new Intent(Intent.ACTION_SEND);
                        myIntent.setType("text/plain");
                        String shareBody = getString(R.string.shareOne);
                        String shareSub = getString(R.string.shareTwo);
                        myIntent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                        myIntent.putExtra(Intent.EXTRA_TEXT,shareSub);
                        startActivity(Intent.createChooser(myIntent,"Share using Coach"));
                        break;
                    case(R.id.ItemPlay):
                        click("play");
                        break;
                    case(R.id.ItemPause):
                        click("pause");
                        break;

                }
                return true;
            }
        });
    }

    //play and pause music of de navigation drawer
    public void click(String set) {
        if(set.equals("play")){
            startService(new Intent(this,ServiceMP.class));
        }else if(set.equals("pause")){
            stopService(new Intent(this,ServiceMP.class));
        }
    }
    private void createList() {
        List<Item> lesItem = controle.getListItem();
        if(lesItem != null){
            ListView lstItem = (ListView)findViewById(R.id.lstFood);
            CalorieListAdapter adapter = new CalorieListAdapter(this,(ArrayList<Item>)lesItem,totaal);
            lstItem.setAdapter(adapter);
            int sum = 0;
            for (int i = 0; i < lesItem.size(); i++ ){
                sum += lesItem.get(i).getCalorie();
            }
            totaal.setText(sum + " Kcal");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_cal,menu);


        //change menu icon color
        // Drawable icon = menu.getItem(0).getIcon();
        //icon.mutate();
        //icon.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        switch (item.getItemId()){
            case R.id.action_add_task:
                openDialog();
                return true;
            case R.id.about:
                startActivity(new Intent(this,InfoActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void applyAdd(String food, Integer calorie) {

        if(food.equals("") || calorie.equals(0) ){
            Toast.makeText(CalorieCounterActivity.this,"invalid input", Toast.LENGTH_SHORT).show();
        }
        else
        {
            this.controle.createProfiel(food,calorie);
            Toast.makeText(CalorieCounterActivity.this,"add oke", Toast.LENGTH_SHORT).show();
            createList();

        }

    }



}
