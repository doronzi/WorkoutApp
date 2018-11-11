package com.workout.workout;

import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Chronometer;

import java.util.ArrayList;
import java.util.List;

public class new_workout_activity extends AppCompatActivity {
    final double NO_REST_BEFORE = 0;
    Workout newWorkout;
    private List<Exercise> exList = new ArrayList<>();
    private RecyclerView exRecyclerView;
    private ExerciseAdapter exAdapter;
    Button addExButton, addSetButton, endSetButton;
    Chronometer setChronmtr, globalChronmtr, restChronmtr;
    private double restTime = 0;
    int currentEx = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_workout_activity);

        final TextView workoutNameTxtView = (TextView)findViewById(R.id.workoutName);
        TextView dateTxtView = (TextView)findViewById(R.id.dateTextView);
        setChronmtr = (Chronometer)findViewById(R.id.simpleChronometerSetTime);
        globalChronmtr = (Chronometer)findViewById(R.id.simpleChronometerGlobalTime);
        restChronmtr = (Chronometer)findViewById(R.id.simpleChronometerRestTime);
        addExButton = (Button) findViewById(R.id.addEx);
        addSetButton = (Button)findViewById(R.id.newSet);
        endSetButton = (Button)findViewById(R.id.endSet);

        globalChronmtr.start();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

// //Set up the input
//        final EditText input = new EditText(this);
//// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//        builder.setView(input);
//
//// Set up the buttons
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                workoutNameTxtView.setText(input.getText().toString());
//                newWorkout = new Workout(input.getText().toString());
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//
//        builder.show();
        newWorkout = new Workout("ddd");
        //exList = newWorkout.getExs();
        exRecyclerView = (RecyclerView) findViewById(R.id.exList);
        exAdapter = new ExerciseAdapter(exList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        exRecyclerView.setLayoutManager(mLayoutManager);
        exRecyclerView.setItemAnimator(new DefaultItemAnimator());
        exRecyclerView.setAdapter(exAdapter);
        dateTxtView.setText(newWorkout.getDate().toString());

    }
    public void addEx(View view){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("add Exercise");
//
//    // Set up the input
//        final TextView exerciseName = new TextView(this);
//        exerciseName.setText("Please Enter Exercise name");
//        final EditText inputName = new EditText(this);
//        final TextView numOfSets = new TextView(this);
//        numOfSets.setText("what is the number of sets you should do?");
//        final EditText inputNumOfSets = new EditText(this);
//    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//        builder.setView(exerciseName);
//        builder.setView(inputName);
//        builder.setView(numOfSets);
//        builder.setView(inputNumOfSets);
//
//    // Set up the buttons
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                newWorkout.addEx(inputName.getText().toString(), Integer.parseInt(inputNumOfSets.getText().toString()));
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//
//        builder.show();
        //newWorkout.addEx("jav", 3);
        exList.add(new Exercise(3,"jjj"));
        exAdapter.notifyDataSetChanged();
        addSetButton.setEnabled(true);
        addExButton.setEnabled(false);

    }
    public void newSet(View view) {
        long systemCurrTime = SystemClock.elapsedRealtime();
        if (restTime != NO_REST_BEFORE){
            restTime =  (systemCurrTime - restChronmtr.getBase()) /1000;
            restChronmtr.stop();
        }

        systemCurrTime = SystemClock.elapsedRealtime();
        setChronmtr.setBase(systemCurrTime);
        setChronmtr.start();
        endSetButton.setEnabled(true);
        addSetButton.setEnabled(false);
    }
    public void endSet(View view){
        long systemCurrTime = SystemClock.elapsedRealtime();

        Exercise ex = exList.get(currentEx);
        long elapsedMillis = systemCurrTime - setChronmtr.getBase();
        double elapsedSetSecs = elapsedMillis / 1000;
        setChronmtr.stop();

        systemCurrTime = SystemClock.elapsedRealtime();
        restChronmtr.setBase(systemCurrTime);
        restChronmtr.start();

        ex.setSet(22,3, elapsedSetSecs, restTime);
        ex.increntCounter();
        exAdapter.notifyDataSetChanged();
        if (ex.isEnded())
            addExButton.setEnabled(true);
        else
            addSetButton.setEnabled(true);
        endSetButton.setEnabled(false);

    }


}
