package com.example.fitnessapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fitnessapp.Model.AbsExercises;
import com.example.fitnessapp.Model.Exercises;
import com.example.fitnessapp.Model.LegExercises;
import com.example.fitnessapp.R;
import com.example.fitnessapp.databinding.ActivityExerciseBinding;
import com.example.fitnessapp.databinding.DoneExercisesBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

public class ExerciseActivity extends AppCompatActivity {

    ActivityExerciseBinding binding;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refCalendar = database.getReference("Exercise_Calendar");

    Toolbar toolbar;
    String Workout = "";
    int index = 0;
    boolean isWorkout = true;
    private MediaPlayer mediaPlayer;

    Exercises breakExercises;
    List<Exercises> exercisesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeUI();
        initializeData();
    }

    private void initializeUI() {
        Workout = getIntent().getStringExtra("WORKOUT");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(Workout + " Exercise ");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void initializeData() {
        index = 0;
        isWorkout = true;
        breakExercises = new Exercises("0", "Take Break", 10, R.raw.hold, R.raw.done_bell);
        exercisesList = Workout.equals("Abs") ? new AbsExercises().getExercises() : new LegExercises().getExercises();
        showExercisesWorkout(exercisesList);
//        showExercisesWorkoutData();
    }

    @SuppressLint("SetTextI18n")
    void showExercisesWorkoutData() {
        // Base case: all exercises completed
        if (index >= exercisesList.size()) {
            showDialogDoneExerciseLayout();
            return;
        }

        Exercises exercisesObj;
        // Determine the current exercise based on workout or break phase
        if (isWorkout) {
            exercisesObj = exercisesList.get(index);
        } else {
            exercisesObj = breakExercises;
        }

        // Execute the current exercise
        executeExercise(exercisesObj);

        // Schedule the next exercise
        long duration = exercisesObj.getExTime() * 1000;
        scheduleNextExercise(duration);
    }

    void executeExercise(Exercises exercisesObj) {
        releaseMediaPlayer();
        mediaPlayer = MediaPlayer.create(this, exercisesObj.getExSound());
        mediaPlayer.start();

        binding.exerciseRound.setText("Round " + (index + 1) + " / " + exercisesList.size());
        binding.exerciseName.setText(exercisesObj.getExName());
        binding.timer.setMaximumTime(exercisesObj.getExTime());
        binding.timer.setInitPosition(exercisesObj.getExTime());
        binding.timer.start();
        binding.exerciseAnimationView.setAnimation(exercisesObj.getExImage());
        binding.exerciseAnimationView.playAnimation();
    }

    void scheduleNextExercise(long duration) {
        // Schedule next exercise after the current exercise duration
        new Handler().postDelayed(() -> {
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
            // Toggle workout and break phases
            isWorkout = !isWorkout;
            // Increment index only if it's a workout phase
            index = isWorkout ? index + 1 : index;
            // Recursively call the method for the next exercise
            showExercisesWorkoutData();
        }, duration);
    }

//    //////////////////////////////////////////////////////////////////

    void showExercisesWorkout(List<Exercises> exercises) {
        // Display the first exercise
        getExercise(exercises.get(index));
        // Set the round counter
        updateRoundCounter();
        // Set a listener for the timer's end event
        binding.timer.setBaseTimerEndedListener(this::onTimerEnded);
    }


    // Method to handle timer end event
    private void onTimerEnded() {
        // Reset the timer
        binding.timer.reset();
        // Toggle between workout and break phases
        isWorkout = !isWorkout;
        if (isWorkout) {
            // If it's a workout phase
            index += 1;
            if (index < exercisesList.size()) {
                // If there are more exercises remaining, display the next exercise
                getExercise(exercisesList.get(index));
                updateRoundCounter();
            } else {
                // If all exercises are completed, show a dialog indicating the workout is done
                showDialogDoneExerciseLayout();
            }
        } else {
            // If it's a break phase, display the break exercise
            getExercise(breakExercises);
        }
    }

    // Method to update round counter
    @SuppressLint("SetTextI18n")
    private void updateRoundCounter() {
        binding.exerciseRound.setText("Round " + (index + 1) + " / " + exercisesList.size());
    }

    private void getExercise(Exercises exercise) {
        releaseMediaPlayer();
        mediaPlayer = MediaPlayer.create(this, exercise.getExSound());
        mediaPlayer.start();

        binding.exerciseName.setText(exercise.getExName());
        binding.timer.setMaximumTime(exercise.getExTime());
        binding.timer.setInitPosition(exercise.getExTime());
        binding.timer.start();
        binding.exerciseAnimationView.setAnimation(exercise.getExImage());
        binding.exerciseAnimationView.playAnimation();
    }

    private void showDialogDoneExerciseLayout() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.done_exercises, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(0));

        DoneExercisesBinding doneExercisesBinding = DoneExercisesBinding.bind(dialogView);
        doneExercisesBinding.btnDone.setOnClickListener(v -> {
            saveExerciseCompletionToDatabase();
            alertDialog.dismiss();
            navigateToMainActivity();
        });
    }

    private void saveExerciseCompletionToDatabase() {
        refCalendar.child(firebaseUser.getUid()).child("Calender").child(getCurrentDate()).setValue(getCurrentDate());
    }

    private void navigateToMainActivity() {
        startActivity(new Intent(ExerciseActivity.this, MainActivity.class));
        finishAffinity();
    }

    @SuppressLint("SimpleDateFormat")
    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(currentDate);
    }

    @Override
    public boolean onOptionsItemSelected(@Nonnull MenuItem item) {
        // Handle toolbar item clicks here
        if (item.getItemId() == android.R.id.home) {
            // Handle the back button click (navigate up or finish the activity)
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
        binding.timer.stop();
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}