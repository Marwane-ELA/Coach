package com.marwane.coach.view;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.marwane.coach.R;
import com.marwane.coach.model.Exercise;
import com.marwane.coach.tools.Common2;
import com.marwane.coach.tools.YogaDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Bronnen:
 *
 * https://www.youtube.com/watch?v=dxeKPU0MBU8&t=630s
 */
public class Daily_TrainingActivity extends AppCompatActivity {

    Button btnStart;
    ImageView ex_image;
    TextView txtgetReady,txtCountdown,txtTimer,ex_name;
    ProgressBar progressBar;
    LinearLayout layoutGetReady;

    int ex_id = 0,limit_time=0;
    List<Exercise> list = new ArrayList<>();

    YogaDB yogaDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__training);

        initData();

        yogaDB = new YogaDB(this);



        btnStart = (Button)findViewById(R.id.btnStart);
        ex_image = (ImageView)findViewById(R.id.detail_image);

        txtCountdown = (TextView) findViewById(R.id.txtCountDown);
        txtgetReady = (TextView) findViewById(R.id.txtGetReady);
        txtTimer = (TextView) findViewById(R.id.timer);
        ex_name = (TextView)findViewById(R.id.titleEx);

        layoutGetReady = (LinearLayout)findViewById(R.id.layout_get_ready);

        progressBar = (MaterialProgressBar)findViewById(R.id.progressBar);

        //set data
        progressBar.setMax(list.size());

        setExerciseInformation(ex_id);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if the user click on the start button, i will show get ready of 5s and then we will show the exercise
                if(btnStart.getText().toString().toLowerCase().equals("start")){
                    showGetReady();
                    btnStart.setText("done");
                }
                else if(btnStart.getText().toString().toLowerCase().equals("done")){

                    //if user click done while exercise is running we just cancel count down and show RestTime
                    if(yogaDB.getSettingMode() == 0){
                        exercisesEasyModeCountDown.cancel();
                    }
                    else if(yogaDB.getSettingMode() == 1){
                        exercisesMediumModeCountDown.cancel();
                    }
                    else if(yogaDB.getSettingMode() == 2){
                        exercisesHardModeCountDown.cancel();
                    }


                    restTimeCountDown.cancel();

                    if(ex_id < list.size()){
                        showResetTime();
                        ex_id++;
                        progressBar.setProgress(ex_id);
                        txtTimer.setText("");

                    }
                    else{
                        showFinished();
                    }
                }
                else{
                    if(yogaDB.getSettingMode() == 0){
                        exercisesEasyModeCountDown.cancel();
                    }
                    else if(yogaDB.getSettingMode() == 1){
                        exercisesMediumModeCountDown.cancel();
                    }
                    else if(yogaDB.getSettingMode() == 2){
                        exercisesHardModeCountDown.cancel();
                    }

                    restTimeCountDown.cancel();

                    if(ex_id < list.size()){
                        setExerciseInformation(ex_id);
                    }
                    else
                    {
                        showFinished();
                    }
                }

            }
        });

    }

    private void showResetTime() {

        ex_image.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        btnStart.setText(getString(R.string.Skip));
        btnStart.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);

        restTimeCountDown.start();

        txtgetReady.setText(getString(R.string.restTime));



    }

    private void showGetReady() {
        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        txtgetReady.setText(getString(R.string.getReady));
        new CountDownTimer(6000,1000){

            @Override
            public void onTick(long l) {
                txtCountdown.setText(""+(l-1000)/1000);
            }

            @Override
            public void onFinish() {
                showExercises();
            }
        }.start();
    }

    private void showExercises() {

        if(ex_id < list.size()){ //list size contains all exercises
            ex_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetReady.setVisibility(View.INVISIBLE);

            if(yogaDB.getSettingMode() == 0){
                exercisesEasyModeCountDown.start();
            }
            else if(yogaDB.getSettingMode() == 1){
                exercisesMediumModeCountDown.start();
            }
            else if(yogaDB.getSettingMode() == 2){
                exercisesHardModeCountDown.start();
            }


            //set data
            ex_image.setImageResource(list.get(ex_id).getImage_id());
            ex_name.setText(list.get(ex_id).getName());




        }
        else
        {
            showFinished();
        }

    }

    //Countdown
    CountDownTimer exercisesEasyModeCountDown = new CountDownTimer(Common2.TIME_LIMIT_EASY,1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()-1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText(getString(R.string.btnStart));


            }
            else{
                showFinished();
            }

        }
    };
    CountDownTimer exercisesMediumModeCountDown = new CountDownTimer(Common2.TIME_LIMIT_MEDIUM,1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()-1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText(getString(R.string.btnStart));


            }
            else{
                showFinished();
            }

        }
    };
    CountDownTimer exercisesHardModeCountDown = new CountDownTimer(Common2.TIME_LIMIT_HARD,1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()-1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText(getString(R.string.btnStart));


            }
            else{
                showFinished();
            }

        }
    };


    CountDownTimer restTimeCountDown = new CountDownTimer(10000,1000) {
        @Override
        public void onTick(long l) {
            txtCountdown.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            //After a break of 10seconds we start the new exercise
          setExerciseInformation(ex_id);
          showExercises();
        }
    };

    private void showFinished() {

        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        txtgetReady.setText(getString(R.string.finished));
        txtCountdown.setText(getString(R.string.congr));
        txtCountdown.setTextSize(20);

        //Save workout done to DB
        yogaDB.saveDay("" + Calendar.getInstance().getTimeInMillis());

    }

    private void setExerciseInformation(int id) {

        ex_image.setImageResource(list.get(id).getImage_id());
        ex_name.setText(list.get(id).getName());
        btnStart.setText(getString(R.string.btnStart));

        ex_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.INVISIBLE);
    }

    private void initData() {
        list.add(new Exercise(R.drawable.easy_pose,"Easy Pose"));
        list.add(new Exercise(R.drawable.cobra_pose,"Cobra Pose"));
//        list.add(new Exercise(R.drawable.downward_facing_dog,"Downward Facing Dog"));
//        list.add(new Exercise(R.drawable.boat_pose,"Boat pose"));
//        list.add(new Exercise(R.drawable.half_pigeon,"half_pigeon"));
//        list.add(new Exercise(R.drawable.low_lunge,"Low Lunge"));
//        list.add(new Exercise(R.drawable.upward_bow,"Upward pose"));
//        list.add(new Exercise(R.drawable.crescent_lunge,"Crescent Lunge"));
//        list.add(new Exercise(R.drawable.warrior_pose,"Warrior Pose"));
//        list.add(new Exercise(R.drawable.bow_pose,"Bow pose"));
//        list.add(new Exercise(R.drawable.warrior_pose_2,"Warrior pose 2"));

    }
}
