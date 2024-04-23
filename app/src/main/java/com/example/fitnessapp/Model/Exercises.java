package com.example.fitnessapp.Model;

public class Exercises {

    String ID, exName;
    int exTime, exImage, exSound;

    public Exercises() {
    }

    public Exercises(String ID, String exName, int exTime, int exImage, int exSound) {
        this.ID = ID;
        this.exName = exName;
        this.exTime = exTime;
        this.exImage = exImage;
        this.exSound = exSound;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public int getExTime() {
        return exTime;
    }

    public void setExTime(int exTime) {
        this.exTime = exTime;
    }

    public int getExImage() {
        return exImage;
    }

    public void setExImage(int exImage) {
        this.exImage = exImage;
    }

    public int getExSound() {
        return exSound;
    }

    public void setExSound(int exSound) {
        this.exSound = exSound;
    }
}
