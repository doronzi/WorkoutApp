package com.workout.workout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Workout {
    private String woName;
    private List<Exercise> exs;
    private String date;

    public Workout(String name){
        exs = new ArrayList<Exercise>();
        woName = name;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        date = dateFormat.format(new Date());
    }

    public String getWoName() {
        return woName;
    }

    public String getDate() {
        return date;
    }

    public List<Exercise> getExs() {
        return exs;
    }

    public void addEx(String exName, int number_of_sets){
        Exercise ex = new Exercise(number_of_sets, exName);
        exs.add(ex);
    }
}
