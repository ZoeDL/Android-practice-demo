package com.example.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public class Number {
        int num;
        public boolean isSquare() {
            double squareFoot = Math.sqrt(num);
            return Math.floor(squareFoot) == squareFoot? true : false;
        }

        public boolean isTriangular() {
            int acc = 1;
            int x = 1;
            while(x < num) {
                acc++;
                x += acc;
            }
            return x == num? true : false;
        }

        public String testType() {
            String msg = num + "";
            if(isTriangular() && isSquare()) {
                msg = "is both square & triangular";
            } else if(!isSquare() && !isTriangular()) {
                msg = "is neither";
            } else if(isSquare()) {
                msg = "is square number";
            } else {
                msg = "is triangular number";
            }
            return msg;
        }
    }

    public void test(View view) {
        EditText input = findViewById(R.id.inputNumber);
        Number inputNum = new Number();
        inputNum.num = Integer.parseInt(input.getText().toString());
        Toast.makeText(getApplicationContext(), inputNum.testType(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
