package com.example.imagesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeImage(View view) {
        ImageView btn = findViewById(R.id.zhuzhu);
        if(flag == 0) {
            btn.setImageResource(R.drawable.kiss);
            flag = 1;
        } else {
            btn.setImageResource(R.drawable.zhuzhu);
            flag = 0;
        }
    }
}
