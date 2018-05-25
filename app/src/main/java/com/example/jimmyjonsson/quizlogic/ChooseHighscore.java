package com.example.jimmyjonsson.quizlogic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class ChooseHighscore extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosehighscore);


        Button button1 = (Button) findViewById(R.id.singleplayer);

        Button button2 = (Button) findViewById(R.id.multiplayer);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseHighscore.this, HighscoreActivityNew.class);
                startActivity(intent);
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseHighscore.this, HighscoreMultiplayer.class);
                startActivity(intent);
            }
        });







    }
}





