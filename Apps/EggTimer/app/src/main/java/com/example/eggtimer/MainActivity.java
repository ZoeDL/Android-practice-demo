package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CountDownTimer timer;
    boolean countingDown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timerSeekBar = findViewById(R.id.timerSeekBar);
        final TextView minDisplay = findViewById(R.id.minDisplay);
        final TextView secDisplay = findViewById(R.id.secDisplay);
        final Button button = findViewById(R.id.button);

        timerSeekBar.setProgress(60);
        timerSeekBar.setMax(120);


        timer = new CountDownTimer(60000, 1000) {
            int remainingSecs = 60;
            public void onTick(long millionSecondsUntilDone) {
                timerSeekBar.setProgress(remainingSecs);
                minDisplay.setText(String.format("%02d", remainingSecs/60));
                secDisplay.setText(String.format("%02d", remainingSecs%60));
                remainingSecs -= 1;
            }

            public void onFinish() {
                minDisplay.setText("00");
                secDisplay.setText("00");
            }
        };

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {
                if(!fromUser) return;
                button.setText("Go");
                timer.cancel();
                countingDown = false;
                minDisplay.setText(String.format("%02d", progress/60));
                secDisplay.setText(String.format("%02d", progress%60));

                 //reset the timer
                timer = new CountDownTimer(progress * 1000, 1000) {
                    int remainningSecs = progress;
                    public void onTick(long millionSecondsUntilDone) {
                        timerSeekBar.setProgress(remainningSecs);
                        minDisplay.setText(String.format("%02d", remainningSecs/60));
                        secDisplay.setText(String.format("%02d", remainningSecs%60));
                        remainningSecs -= 1;
                    }

                    public void onFinish() {
                        minDisplay.setText("00");
                        secDisplay.setText("00");
                    }
                };
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onClick(View view) {
        Button button = findViewById(R.id.button);
        countingDown = !countingDown;
        if(countingDown) {
            timer.start();
            button.setText("Pause");

        } else {
            timer.cancel();
            button.setText("Go");
        }

    }
}
