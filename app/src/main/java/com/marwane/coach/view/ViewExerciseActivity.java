package com.marwane.coach.view;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marwane.coach.R;
import com.marwane.coach.tools.Common2;
import com.marwane.coach.tools.YogaDB;

/**
 * information change between two activities
 */
public class ViewExerciseActivity extends AppCompatActivity {

    int image_id;
    String name;

    TextView timer,title;
    ImageView detail_image;

    Button btnStart,btnVideo;

    boolean isRunning = false;

    YogaDB yogaDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);

        yogaDB = new YogaDB(this);



        //timer and image
        timer = (TextView)findViewById(R.id.timer);
        title = (TextView)findViewById(R.id.titleEx);
        detail_image = (ImageView)findViewById(R.id.detail_image);

        btnVideo = (Button)findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (ViewExerciseActivity.this, VideoActivity.class);
                intent.putExtra("image_id",image_id);
                intent.putExtra("name",name);
                startActivity(intent);

            }
        });
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRunning)
                {
                    btnStart.setText("DONE");

                    int timeLimit = 0;
                    if(yogaDB.getSettingMode() == 0){
                        timeLimit = Common2.TIME_LIMIT_EASY;
                    }
                    else  if(yogaDB.getSettingMode() == 1){
                        timeLimit = Common2.TIME_LIMIT_MEDIUM;
                    }
                    else  if(yogaDB.getSettingMode() == 2){
                        timeLimit = Common2.TIME_LIMIT_HARD;
                    }

                    new CountDownTimer(timeLimit, 1000) {
                        @Override
                        public void onTick(long l) {
                            timer.setText(""+l/1000);
                        }

                        @Override
                        public void onFinish() {
                            //ads here
                            Toast.makeText(ViewExerciseActivity.this,"Finish!!!",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                }
                else{
                    Toast.makeText(ViewExerciseActivity.this,"Finish!!!",Toast.LENGTH_SHORT).show();
                    finish();
                }

                isRunning = !isRunning;
            }
        });

        timer.setText("");

        if(getIntent() != null)
        {
            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");

            //detail_image.setImageResource(image_id);
            title.setText(name);
        }
    }



//---
}
