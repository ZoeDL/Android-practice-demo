package com.example.gridlayoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* how to determine which button is tapped
    * get the id of the tapped button, transform the system id to our self-defined id
    * find the corresponding resources regarding the self-defined ids given that the
    *  resource names is equal to our self-defined ids
    * */
    public void buttonTapped(View view) {
        int id = view.getId();
        String ourId = view.getResources().getResourceEntryName(id);
        int resourceID = getResources().getIdentifier(ourId, "raw", "com.example.gridlayoutdemo");
//        switch (ourId) {
//            case "firework": {
//                mediaPlayer = MediaPlayer.create(this, R.raw.fireworks_screamers);
//                break;
//            }
//            case "bird": {
//                mediaPlayer = MediaPlayer.create(this, R.raw.bird_cockatoo_black_squawk_slight_distance_001_41720);
//                break;
//            }
//            default: {
//                mediaPlayer = MediaPlayer.create(this, R.raw.yfs_la_paz_barking_dogs_in_backyard_074);
//            }
//        }
        MediaPlayer mediaPlayer = MediaPlayer.create(this, resourceID);
        mediaPlayer.start();
    }
}
