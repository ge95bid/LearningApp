package com.example.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        RelativeLayout relativeLayout = findViewById(R.id.splash);

        // Starten Sie eine neue Activity nach einer Verzögerung von 1 Sekunde
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                Animation animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.fade_in);

                startActivity(intent);


            }
        }, 4000);

        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    }

