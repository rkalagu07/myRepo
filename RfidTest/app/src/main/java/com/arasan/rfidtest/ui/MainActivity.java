package com.arasan.rfidtest.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.arasan.rfidtest.R;
import com.arasan.rfidtest.data.viewmodel.TvShowViewModel;
import com.google.gson.Gson;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    TvShowViewModel mConnectionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mConnectionViewModel = new ViewModelProvider(this).get(TvShowViewModel.class);
        LoadApi();
    }

    private void LoadApi() {
        mConnectionViewModel.getPostsLiveData().observe(this, posts -> {
            Log.e("LoadApi: ", new Gson().toJson(posts));
        });
    }
}