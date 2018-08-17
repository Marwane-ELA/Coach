package com.marwane.coach.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.marwane.coach.R;
import com.marwane.coach.notification.AlarmNotificationReceiver;
import com.marwane.coach.tools.YogaDB;

import java.util.Calendar;
import java.util.Date;

/**
 * https://developer.android.com/training/notify-user/build-notification
 * https://www.youtube.com/watch?v=SWsuijO5NGE
 * https://www.youtube.com/watch?v=_1U2hDOcdAI
 * https://www.youtube.com/watch?v=TdlnnuTfPAw
 */
public class SettingActivity extends AppCompatActivity {

    Button btnSave;
    RadioButton rdiEasy,rdiMedium,rdiHard;
    RadioGroup rdiGroup;
    YogaDB yogaDB;
    ToggleButton switchAlarm;
    TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //Init view
        btnSave = (Button)findViewById(R.id.btnSave);
        rdiEasy = (RadioButton)findViewById(R.id.rdiEasy);
        rdiMedium = (RadioButton)findViewById(R.id.rdiMedium);
        rdiHard = (RadioButton)findViewById(R.id.rdihard);
        rdiGroup = (RadioGroup)findViewById(R.id.rdiGroup);

        switchAlarm = (ToggleButton)findViewById(R.id.switchAlarm);

        timePicker = (TimePicker)findViewById(R.id.timePicker);

        yogaDB = new YogaDB(this);

        int mode = yogaDB.getSettingMode();
        setRadioButton(mode);

        //Event

        btnSave.setOnTouchListener(new View.OnTouchListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                saveWorkoutMode();
                saveAlarm(switchAlarm.isChecked());
                Toast.makeText(SettingActivity.this,getString(R.string.ToastSetting),Toast.LENGTH_SHORT).show();
                finish();
                return true;
            }


        });


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void saveAlarm(boolean checked) {
        //notif
        if(checked){
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;

            //appelle l'alarm pour la notif
            intent = new Intent(SettingActivity.this, AlarmNotificationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

            //set time to alarm
            Calendar calendar = Calendar.getInstance();
            Date toDay = Calendar.getInstance().getTime();
            calendar.set(toDay.getYear(),toDay.getMonth(),toDay.getDay(),timePicker.getHour(),timePicker.getMinute());

            manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

            Log.d("DEBUG","Alarm will make at:"+timePicker.getHour()+": "+timePicker.getMinute());

        }
        else
        {
            //cancel alarm
            Intent intent = new Intent(SettingActivity.this, AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);


        }
    }

    private void saveWorkoutMode(){
        int selectedID = rdiGroup.getCheckedRadioButtonId();
        if(selectedID == rdiEasy.getId()){
            yogaDB.saveSettingMode(0);
        }
        else if(selectedID == rdiMedium.getId()){
            yogaDB.saveSettingMode(1);
        }
        else  if(selectedID == rdiHard.getId()){
            yogaDB.saveSettingMode(2);
        }

    }

    private void setRadioButton(int mode) {
       if(mode == 0) {
           rdiGroup.check(R.id.rdiEasy);
       }
       else if(mode == 1) {
           rdiGroup.check(R.id.rdiMedium);
       }
       else if(mode == 2) {
            rdiGroup.check(R.id.rdihard);
        }
    }
}
