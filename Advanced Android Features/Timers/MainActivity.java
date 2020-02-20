package com.example.timerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a handler to execute a chunk of code every certain time
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable runs!", "One second passed!");
                // run every second
                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000, 1000) {
            public void onTick(long millionSecondsUntilDone) {
                Log.i("seconds left", String.valueOf(millionSecondsUntilDone/1000));
            }

            public void onFinish() {
                Log.i("finished", "countdown finished!");
            }
        }.start();
    }

}
