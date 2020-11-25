package com.app.fitnessapp;

import android.widget.Spinner;

public class TargetGoals {
    String targetWeight;
    String targetCalorie;


    public TargetGoals(String targetWeight, String targetCalorie) {
        this.targetWeight = targetWeight;
        this.targetCalorie = targetCalorie;
    }

    public TargetGoals() {
    }

    public String getTargetWeight() {
        return targetWeight ;
    }

    public void setTargetWeight(String targetWeight) {
        this.targetWeight = targetWeight;
    }

    public String getTargetCalorie() {
        return targetCalorie;
    }

    public void setTargetCalorie(String targetCalorie) {
        this.targetCalorie = targetCalorie;
    }
}

