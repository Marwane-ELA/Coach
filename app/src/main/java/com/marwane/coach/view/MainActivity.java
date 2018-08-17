package com.marwane.coach.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.marwane.coach.R;
import com.marwane.coach.service.ServiceMP;

public class MainActivity extends AppCompatActivity  {

    LinearLayout lytShare;

    //drawer
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView nv;
    private Intent in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ecouteMenu((LinearLayout) findViewById(R.id.lytMain), MainImgCalcActivity.class);
        ecouteMenu((LinearLayout) findViewById(R.id.lytTimer), CounterChronoActivity.class);
        ecouteMenu((LinearLayout) findViewById(R.id.lytYoga), YogaActivity.class);
        ecouteMenu((LinearLayout) findViewById(R.id.LytBT), BluetoothActivity.class);
        ecouteMenu((LinearLayout) findViewById(R.id.lytMediaPlayer), MediaPlayerActivity.class);
        ecouteMenu((LinearLayout) findViewById(R.id.lytMP2), MapsGoogleActivity.class);
        ecouteMenu((LinearLayout) findViewById(R.id.lytStep), StepCounterActivity.class);
        ecouteMenu((LinearLayout) findViewById(R.id.lytCalorie), CalorieCounterActivity.class);




        lytShare = (LinearLayout)findViewById(R.id.lytShare);

        lytShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = getString(R.string.shareOne);
                String shareSub = getString(R.string.shareTwo);
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareSub);
                startActivity(Intent.createChooser(myIntent,"Share using Coach"));


            }
        });

        //Drawer
        nv = (NavigationView)findViewById(R.id.nv10);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawMain);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                        in = new Intent(getApplicationContext(),MainImgCalcActivity.class);
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

    private void ecouteMenu(LinearLayout lyt, final Class classe){
        lyt.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, classe);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; This add items to the action bar if it is present;
        getMenuInflater().inflate(R.menu.actionbar_menu,menu);
        return true;
    }

    //Determine if action bar item was selected. If true the do corresponding action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        //Handle presses on the action bar items
        switch (item.getItemId()){
            case R.id.about:
                startActivity(new Intent(this,InfoActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
