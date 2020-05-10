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

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;


public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("XXXXXXXXXXX")
                .clientKey("XXXXXXXXXXX")
                .server("XXXXXXXXXXXXXXXXX")
                .build()
        );

        // save object
//        ParseObject twitter = new ParseObject("Twitter");
//        twitter.put("username", "Zoe");
//        twitter.put("tweet", "Youhe bread is so yummy!");
//        twitter.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e == null) {
//                    Log.i("twitter", "succeed!");
//                } else {
//                    Log.i("twitter", "failed");
//                }
//            }
//        });

        // retrieve object
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Twitter");
//        query.getInBackground("yqKuR4nmbn", new GetCallback<ParseObject>() {
//            @Override
//            public void done(ParseObject object, ParseException e) {
//                if(e == null) {
//                    // update and retrieve
//                    object.put("tweet", "I want to come back to the U.S ASAP");
//                    object.saveInBackground();
//                    Log.i("object username", object.getString("username"));
//                    Log.i("object tweet", object.getString("tweet"));
//                } else {
//                    Log.i("retrieve failed", e.toString());
//                }
//            }
//        });

        /* advance query */
        // loop through the objects in Class Twitter
        ParseQuery<ParseObject>  query = ParseQuery.getQuery("Twitter");
        // set some constrains to the query
        query.whereEqualTo("username", "Blama");
        query.setLimit(1);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for(ParseObject object : objects) {
                    if(e == null) {
                        Log.i("objects in Twitter",  object.getString("tweet"));
                    }
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
