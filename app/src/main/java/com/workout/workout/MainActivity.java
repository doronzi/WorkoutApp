package com.workout.workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseReference myRef;
    List<Workout> workouts;
    private RecyclerView exRecyclerView;
    private WorkoutsAdapter workoutsAdapter;
    private final String TAG = "main";
    private final int WORKOUTS_LIMIT = 2;
    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: here");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Workouts");
        workouts = new ArrayList<Workout>();
        exRecyclerView = (RecyclerView) findViewById(R.id.workoutsRecycler);
        workoutsAdapter = new WorkoutsAdapter(workouts);


        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Workout w = (ds.getValue(Workout.class));
                    Log.d(TAG, "oncreate: "+w.getWoName());
                    workouts.add(w);
                    Log.d(TAG, "onCreate:" + ds.getKey());
                    Log.i(TAG, "onCreate: " + String.valueOf(workouts));


                    Log.i(TAG, "onCreate: " + String.valueOf(workouts));

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    exRecyclerView.setLayoutManager(mLayoutManager);
                    exRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    exRecyclerView.setAdapter(workoutsAdapter);
                    pos = workouts.size() - 1;
                    Log.i(TAG, "onCreate: pos " + pos);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.limitToLast(WORKOUTS_LIMIT).addListenerForSingleValueEvent(valueEventListener);



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
                    if (!workouts.contains(w)) workouts.add(w);

                    Log.d(TAG, "getMore:" + ds.getKey());
                    Log.i(TAG, "getMore: " + String.valueOf(workouts));


                    Log.i(TAG, "getMore: " + String.valueOf(workouts));

                    workoutsAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        };
        Log.i(TAG, "getMoreWorkouts: pos " + pos);
        myRef.orderByChild("id").limitToLast(WORKOUTS_LIMIT + 1).startAt(workouts.get(pos).getId()).addListenerForSingleValueEvent(valueEventListener);

        pos = workouts.size() - 1;
    }
}
