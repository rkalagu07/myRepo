package com.arasan.master;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.AppBarLayout;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    AppBarLayout mAppbarLayout;
    private NavController mNavController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppbarLayout = findViewById(R.id.appbarlayout);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Ray Service");
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NavHostFragment aNavHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        mNavController = aNavHostFragment.getNavController();
        NavigationUI.setupWithNavController(mToolbar, mNavController);
        mNavController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            int aId = navDestination.getId();
            if (aId == R.id.otpFragment) {
                UpdateToolbar(false);
            }
            else if (aId == R.id.homeFragment || aId ==  R.id.settingsFragment) {
                UpdateToolbar(true);
            }
        });
    }

    private void UpdateToolbar(boolean isToolbar) {
        if (!isToolbar) {
            mToolbar.setVisibility(View.GONE);
            mAppbarLayout.setVisibility(View.GONE);
        } else {
            mToolbar.setVisibility(View.VISIBLE);
            mAppbarLayout.setVisibility(View.VISIBLE);

        }
    }
}