package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    ArrayList<String> users;
    ListView userList;
    ArrayAdapter<String> arrayAdapter;
    ParseUser activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("User List");
        activeUser = ParseUser.getCurrentUser();

        if (activeUser.getList("isFollowing") == null) {
            ArrayList<String> followingUsers = new ArrayList<>();
            activeUser.put("isFollowing", followingUsers);
        }

        users = new ArrayList<String>();
        userList = findViewById(R.id.userList);
        userList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_checked, users);
        userList.setAdapter(arrayAdapter);

        // query all the users in the database
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", activeUser.getString("username"));
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e == null && objects.size() > 0) {
                    for(ParseUser user : objects) {
                        users.add(user.getUsername());
                    }
                    arrayAdapter.notifyDataSetChanged();

                    // make all the followers of the active user checked
                    ArrayList<String> followers = (ArrayList) activeUser.getList("isFollowing");
                    for(int i=0; i<users.size(); i++) {
                        if(followers.contains(users.get(i))) {
                            userList.setItemChecked(i, true);
                        }
                    }
                }
            }
        });

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView checkedTextView = (CheckedTextView) view;
                if(checkedTextView.isChecked()) {
                    activeUser.addUnique("isFollowing", users.get(position));
                    activeUser.saveInBackground();
                } else {
                    ArrayList<String> followingUsers = (ArrayList)activeUser.getList("isFollowing");
                    // update arraylist
                    followingUsers.remove(users.get(position));
                    activeUser.put("isFollowing", followingUsers);
                    activeUser.saveInBackground();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            Intent myIntent = new Intent(getApplicationContext(), UserFeedActivity.class);
            startActivity(myIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
