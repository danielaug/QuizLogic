package com.example.jimmyjonsson.quizlogic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.jimmyjonsson.quizlogic.GameOptionActivity.counter;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.userNameID;

public class HighscoreMultiplayer extends AppCompatActivity {


    private TextView displayScore;
    private TextView displayHighscore;
    ArrayList<User> userArrayList;
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_multiplayer);



        displayScore = (TextView) findViewById(R.id.textScore);
        displayHighscore = (TextView) findViewById(R.id.textHighScore);

        Button button = (Button) findViewById(R.id.button12);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                dbHandler.updateSaveTable(0,0,0,userNameID);
                Intent intent = new Intent(HighscoreMultiplayer.this, GameOptionActivity.class);
                startActivity(intent);


            }
        });
        int highscore;
        // receive the score from last activity by Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        displayScore.setText("Your score: " + score);


        dbHandler = new DBHandler();
        userArrayList = dbHandler.getPLayer();
        highscore = dbHandler.getHighScore(userNameID);

        if(score > highscore) {
            dbHandler.newHighScore(userNameID,score);
            displayHighscore.setText("High score: " + score);

        } else {
            displayHighscore.setText("High score: " + highscore);
        }


    }



    public void onClick(View view) {
        Intent intent = new Intent(HighscoreMultiplayer.this, MainActivity.class);
        startActivity(intent);
    }




}

