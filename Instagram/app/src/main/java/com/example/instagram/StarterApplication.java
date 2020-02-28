package com.example.instagram;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;

public class StarterApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add initialization code to connect to the Parse Server
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("4dc14be04cd16942fdda3cc074c11d02eef500d1")
                .clientKey("d5ee1d253722b75c7635cfccc9f9ad5d2a02617f")
                .server("http://18.217.23.166:80/parse/")
                .build()
        );

    }
}
