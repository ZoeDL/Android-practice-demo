package com.example.timestablesapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public void genTimesTable(int curTime) {
        ListView listView = findViewById(R.id.timesTableListView);
        ArrayList<String> timesTableContent = new ArrayList<>();
        for(int i=1; i<10; i++) {
            timesTableContent.add(Integer.toString(i * curTime));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                timesTableContent);

        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genTimesTable(1);

        SeekBar seekBar = findViewById(R.id.timesTableSeekBar);
        seekBar.setMax(20);
        seekBar.setProgress(1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minVal = 1;
                int timesTable;
                if(progress < 1) {
                    timesTable = minVal;
                    seekBar.setProgress(minVal);
                } else {
                    timesTable = progress;
                }

                // update the number on list view
                genTimesTable(timesTable);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
