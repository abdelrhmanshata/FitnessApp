package com.example.fitnessapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.fitnessapp.Fragment.CalenderFragment;
import com.example.fitnessapp.Fragment.HomeFragment;
import com.example.fitnessapp.Fragment.ProfileFragment;
import com.example.fitnessapp.R;
import com.example.fitnessapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    HomeFragment homeFragment = new HomeFragment();
    CalenderFragment calenderFragment = new CalenderFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getHomePage();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation item clicks here.
        if (item.getItemId() == R.id.nav_home) {
            getHomePage();
        } else if (item.getItemId() == R.id.nav_calender) {
            getCalenderPage();
        } else if (item.getItemId() == R.id.nav_profile) {
            getProfilePage();
        } else if (item.getItemId() == R.id.nav_logout) {
            auth.signOut();
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finishAffinity();
        } else {
            getHomePage();
        }
        drawerLayout.closeDrawer(navigationView);
        return true;
    }

    void getHomePage() {
        toolbar.setTitle(getString(R.string.home));
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
    }

    void getCalenderPage() {
        toolbar.setTitle(getString(R.string.calender));
        getSupportFragmentManager().beginTransaction().replace(R.id.container, calenderFragment).commit();
    }

    void getProfilePage() {
        toolbar.setTitle(getString(R.string.profile));
        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
    }
}