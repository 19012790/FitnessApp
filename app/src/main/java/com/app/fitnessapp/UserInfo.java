package com.app.fitnessapp;

public class UserInfo
{

    String weight;
    String height;
    String targetWeight;

    public UserInfo() {
    }

    public String getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(String targetWeight) {
        this.targetWeight = targetWeight;
    }

    public UserInfo(String weight, String height) {
        this.weight = weight;
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    public String ToString()
    {
        return "weight" + weight + "/n" + "height" + height;

    }
}
