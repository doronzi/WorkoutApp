package com.workout.workout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Chronometer;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_workout_activity extends AppCompatActivity {
    Workout newWorkout;
    Button addExButton, addSetButton, endSetButton, finishWorkoutButton;
    Chronometer setChronmtr, globalChronmtr, restChronmtr;
    private double restTime = 0;
    int currentEx = 0;
    DatabaseReference myRef;
    LinearLayout listViewLayout;
    WorkoutManager workoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_workout_activity);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        //myRef.setValue("Workouts");

        final TextView workoutNameTxtView = (TextView)findViewById(R.id.workoutName);
        final TextView dateTxtView = (TextView)findViewById(R.id.dateTextView);
        setChronmtr = (Chronometer)findViewById(R.id.simpleChronometerSetTime);
        globalChronmtr = (Chronometer)findViewById(R.id.simpleChronometerGlobalTime);
        restChronmtr = (Chronometer)findViewById(R.id.simpleChronometerRestTime);
        addExButton = (Button) findViewById(R.id.addEx);
        addSetButton = (Button)findViewById(R.id.newSet);
        endSetButton = (Button)findViewById(R.id.endSet);
        finishWorkoutButton = (Button)findViewById(R.id.finishWorkout);
        listViewLayout = (LinearLayout)findViewById(R.id.exList);
        workoutManager = new WorkoutManager(listViewLayout);


        globalChronmtr.start();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

 //Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newWorkout = new Workout(input.getText().toString());
                workoutNameTxtView.setText(newWorkout.getWoName());
                dateTxtView.setText(newWorkout.getDate());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        builder.show();

    }
    public void addEx(View view){
        if (finishWorkoutButton.isEnabled())
            finishWorkoutButton.setEnabled(false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("add Exercise");

    // Set up the input
        final TextView exerciseName = new TextView(this);
        exerciseName.setText("Please Enter Exercise name");
        final EditText inputName = new EditText(this);
        final TextView numOfSets = new TextView(this);
        numOfSets.setText("what is the number of sets you should do?");
        final EditText inputNumOfSets = new EditText(this);
        final LinearLayout alertLayout = new LinearLayout(this);
        alertLayout.setOrientation(LinearLayout.VERTICAL);
    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        alertLayout.addView(exerciseName);
        alertLayout.addView(inputName);
        alertLayout.addView(numOfSets);
        alertLayout.addView(inputNumOfSets);
        builder.setView(alertLayout);

    // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                addSetButton.setEnabled(true);
                addExButton.setEnabled(false);

                workoutManager.addNewExercise(Integer.parseInt(inputNumOfSets.getText().toString()),
                        inputName.getText().toString(),getBaseContext());

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }
    public void newSet(View view) {
        long systemCurrTime = SystemClock.elapsedRealtime();

        restTime =  (systemCurrTime - restChronmtr.getBase()) /1000;
        restChronmtr.stop();


        systemCurrTime = SystemClock.elapsedRealtime();
        setChronmtr.setBase(systemCurrTime);
        setChronmtr.start();
        endSetButton.setEnabled(true);
        addSetButton.setEnabled(false);
    }
    public void endSet(View view){
        long systemCurrTime = SystemClock.elapsedRealtime();


        long elapsedMillis = systemCurrTime - setChronmtr.getBase();
        final double setTime = elapsedMillis / 1000;
        setChronmtr.stop();

        systemCurrTime = SystemClock.elapsedRealtime();
        restChronmtr.setBase(systemCurrTime);
        restChronmtr.start();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Finish Set");

        // Set up the input
        final TextView repsString = new TextView(this);
        repsString.setText("Please Enter The number of Reps");
        final EditText inputReps = new EditText(this);
        final TextView weightString = new TextView(this);
        weightString.setText("Please Enter The Weight");
        final EditText inputWeight = new EditText(this);
        final LinearLayout alertLayout = new LinearLayout(this);
        alertLayout.setOrientation(LinearLayout.VERTICAL);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        alertLayout.addView(repsString);
        alertLayout.addView(inputReps);
        alertLayout.addView(weightString);
        alertLayout.addView(inputWeight);
        builder.setView(alertLayout);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Exercise ex = workoutManager.getExByPos(currentEx);

                workoutManager.endSet(currentEx, Integer.parseInt(inputReps.getText().toString()),
                        Float.parseFloat(inputWeight.getText().toString()), setTime, restTime);
                if (ex != null && ex.isEnded()) {
                    addExButton.setEnabled(true);
                    finishWorkoutButton.setEnabled(true);
                    currentEx++;
                } else
                    addSetButton.setEnabled(true);
                endSetButton.setEnabled(false);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }


    public void finishWorkout(View view) {
        newWorkout.setExs(workoutManager.getExList());

        myRef.child("Workouts").child(newWorkout.getId()).setValue(newWorkout);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void toMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
