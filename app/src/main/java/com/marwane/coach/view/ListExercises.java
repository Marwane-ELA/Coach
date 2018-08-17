package com.marwane.coach.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.marwane.coach.R;
import com.marwane.coach.adapter.RecyclerViewAdapter;
import com.marwane.coach.model.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Bronnen:
 * https://developer.android.com/guide/topics/ui/layout/recyclerview
 * https://www.youtube.com/playlist?list=PLk7v1Z2rk4hjHrGKo9GqOtLs1e2bglHHA
 * https://www.youtube.com/watch?v=Vyqz_-sJGFk&t=6s
 * https://www.youtube.com/watch?v=pT8IklxqY4Q&t=2s
 *
 */
public class ListExercises extends AppCompatActivity {

    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);
        
        initData();

        recyclerView = (RecyclerView)findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        exerciseList.add(new Exercise(R.drawable.easy_pose,"Easy Pose"));
        exerciseList.add(new Exercise(R.drawable.cobra_pose,"Cobra Pose"));
        exerciseList.add(new Exercise(R.drawable.downward_facing_dog,"Downward Facing Dog"));
        exerciseList.add(new Exercise(R.drawable.boat_pose,"Boat pose"));
        exerciseList.add(new Exercise(R.drawable.half_pigeon,"half_pigeon"));
        exerciseList.add(new Exercise(R.drawable.low_lunge,"Low Lunge"));
        exerciseList.add(new Exercise(R.drawable.upward_bow,"Upward pose"));
        exerciseList.add(new Exercise(R.drawable.crescent_lunge,"Crescent Lunge"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose,"Warrior Pose"));
        exerciseList.add(new Exercise(R.drawable.bow_pose,"Bow pose"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose_2,"Warrior pose 2"));

    }
}
