package com.example.fitnessapp.Model;

import com.example.fitnessapp.R;

import java.util.ArrayList;
import java.util.List;

public class LegExercises {
    List<Exercises> exercises;

    public LegExercises() {
        exercises = new ArrayList<>();

        exercises.add(new Exercises("1", "Jumping Squats", 30, R.raw.leg_1, R.raw.captain_whistle));
        exercises.add(new Exercises("2", "Jumping Jacks", 30, R.raw.leg_2, R.raw.captain_whistle));
        exercises.add(new Exercises("3", "Lunges", 30, R.raw.leg_3, R.raw.captain_whistle));
        exercises.add(new Exercises("4", "Step-Up onto Chair", 30, R.raw.leg_4, R.raw.captain_whistle));
        exercises.add(new Exercises("5", "Squats onto Box", 30, R.raw.leg_5, R.raw.captain_whistle));
        exercises.add(new Exercises("6", "Fast Split Squat", 30, R.raw.leg_6, R.raw.captain_whistle));
        exercises.add(new Exercises("7", "Fast Split Squat", 30, R.raw.leg_7, R.raw.captain_whistle));
        exercises.add(new Exercises("8", "Squats and Lunge with a front kick", 30, R.raw.leg_8, R.raw.captain_whistle));
        exercises.add(new Exercises("9", "Squats and Lunge with a front arm", 30, R.raw.leg_9, R.raw.captain_whistle));
        exercises.add(new Exercises("10", "Side leg circles", 30, R.raw.leg_10, R.raw.captain_whistle));
    }

    public List<Exercises> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercises> exercises) {
        this.exercises = exercises;
    }
}
