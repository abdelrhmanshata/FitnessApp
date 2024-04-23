package com.example.fitnessapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.fitnessapp.Activity.WorkoutActivity;
import com.example.fitnessapp.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.absCardView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), WorkoutActivity.class);
            intent.putExtra("WORKOUT", "Abs");
            startActivity(intent);
        });

        binding.legCardView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), WorkoutActivity.class);
            intent.putExtra("WORKOUT", "Leg");
            startActivity(intent);
        });

        return binding.getRoot();
    }
}