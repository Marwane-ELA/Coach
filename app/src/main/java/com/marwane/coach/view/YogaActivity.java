package com.marwane.coach.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.marwane.coach.R;
import com.marwane.coach.service.ServiceMP;

/**
 * Bronnen:
 * https://www.youtube.com/watch?v=Q5Ndr944U2o
 * https://stackoverflow.com/questions/45422761/how-can-i-change-colorprimarydark-for-just-one-activity
 *
 *
 */
public class YogaActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;

    Button btnExercises,btnSetting,btnCalendar;
    ImageView btnTraining;

    //Drawer
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView nv;
    private Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);


        btnExercises = (Button)findViewById(R.id.btnExercises);
        btnSetting = (Button)findViewById(R.id.btnSetting);
        btnTraining = (ImageView)findViewById(R.id.btnTraining);
        btnCalendar = (Button)findViewById(R.id.btnCalendar);

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YogaActivity.this,CalendarActivity.class);
                startActivity(intent);
            }
        });

        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YogaActivity.this,Daily_TrainingActivity.class);
                startActivity(intent);
            }
        });

        btnExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YogaActivity.this,ListExercises.class);
                startActivity(intent);

            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YogaActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });

        //Drawer
        nv = (NavigationView)findViewById(R.id.nv4);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.yogaDrawer);
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

    //drawer button
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; This add items to the action bar if it is present;
        getMenuInflater().inflate(R.menu.actionbar_menu,menu);
        return true;
    }



    //swipe
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }



}
