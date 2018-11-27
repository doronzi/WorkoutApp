package com.workout.workout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExistingWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_workout);

        final TextView workoutNameTxtView = (TextView)findViewById(R.id.workoutName);
        final TextView dateTxtView = (TextView)findViewById(R.id.dateTextView);
        LinearLayout listViewLayout = (LinearLayout)findViewById(R.id.exList);
        WorkoutManager workoutManager = new WorkoutManager(listViewLayout);
        Intent intent = getIntent();
        Workout workout = (Workout)intent.getSerializableExtra("workout");
        workoutNameTxtView.setText(workout.getWoName());
        dateTxtView.setText(workout.getDate());
        for (int i=0; i< workout.getNumOfExs(); i++){
            Exercise ex = workout.getEx(i);
            if (ex != null){
                workoutManager.addEx(getBaseContext(), ex);
            }

        }

    }
}
