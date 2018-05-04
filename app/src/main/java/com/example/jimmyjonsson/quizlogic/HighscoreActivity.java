package com.example.jimmyjonsson.quizlogic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.jimmyjonsson.quizlogic.GameOptionActivity.counter;
import static com.example.jimmyjonsson.quizlogic.GameOptionActivity.currentScoreCounter;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.userName;

public class HighscoreActivity extends AppCompatActivity {

    private TextView displayScore;
    private TextView displayHighscore;
    ArrayList<User> userArrayList;
    DBHelper dbHelper;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);


        displayScore = (TextView) findViewById(R.id.textScore);
        displayHighscore = (TextView) findViewById(R.id.textHighScore);

        Button button = (Button) findViewById(R.id.button12);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                dbHelper.insertValuesIntoSaveTable(0,0,0,userName);
                Intent intent = new Intent(HighscoreActivity.this, GameOptionActivity.class);
                startActivity(intent);


            }
        });
        int highscore;
        // receive the score from last activity by Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        displayScore.setText("Your score: " + score);



        highscore = dbHelper.getHighscore(userName);
        dbHelper = new DBHelper(this);
        userArrayList = dbHelper.getUsers();

        if(score > highscore) {
            dbHelper.newHighScore(userName,score);
            displayHighscore.setText("High score: " + score);

        } else {
            displayHighscore.setText("High score: " + highscore);
        }


    }



    public void onClick(View view) {
        Intent intent = new Intent(HighscoreActivity.this, MainActivity.class);
        startActivity(intent);
    }

}



