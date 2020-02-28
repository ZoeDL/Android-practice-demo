package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener {
    Button button;
    TextView passwordView;

    // Add keyboard listener to "ENTER"
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER) {
            tapButton(v);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.loginButton);
        passwordView = findViewById(R.id.password);

        passwordView.setOnKeyListener(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add initialization code to connect to the Parse Server
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("4dc14be04cd16942fdda3cc074c11d02eef500d1")
                .clientKey("d5ee1d253722b75c7635cfccc9f9ad5d2a02617f")
                .server("http://18.217.23.166:80/parse/")
                .build()
        );
    }


    public void tapButton(View view) {
        String mode = button.getText().toString();
        TextView usernameView = findViewById(R.id.username);

        String username = usernameView.getText().toString().trim();
        String password = passwordView.getText().toString().trim();
        if(mode.equals("sign up")) {
            // do some checking
            if(username.length() == 0 || password.length() < 6) {
                makeText(this, "username/password invalid!", LENGTH_LONG).show();
                return;
            }

            // sign up
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);
            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        Log.i("sign up", "Succeed!");
                    } else {
                        Toast.makeText(getApplicationContext(), e.toString().split(":")[1], Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else { // log in
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(user != null) {
                        Toast.makeText(getApplicationContext(), "Log in success!!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), e.toString().split(":")[1], Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void switchText(View view) {
        TextView textView = findViewById(R.id.text);
        String mode = textView.getText().toString().split("or ", 2)[1];
        if(mode.equals("Login")) {
            button.setText("login");
            textView.setText("or Sign Up");
        } else {
            button.setText("sign up");
            textView.setText("or Login");
        }
    }


}
