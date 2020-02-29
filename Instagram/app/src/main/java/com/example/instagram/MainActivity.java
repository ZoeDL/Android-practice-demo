package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {
    Button button;
    TextView passwordView;

    // Add keyboard listener to "ENTER"
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            tapButton(v);
        }
        return false;
    }

    public void viewUserList() {
        Intent intent = new Intent(this, UserListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Instagram");

        // ParseUser.logOut();

        if(ParseUser.getCurrentUser() != null) {
            viewUserList();
        }

        ConstraintLayout background = findViewById(R.id.background);
        ImageView logo = findViewById(R.id.logo);
        button = findViewById(R.id.loginButton);
        passwordView = findViewById(R.id.password);

        passwordView.setOnKeyListener(this);
        background.setOnClickListener(this);
        logo.setOnClickListener(this);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
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
                         viewUserList();
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
                        viewUserList();
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


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.background || v.getId() == R.id.logo) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
