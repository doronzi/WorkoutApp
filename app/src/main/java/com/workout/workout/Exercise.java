package com.workout.workout;

public class Exercise {
    private WorkoutSet[] sets;
    private String exName;
    private boolean isExpanded;
    private int setCounter;

    public Exercise(int numOfSets, String name){
        sets = new WorkoutSet[numOfSets];
        for (int i =0; i < numOfSets; i++)
            sets[i] = new WorkoutSet();
        exName = name;
        setCounter = 0;
    }

    public String getExName() {
        return exName;
    }
    public int getNumberOfSets(){
        return sets.length;
    }

    public WorkoutSet[] getSets() {
        return sets;
    }
    public void setSet(int reps, float weight, double setTime, double restTime){
        sets[setCounter].setReps(reps);
        sets[setCounter].setWeight(weight);
        sets[setCounter].setRest(restTime);
        sets[setCounter].setSetTime(setTime);
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public void setSets(WorkoutSet[] sets) {
        this.sets = sets;
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
        return exString.substring(0, exString.length() - 1);
    }
    public boolean isEnded(){
        if (setCounter >= sets.length) return true;
        return false;
    }
}
