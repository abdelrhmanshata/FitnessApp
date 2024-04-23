package com.example.fitnessapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.Model.Exercises;
import com.example.fitnessapp.databinding.ItemExerciseBinding;

import java.util.List;


public class AdapterExercises extends RecyclerView.Adapter<AdapterExercises.ViewHolder> {
    Context context;
    List<Exercises> mExercises;
    private OnItemClickListener mListener;

    public AdapterExercises(Context context, List<Exercises> exercises, OnItemClickListener mListener) {
        this.context = context;
        this.mExercises = exercises;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemExerciseBinding binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercises exercise = mExercises.get(position);
        holder.binding.exerciseName.setText(exercise.getExName());
        holder.binding.exerciseTime.setText(exercise.getExTime() + " s");
    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItem_Click(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemExerciseBinding binding;

        public ViewHolder(ItemExerciseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItem_Click(position);
                }
            }
        }
    }
}
