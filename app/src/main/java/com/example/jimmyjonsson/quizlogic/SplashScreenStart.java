package com.example.jimmyjonsson.quizlogic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenStart extends AppCompatActivity {

    private ImageView imageViewSplash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_start);

        imageViewSplash = (ImageView) findViewById(R.id.imageViewSplash);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.transition);
        imageViewSplash.startAnimation(animation);


        Thread timer = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(3000);
                    Intent intent = new Intent(SplashScreenStart.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                    super.run();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        timer.start();
    }
}
