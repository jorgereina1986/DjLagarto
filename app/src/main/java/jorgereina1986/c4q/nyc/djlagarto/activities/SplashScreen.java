package jorgereina1986.c4q.nyc.djlagarto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import jorgereina1986.c4q.nyc.djlagarto.R;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent intent = new Intent(SplashScreen.this, Main2Activity.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH_TIME_OUT);

    }
}
