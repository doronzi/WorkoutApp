package com.workout.workout;

import java.util.ArrayList;

public class Exercise {
    private ArrayList<WorkoutSet> sets;
    private String exName;
    private boolean isExpanded;
    private int setCounter;
    private int numOfSets;

    public Exercise(int numOfSets, String name){
        //sets = new WorkoutSet[numOfSets];
//        for (int i =0; i < numOfSets; i++)
//            sets[i] = new WorkoutSet();
        sets = new ArrayList<WorkoutSet>();
        exName = name;
        this.numOfSets = numOfSets;
        setCounter = 0;
    }

    public String getExName() {
        return exName;
    }
    public int getNumberOfSets(){
        return sets.size();
    }

//    public WorkoutSet[] getSets() {
//        return sets;
//    }

    public ArrayList<WorkoutSet> getSets() {
        return sets;
    }

    public void setSet(int reps, float weight, double setTime, double restTime){
        sets.add(new WorkoutSet(reps,weight,setTime,restTime));
//        sets[setCounter].setReps(reps);
//        sets[setCounter].setWeight(weight);
//        sets[setCounter].setRest(restTime);
//        sets[setCounter].setSetTime(setTime);
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }


    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getSetCounter() {
        return setCounter;
    }
    public void increntCounter(){
        setCounter++;
    }
    public String toString(){
        String exString = "";
        for (WorkoutSet set : sets)
            exString += set.toString() +"\n";
        if (exString != "") exString = exString.substring(0, exString.length() - 1);
        return exString;
    }

    public boolean isEnded(){
        if (setCounter >=numOfSets ) return true;
//        if (setCounter >= sets.length) return true;
        return false;
    }
}
