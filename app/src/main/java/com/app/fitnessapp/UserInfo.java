package com.app.fitnessapp;

public class UserInfo
{

    String weight;
    String height;


    String caloricIntake;
    String date;


    public UserInfo() {
    }



    public UserInfo(String weight, String height,String caloricIntake,String date) {
        this.weight = weight;
        this.height = height;
        this.caloricIntake = caloricIntake;
        this.date = date;


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


    public String getCaloricIntake() {
        return caloricIntake;
    }

    public void setCaloricIntake(String caloricIntake) {
        this.caloricIntake = caloricIntake;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String ToString()
    {
        return "weight:" + weight + "\n" + "height:" + height +"\n" + "caloricIntake:"+ caloricIntake + "\n" + "date:" + date;

    }
}
