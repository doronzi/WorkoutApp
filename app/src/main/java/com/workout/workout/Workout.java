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
    private String id;

    public Workout(){}

    public Workout(String name){
        exs = new ArrayList<Exercise>();
        woName = name;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(new Date());
        DateFormat dateIDFormat = new SimpleDateFormat("yyyyMMddHHmm");
        id = dateIDFormat.format(new Date());
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

    public String getId() {
        return id;
    }


    public void setExs(List<Exercise> exs) {
        this.exs = new ArrayList<Exercise>(exs);
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Workout)obj).getId());
    }
}
