package com.workout.workout;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.workout.workout.Exercise;
import com.workout.workout.SetsAdapter;

import java.util.ArrayList;
import java.util.List;

public class WorkoutManager {
    private List<ListView> listViews;
    private List<SetsAdapter> listAdapters;
    private LinearLayout listViewLayout;
    private List<Exercise> exList;
    public WorkoutManager(LinearLayout linearLayout){
        this.listViewLayout = linearLayout;
        listViews = new ArrayList<>();
        listAdapters = new ArrayList<>();
        exList = new ArrayList<>();
    }
    public void addExercise(int numOfSets, String exName ,Context context){
        Log.d("mylog", "addExercise: "+ String.valueOf(context));
        Exercise ex = new Exercise(numOfSets, exName);
        exList.add(ex);
        ListView tempListView = new ListView(context);
        TextView listViewTitleTxt = new TextView(context);
        listViewTitleTxt.setText(ex.getExName());
        listViewTitleTxt.setTextColor(Color.BLACK);
        tempListView.addHeaderView(listViewTitleTxt);
        SetsAdapter tempAdapter = new SetsAdapter(ex.getSets(),context);
        tempListView.setAdapter(tempAdapter);
        listAdapters.add(tempAdapter);
        listViews.add(tempListView);
        listViewLayout.addView(tempListView);
    }
    public Exercise getExerciseFromPosition(int pos){
        return exList.get(pos);
    }
    public SetsAdapter getAdapterFromPosition(int pos){
        return listAdapters.get(pos);
    }

    public List<Exercise> getExList() {
        return exList;
    }
    public void endSet(int pos, int reps, float weight, double setTime, double restTime){
        Exercise ex = exList.get(pos);
        ex.setSet(reps,weight,setTime,restTime);
        ex.increntCounter();
        Log.d("mylog", "endSet: " +String.valueOf(listAdapters.get(pos).mContext)+"pos: "+ String.valueOf(pos));
        listAdapters.get(pos).notifyDataSetChanged();
    }
}
