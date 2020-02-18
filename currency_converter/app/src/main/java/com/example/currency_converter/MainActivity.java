package com.example.currency_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert(View view) {
        EditText num = findViewById(R.id.dollars);
        Double pond = 0.77 * Double.valueOf(num.getText().toString());
        Toast.makeText(MainActivity.this,String.format("%.2f", pond), Toast.LENGTH_LONG).show();
    }
}
