package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = findViewById(R.id.myListView);
        final ArrayList<String> names = new ArrayList<String>();
        names.add("Zoe");
        names.add("Steven");
        names.add("Tommy");
        names.add("Ralph");
        names.add("Justin");


        // array adapter - convert arraylist to simple_list_item1 format
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        myListView.setAdapter(arrayAdapter);

        // make the list item clickable
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Hello, " + names.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
