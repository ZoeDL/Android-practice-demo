package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout playingPage;
    Button startButton;
    Button playAgainButton;
    TextView opt1;
    TextView opt2;
    TextView opt3;
    TextView opt4;
    TextView timerDisplay;
    TextView sumDisplay;
    TextView score;
    TextView msg;

    int totalQuestions;
    int rightAns;
    int ansPos;
    boolean finished;

    public void startGame(View view) {
        startButton.setVisibility(View.GONE);
        playingPage.setVisibility(View.VISIBLE);
        reset(findViewById(R.id.playAgain));
    }

    public void generateQuestions() {
        int[] opts = new int[4];
        Random rdm = new Random();
        int a = rdm.nextInt(30);
        int b = rdm.nextInt(30);
        ansPos = rdm.nextInt(4);
        sumDisplay.setText(a + " + " + b);

        for(int i=0; i<4; i++) {
            if(i == ansPos) {
                opts[i] = a + b;
            } else {
                int c = rdm.nextInt(60);
                while(c == a + b) {
                    c = rdm.nextInt(60);
                }
                opts[i] = c;
            }
        }

        opt1.setText(Integer.toString(opts[0]));
        opt2.setText(Integer.toString(opts[1]));
        opt3.setText(Integer.toString(opts[2]));
        opt4.setText(Integer.toString(opts[3]));
    }

    public void chooseAnswer(View view) {
        if(finished) {
            return;
        }
        msg.setVisibility(View.VISIBLE);
        totalQuestions++;
        // if the position matches, we hit the correct answer
        if(view.getTag().toString().equals(Integer.toString(ansPos))) {
            msg.setText("Correct!");
            rightAns++;
        } else {
            msg.setText("Wrong!");
        }
        score.setText(rightAns + "/" + totalQuestions);
        generateQuestions();
    }

    // reset the game
    public void reset(View view) {
        totalQuestions = 0;
        rightAns = 0;
        finished = false;
        // restart the timer
        new CountDownTimer(30000, 1000) {
            int remainingSecs = 30;
            @Override
            public void onTick(long millisUntilFinished) {
                timerDisplay.setText(Integer.toString(remainingSecs));
                remainingSecs--;
            }

            @Override
            public void onFinish() {
                timerDisplay.setText("0");
                msg.setText("You score is " + score.getText());
                playAgainButton.setVisibility(View.VISIBLE);
                finished = true;
            }
        }.start();
        generateQuestions();
        score.setText("0/0");
        msg.setVisibility(View.GONE);
        playAgainButton.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playingPage = findViewById(R.id.playingPage);
        timerDisplay = findViewById(R.id.timerDisplay);
        playAgainButton = findViewById(R.id.playAgain);
        startButton = findViewById(R.id.playButton);
        sumDisplay = findViewById(R.id.calculation);
        score = findViewById(R.id.score);
        msg = findViewById(R.id.msg);
        opt1 = findViewById(R.id.r1c1);
        opt2 = findViewById(R.id.r1c2);
        opt3 = findViewById(R.id.r2c1);
        opt4 = findViewById(R.id.r2c2);

        playingPage.setVisibility(View.GONE);
    }
}
