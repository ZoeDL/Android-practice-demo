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
                .applicationId("XXXXXXXXXXXXXXX")
                .clientKey("XXXXXXXXXXXXXXXXXXX")
                .server("XXXXXXXXXXXXXXXXXXXX")
                .build()
        );

    }
}
