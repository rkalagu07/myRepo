package com.arasan.master;

import android.app.Application;

import com.facebook.stetho.Stetho;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
   //     PreferenceManager.getPrefInstance(getApplicationContext());
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(getApplicationContext());
        }
    }
}
