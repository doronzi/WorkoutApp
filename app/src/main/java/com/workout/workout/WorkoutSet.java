package com.workout.workout;

public class WorkoutSet {
    private int reps;
    private float weight;
    private double rest;
    private double setTime;
    public WorkoutSet(){
        reps = 0;
        weight = 0;
    }
    public WorkoutSet(int reps, float weight){
        this.reps = reps;
        this.weight = weight;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setRest(double rest) {
        this.rest = rest;
    }

    public void setSetTime(double setTime) {
        this.setTime = setTime;
    }

    public int getReps(){
        return reps;
    }

    public float getWeight() {
        return weight;
    }
    public String toString(){
        return (Integer.toString(reps) +"Reps of "+Float.toString(weight) +"Kgs.\n"  +
                "Set Time: "+Double.toString(setTime) +" Rest Time (Before set): " + Double.toString(rest));
    }
}
