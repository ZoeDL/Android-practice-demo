package com.example.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* move the image out of the screen when app starts */
        ImageView kiss = findViewById(R.id.kiss);
        kiss.setTranslationX(-1500f);
    }

    public void fade(View view) {
        ImageView zhuzhu = findViewById(R.id.zhuzhu);
        ImageView kiss = findViewById(R.id.kiss);
        zhuzhu.animate().alpha(0f).setDuration(2000);
        kiss.animate().alpha((1f)).setDuration(2000);
    }

    public void translate(View view) {
        ImageView zhuzhu = findViewById(R.id.zhuzhu);
        ImageView kiss = findViewById(R.id.kiss);
        /*move it down by 1000dp */
         zhuzhu.animate().translationYBy(1000f).setDuration(2000);
        /* move it to the left */
         zhuzhu.animate().translationXBy(-1000f).setDuration(2000);
        /* move zhuzhu to right and move kiss to the screen */
        zhuzhu.animate().translationXBy(1000f).setDuration(1000);
        kiss.animate().translationXBy(1500f).setDuration(1000);

    }

    /* rotate while scaling */
    public void rotate(View view) {
        ImageView kiss = findViewById(R.id.kiss);
        kiss.animate().rotation(1800f).scaleX(0.1f).scaleY(0.1f).setDuration(3000);
    }

    public void zoom(View view) {
        ImageView zhuzhu = findViewById(R.id.zhuzhu);
        zhuzhu.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);
    }
}
