package com.marwane.coach.view;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.marwane.coach.R;
import com.marwane.coach.service.ServiceMP;

/**
 * Bronnen:
 * https://www.youtube.com/watch?v=CNGMWnmldaU
 */
public class StepCounterActivity extends AppCompatActivity implements SensorEventListener{

    TextView tv_steps;
    SensorManager sensorManager;
    boolean running = false;

    //drawer
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView nv;
    private Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        tv_steps = (TextView)findViewById(R.id.tv_steps);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        //Drawer
        nv = (NavigationView)findViewById(R.id.nv7);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawStep);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; This add items to the action bar if it is present;
        getMenuInflater().inflate(R.menu.actionbar_menu,menu);
        return true;
    }


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
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);
        }
        else{
            Toast.makeText(this,"Sensor not found************************",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(running){
            tv_steps.setText(String.valueOf(sensorEvent.values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
