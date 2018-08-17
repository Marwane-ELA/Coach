package com.marwane.coach.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.marwane.coach.R;
import com.marwane.coach.service.ServiceMP;

import dyanamitechetan.vusikview.VusikView;


/**
 * Bronnen:
 * https://developer.android.com/guide/components/services
 * https://www.youtube.com/watch?v=p2ffzsCqrs8
 * https://www.youtube.com/watch?v=zCYQBIcePaw&t=412s
 * https://developer.android.com/reference/android/media/MediaPlayer
 */
public class MediaPlayerActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnStart,btnStop;
    Button playBtn;
    SeekBar positionBar;
    SeekBar volumeBar;
    TextView elapsedTimeLabel;
    TextView remainingTimeLabel;
    MediaPlayer mp;
    int totalTime;
    private VusikView musicView;

    //drawer
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView nv;
    private Intent in;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

       /* btnStart = (Button)findViewById(R.id.btnMPStart);
        btnStop = (Button)findViewById(R.id.btnMPStop);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);*/

        playBtn = (Button) findViewById(R.id.playBtn);
        elapsedTimeLabel = (TextView) findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = (TextView) findViewById(R.id.remainingTimeLabel);
        musicView = (VusikView)findViewById(R.id.musicView);

        // Media Player
        //startService(new Intent(this,ServiceMP.class));
        mp = MediaPlayer.create(this, R.raw.growup);
        mp.setLooping(true);
        mp.seekTo(0);
        mp.setVolume(0.5f, 0.5f);
        totalTime = mp.getDuration();

        // Position Bar
        positionBar = (SeekBar) findViewById(R.id.positionBar);
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            mp.seekTo(progress);
                            positionBar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );


        // Volume Bar
        volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        volumeBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        float volumeNum = progress / 100f;
                        mp.setVolume(volumeNum, volumeNum);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        // Thread (Update positionBar & timeLabel)
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
        }).start();

        //Drawer
        nv = (NavigationView)findViewById(R.id.nv6);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerMusic);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


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
                        in = new Intent(getApplicationContext(),MainImgCalcActivity.class);
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
    public void onClick(View view) {


       /* if(view == btnStart){
            startService(new Intent(this,ServiceMP.class));
        }else if(view == btnStop){
            stopService(new Intent(this,ServiceMP.class));
        }*/

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            positionBar.setProgress(currentPosition);

            // Update Labels.
            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainingTime = createTimeLabel(totalTime-currentPosition);
            remainingTimeLabel.setText("- " + remainingTime);
        }
    };

    public String createTimeLabel(int time) {
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    public void playBtnClick(View view) {

        if (!mp.isPlaying()) {
            // Stopping
            mp.start();
            playBtn.setBackgroundResource(R.drawable.stop);
            musicView.start();

        } else {
            // Playing
            mp.pause();
            playBtn.setBackgroundResource(R.drawable.plaaay);
            musicView.pauseNotesFall();
        }

    }
}
