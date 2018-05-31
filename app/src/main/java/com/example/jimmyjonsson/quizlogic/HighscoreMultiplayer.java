package com.example.jimmyjonsson.quizlogic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.jimmyjonsson.quizlogic.GameOptionActivity.counter;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.userName;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.userNameID;

public class HighscoreMultiplayer extends AppCompatActivity {

    private TextView displayPlayerOne;
    private TextView displayPlayerTwo;
    DBHandler dbHandler;
    private int[] resultArray;
    private String defaultString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_multiplayer);


        displayPlayerOne = (TextView) findViewById(R.id.textPlayerOne);
        displayPlayerTwo = (TextView) findViewById(R.id.textPlayerTwo);
        Button button = (Button) findViewById(R.id.button12);
        defaultString = "No values found.";
        resultArray = new int[5];
        dbHandler = new DBHandler();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighscoreMultiplayer.this, GameOptionActivity.class);
                startActivity(intent);
            }
        });

        try {
            System.out.println("User ID passed to the function: " + userNameID);
            resultArray = getMatchInformation(userNameID);
        } catch (NullPointerException ex){
            System.out.println("Null pointer exception encountered.");
            ex.printStackTrace();
        }

        try {
            displayPlayerOne.setText("User: " + dbHandler.getOpponentName(resultArray[1]) + "\t Score: " + resultArray[3]);
            displayPlayerTwo.setText("User: " + dbHandler.getOpponentName(resultArray[2]) + "\t Score: " + resultArray[4]);
        } catch (NullPointerException ex){
            displayPlayerOne.setText(defaultString);
            displayPlayerTwo.setText(defaultString);
        }


        /*
        int highscore;
        // receive the score from last activity by Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        dbHandler.newHighScore(userNameID,score);
        int fjfj = dbHandler.getOpponentID(userName);
        String gg = dbHandler.getOpponentName(fjfj);

        displayScore.setText("1" + userNameID + score);

        displayHighscore.setText("2" + gg);



        dbHandler = new DBHandler();
        userArrayList = dbHandler.getPLayer();
        highscore = dbHandler.getHighScore(userNameID);*/

        //if(score > highscore) {
            //dbHandler.newHighScore(userNameID,score);
           // displayHighscore.setText("High score: " + score);

        //} else {
        //    displayHighscore.setText("High score: " + highscore);
        //}


    }



    public void onClick(View view) {
        Intent intent = new Intent(HighscoreMultiplayer.this, MainActivity.class);
        startActivity(intent);
    }


    public int[] getMatchInformation(int userID){
        int[] returnArray = {1, 1, 1, 1, 1};
        returnArray = dbHandler.returnLatestMultiPlayerMatch(userID);
        System.out.println(returnArray[0]);
        System.out.println(returnArray[1]);
        System.out.println(returnArray[2]);
        System.out.println(returnArray[3]);
        System.out.println(returnArray[4]);
        return returnArray;
    }




}

/*
private TextView playerOneName;
    private TextView playerTwoName;
    private TextView playerOneScore;
    private TextView playerTwoScore;
    private Button backButton;
    private String defaultString;

playerOneName = (TextView) findViewById(R.id.playerOneName);
        playerTwoName = (TextView) findViewById(R.id.playerTwoName);
        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        backButton = (Button) findViewById(R.id.backButton);
        defaultString = "No value found.";

        try {

            resultArray = getMatchInformation();

            playerOneName.setText(resultArray[1]);
            playerTwoName.setText(resultArray[2]);
            playerOneScore.setText(resultArray[3]);
            playerTwoScore.setText(resultArray[4]);

        } catch (NullPointerException ex){
            playerOneName.setText(defaultString);
            playerTwoName.setText(defaultString);
            playerOneScore.setText(defaultString);
            playerTwoScore.setText(defaultString);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighscoreMultiplayer.this, GameOptionActivity.class);
            }
        });*/

