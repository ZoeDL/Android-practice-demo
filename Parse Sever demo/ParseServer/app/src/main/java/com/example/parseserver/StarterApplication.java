/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.example.parseserver;

import android.app.Application;
import android.util.Log;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("4dc14be04cd16942fdda3cc074c11d02eef500d1")
            .clientKey("d5ee1d253722b75c7635cfccc9f9ad5d2a02617f")
            .server("http://18.217.23.166:80/parse/")
            .build()
    );

    // save object
    ParseObject twitter = new ParseObject("Twitter");
    twitter.put("username", "Zoe");
    twitter.put("tweet", "I want to go outside of Wuhan, please!");
    twitter.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if(e == null) {
          Log.i("twitter", "succeed!");
        } else {
          Log.i("twitter", "failed");
        }
      }
    });

    // retrieve object
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Twitter");
    query.getInBackground("yqKuR4nmbn", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if(e == null) {
          // update and retrieve
          object.put("tweet", "I want to come back to the U.S ASAP");
          object.saveInBackground();
          Log.i("object username", object.getString("username"));
          Log.i("object tweet", object.getString("tweet"));
        } else {
          Log.i("retrieve failed", e.toString());
        }
      }
    });


//    ParseUser.enableAutomaticUser();
//
//    ParseACL defaultACL = new ParseACL();
//    defaultACL.setPublicReadAccess(true);
//    defaultACL.setPublicWriteAccess(true);
//    ParseACL.setDefaultACL(defaultACL, true);

  }
}
