package com.workout.workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseReference myRef;
    List<Workout> workouts;
    private RecyclerView workoutRecyclerView;
    private WorkoutsAdapter workoutsAdapter;
    private final String TAG = "main";
    private final int WORKOUTS_LIMIT = 3;
    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: here");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Workouts");
        workouts = new ArrayList<Workout>();
        workoutRecyclerView = (RecyclerView) findViewById(R.id.workoutsRecycler);
        workoutsAdapter = new WorkoutsAdapter(workouts);



        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Workout w = (ds.getValue(Workout.class));
                    workouts.add(w);

                }
                Collections.reverse(workouts);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                workoutRecyclerView.setLayoutManager(mLayoutManager);
                workoutRecyclerView.setItemAnimator(new DefaultItemAnimator());
                workoutRecyclerView.setAdapter(workoutsAdapter);
                pos = workouts.size() - 1;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.orderByKey().limitToLast(WORKOUTS_LIMIT).addListenerForSingleValueEvent(valueEventListener);



    }

    public void newWorkout(View view) {
        Intent intent = new Intent(this, new_workout_activity.class);
        startActivity(intent);
    }


    public void getMoreWorkouts(View view) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Workout w = (ds.getValue(Workout.class));
                    Log.d(TAG, "getMore: " + w.getWoName());
                    if (!workouts.contains(w))
                        workouts.add(w);
                }
                workoutsAdapter.notifyDataSetChanged();
                Collections.sort(workouts);
                Collections.reverse(workouts);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        };

        String endRetrievingIdPoint = workouts.get(pos).getId();
        myRef.orderByKey().endAt(endRetrievingIdPoint).limitToFirst(WORKOUTS_LIMIT).addListenerForSingleValueEvent(valueEventListener);

        pos = workouts.size() - 1;
    }
}
