package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a DB
        SQLiteDatabase myDB = this.openOrCreateDatabase("Users", MODE_PRIVATE, null );

        // create a table in the database
        myDB.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

        // create a table with id
        myDB.execSQL("CREATE TABLE IF NOT EXISTS usersWithID (name VARCHAR, age INTEGER(3), id INTEGER PRIMARY KEY)");

        // insert items into the table
        myDB.execSQL("INSERT INTO usersWithID (name, age) VALUES ('Zoe', 24)");
        myDB.execSQL("INSERT INTO usersWithID (name, age) VALUES ('Steven', 31)");
        myDB.execSQL("INSERT INTO usersWithID (name, age) VALUES ('Bobby', 19)");

        // delete item in the table.
        // can not use LIMIT in delete and update
        myDB.execSQL("DELETE FROM newUsers");
        myDB.execSQL("DELETE FROM usersWithID WHERE id = 2");

        // update
        myDB.execSQL("UPDATE newUsers SET age = 3 WHERE name = 'Zoe'");

        // create a cursor
//        Cursor c = myDB.rawQuery("SELECT * FROM users WHERE name LIKE '%e%' LIMIT 1", null);
        Cursor c = myDB.rawQuery("SELECT * FROM usersWithID", null);

        // get the index of the columns
        int nameIdx = c.getColumnIndex("name");
        int ageIdx = c.getColumnIndex("age");
        int idIdx = c.getColumnIndex("id");

        c.moveToFirst();
        while(c != null) {
            Log.i("name", c.getString(nameIdx));
            Log.i("age", c.getInt(ageIdx) + "");
            Log.i("id", c.getInt(idIdx) + "");
            c.moveToNext();
        }
    }
}
