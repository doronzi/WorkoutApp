package com.workout.workout;

public class WorkoutSet {
    private int reps;
    private float weight;
    private double rest;
    private double setTime;
    private boolean isInitilized;
    public WorkoutSet(){
        reps = 0;
        weight = 0;
        this.isInitilized = false;
    }
    public WorkoutSet(int reps, float weight){
        this.reps = reps;
        this.weight = weight;
        this.isInitilized = false;
    }
    public WorkoutSet(int reps, float weight, double setTime, double restTime){
        this.reps = reps;
        this.weight = weight;
        this.rest = restTime;
        this.setTime = setTime;
        this.isInitilized = false;
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

    public double getRest() {
        return rest;
    }

    public double getSetTime() {
        return setTime;
    }

    public String toString(){
        return (Integer.toString(reps) +"Reps of "+Float.toString(weight) +"Kgs.\n"  +
                "Set Time: "+Double.toString(setTime) +" Rest Time (Before set): " + Double.toString(rest));
    }

    public boolean isInitilized() {
        return isInitilized;
    }

    public void setInitilized(boolean initilized) {
        isInitilized = initilized;
    }
}
