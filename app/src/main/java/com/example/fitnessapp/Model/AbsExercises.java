package com.example.fitnessapp.Model;

import com.example.fitnessapp.R;

import java.util.ArrayList;
import java.util.List;

public class AbsExercises {

    List<Exercises> exercises;

    public AbsExercises() {
        exercises = new ArrayList<>();

        exercises.add(new Exercises("1", "Reverse Crunches", 30, R.raw.abs_1, R.raw.captain_whistle));
        exercises.add(new Exercises("2", "Mountain Climber", 30, R.raw.abs_2, R.raw.captain_whistle));
        exercises.add(new Exercises("3", "Burpees", 30, R.raw.abs_3, R.raw.captain_whistle));
        exercises.add(new Exercises("4", "Russian Twist", 30, R.raw.abs_4, R.raw.captain_whistle));
        exercises.add(new Exercises("5", "Hindu Push-Ups", 30, R.raw.abs_5, R.raw.captain_whistle));
        exercises.add(new Exercises("6", "Staggered Push-Ups", 30, R.raw.abs_6, R.raw.captain_whistle));
        exercises.add(new Exercises("7", "Reverse Crunches", 30, R.raw.abs_7, R.raw.captain_whistle));
        exercises.add(new Exercises("8", "Push-Up & Rotation", 30, R.raw.abs_8, R.raw.captain_whistle));
        exercises.add(new Exercises("9", "Rotation Exercises", 30, R.raw.abs_9, R.raw.captain_whistle));
        exercises.add(new Exercises("10", "Lower back Exercises", 30, R.raw.abs_10, R.raw.captain_whistle));
    }

    public List<Exercises> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercises> exercises) {
        this.exercises = exercises;
    }
}
