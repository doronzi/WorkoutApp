package com.workout.workout;

import android.content.Context;
import android.graphics.Color;
import android.opengl.Visibility;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

        listViewLayout = linearLayout;
        listViews = new ArrayList<>();
        listAdapters = new ArrayList<>();
        exList = new ArrayList<>();
    }
    public void addNewExercise(int numOfSets, String exName , Context context){
        Log.d("mylog", "addNewExercise: "+ String.valueOf(context));
        Exercise ex = new Exercise(numOfSets, exName);
        addEx(context ,ex);

    }

    public void addEx(Context context, Exercise ex){

        exList.add(ex);
        final ListView tempListView = new ListView(context);
        final LinearLayout tempLinearLayout = new LinearLayout(context);
        TextView listViewTitleTxt = new TextView(context);
        listViewTitleTxt.setText(ex.getExName());
        listViewTitleTxt.setTextColor(Color.BLACK);
        listViewTitleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempListView.getVisibility() == View.GONE)
                    tempListView.setVisibility(View.VISIBLE);
                else
                    tempListView.setVisibility(View.GONE);
            }
        });
        tempLinearLayout.addView(listViewTitleTxt);
        tempLinearLayout.addView(tempListView);
        SetsAdapter tempAdapter = new SetsAdapter(ex.getSets(),context);
        tempListView.setAdapter(tempAdapter);
        listAdapters.add(tempAdapter);
        listViews.add(tempListView);
        listViewLayout.addView(tempLinearLayout);
        tempAdapter.notifyDataSetChanged();
    }
    public Exercise getExByPos(int pos){
        if (pos < exList.size()) return exList.get(pos);
        else return null;
    }
    public List<Exercise> getExList() {
        return exList;
    }
    public void endSet(int pos, int reps, float weight, double setTime, double restTime){
        Exercise ex = exList.get(pos);
        ex.setSet(reps,weight,setTime,restTime);
        ex.increntCounter();
        Log.d("mylog", "endSet: Ex " + String.valueOf(exList.get(pos).getSets().get(0).getReps()));
        Log.d("mylog", "endSet: " +String.valueOf(listAdapters.get(pos).mContext));
        Log.d("mylog", "endSet: " + String.valueOf(listAdapters.size()) + " " + String.valueOf(exList.size())+ " " +
                String.valueOf(listViews.size()));
        listAdapters.get(pos).notifyDataSetChanged();
    }
}
