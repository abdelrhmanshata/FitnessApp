package com.example.fitnessapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fitnessapp.Adapter.AdapterExercises;
import com.example.fitnessapp.Model.AbsExercises;
import com.example.fitnessapp.Model.LegExercises;
import com.example.fitnessapp.R;
import com.example.fitnessapp.databinding.ActivityWorkoutBinding;

import java.util.Objects;

import javax.annotation.Nonnull;

public class WorkoutActivity extends AppCompatActivity implements AdapterExercises.OnItemClickListener {

    ActivityWorkoutBinding binding;
    Toolbar toolbar;
    String Workout = "";

    AdapterExercises adapterExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Workout = getIntent().getStringExtra("WORKOUT");

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(Workout + " Workout ");
        setSupportActionBar(toolbar);
        // Enable the back button in the toolbar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if (Workout.equals("Abs")) {
            AbsExercises exercises = new AbsExercises();
            adapterExercises = new AdapterExercises(this, exercises.getExercises(), this);
            binding.exercisesRecyclerView.setAdapter(adapterExercises);
        } else {
            LegExercises exercises = new LegExercises();
            adapterExercises = new AdapterExercises(this, exercises.getExercises(), this);
            binding.exercisesRecyclerView.setAdapter(adapterExercises);
        }

        binding.goExercise.setOnClickListener(v -> {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra("WORKOUT", Workout);
            startActivity(intent);
        });
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
    public void onItem_Click(int position) {

    }
}