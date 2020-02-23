package com.example.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> imgURLS;
    ArrayList<String> names;
    int rightAnswerPos;
    int idx = 0;
    int num;

    ImageView photo;
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public class DownloadWebContent extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                Bitmap res= BitmapFactory.decodeStream(in);
                return res;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void generateQuestion() {
        if(idx >= num) return;
        String url = imgURLS.get(idx);
        String name = names.get(idx);
        Random rdm = new Random();
        rightAnswerPos = rdm.nextInt(4);
        String[] options = new String[4];
        for(int i=0; i<4; i++) {
            if(i == rightAnswerPos) {
                options[rightAnswerPos] = name;
            } else {
                int pos = rdm.nextInt(num);
                while(pos == idx) {
                    pos = rdm.nextInt(num);
                }
                options[i] = names.get(pos);
            }
        }
        idx++;
        button0.setText(options[0]);
        button1.setText(options[1]);
        button2.setText(options[2]);
        button3.setText(options[3]);

        ImageDownloader imageDownloader = new ImageDownloader();
        try {
            Bitmap image = imageDownloader.execute(url).get();
            photo.setImageBitmap(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void tapButton(View view) {
        if(view.getTag().toString().equals(Integer.toString(rightAnswerPos))) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_LONG).show();
        }
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        photo = findViewById(R.id.photo);

        imgURLS = new ArrayList<>();
        names = new ArrayList<>();

        DownloadWebContent task = new DownloadWebContent();
        String result = null;
        try {
            result = task.execute("http://www.posh24.se/kandisar").get();
            String[] splitResult = result.split("<div class=\"sidebarInnerContainer\">");

            // find all image urls
            Pattern p = Pattern.compile("<img src=\"(.*?)\"");
            Matcher m = p.matcher(splitResult[0]);
            while(m.find()) {
                imgURLS.add(m.group(1));
            }

            // find all celebrities' names
            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResult[0]);
            while(m.find()) {
                names.add(m.group(1));
            }

            num = imgURLS.size();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        generateQuestion();
    }
}