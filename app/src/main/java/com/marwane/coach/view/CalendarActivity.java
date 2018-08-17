package com.marwane.coach.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marwane.coach.R;
import com.marwane.coach.tools.WorkoutDoneDecorator;
import com.marwane.coach.tools.YogaDB;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


/**
 * Bronnen:
 * https://developer.android.com/reference/java/util/Calendar
 * https://www.youtube.com/watch?v=xs5406vApTo
 * https://www.youtube.com/watch?v=pT8IklxqY4Q&index=3&list=PLVi0KDqtiGJwfC8a5jS6w6GctEgRKdXCA
 */
public class CalendarActivity extends AppCompatActivity {

    MaterialCalendarView materialCalendarView;
    HashSet<CalendarDay> list = new HashSet<>();

    YogaDB yogaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        yogaDB = new YogaDB(this);

        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendar);
        //get all workout day from db ,convert to hashset and then display de decorator on the days included in the hashset
        List<String>workoutDay = yogaDB.getWorkoutDays();
        HashSet<CalendarDay>convertedList = new HashSet<>();
        for (String value:workoutDay)
                convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
        materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));



    }
}
