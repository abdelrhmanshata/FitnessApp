package com.example.fitnessapp.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.applandeo.materialcalendarview.EventDay;
import com.example.fitnessapp.R;
import com.example.fitnessapp.databinding.FragmentCalenderBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CalenderFragment extends Fragment {

    FragmentCalenderBinding binding;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refCalendar = database.getReference("Exercise_Calendar");

    List<EventDay> events;

    public CalenderFragment() {
        // Required empty public constructor
    }

    @Override
    @SuppressLint("ResourceAsColor")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCalenderBinding.inflate(inflater, container, false);

        // Create a list to store events
        events = new ArrayList<>();

        getCalenders();

        binding.calendarView.setOnCalendarDayClickListener(calendarDay -> {
            Calendar clickedDayCalendar = calendarDay.getCalendar();

            // Check if the clicked date is an event
            boolean isEvent = isDateEvent(clickedDayCalendar);
            // Perform your actions based on whether the clicked date is an event or not
            if (isEvent) {
                // The clicked date is an event
                Toast.makeText(getContext(), "Event Date clicked", Toast.LENGTH_SHORT).show();
            } else {
                // The clicked date is not an event
                Toast.makeText(getContext(), "Non-Event Date clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }


    void getCalenders() {
        refCalendar.child(firebaseUser.getUid())
                .child("Calender").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        events.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String date = snapshot.getValue(String.class);
                            if (date != null) {
                                EventDay event = new EventDay(convertDate(date), R.drawable.check);
                                events.add(event);
                            }
                        }
                        // Set the list of events to the MaterialCalendarView
                        binding.calendarView.setEvents(events);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }


    private boolean isDateEvent(Calendar clickedDate) {
        for (EventDay eventDate : events) {
            if (eventDate.getCalendar().get(Calendar.YEAR) == clickedDate.get(Calendar.YEAR) && eventDate.getCalendar().get(Calendar.MONTH) == clickedDate.get(Calendar.MONTH) && eventDate.getCalendar().get(Calendar.DAY_OF_MONTH) == clickedDate.get(Calendar.DAY_OF_MONTH)) {
                return true; // Date is an event
            }
        }
        return false; // Date is not an event
    }

    @SuppressLint("SimpleDateFormat")
    Calendar convertDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(dateString);
            if (date != null) {
                calendar.setTime(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}