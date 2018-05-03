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

public class HighscoreActivity extends AppCompatActivity {

    private TextView displayScore;
    private TextView displayHighscore;
    ArrayList<User> userArrayList;
    DBHelper dbHelper;
    SharedPreferences sharedPreferences;
    String currentUser;

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
                updateSharedPref();
                Intent intent = new Intent(HighscoreActivity.this, GameOptionActivity.class);
                startActivity(intent);


            }
        });

        // receive the score from last activity by Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        displayScore.setText("Your score: " + score);


        SharedPreferences myPref = getPreferences(MODE_PRIVATE);
        int highscore;
        sharedPreferences = getSharedPreferences("pref",MODE_PRIVATE);
        currentUser = sharedPreferences.getString("userName",null);
        dbHelper = new DBHelper(this);
        userArrayList = dbHelper.getUsers();
        Log.e("asdasuser",currentUser);

        for (int i = 0; i<userArrayList.size();i++){

            if (currentUser.equals(userArrayList.get(i).getUsername())){
                highscore = userArrayList.get(i).getHighScore();
                displayHighscore.setText("High score: " + highscore);
                Log.e("test","hittat HS" + highscore );
            }
        }

    }

    public void onClick(View view) {
        Intent intent = new Intent(HighscoreActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void updateSharedPref(){
        SharedPreferences sharedPreferences1 = getSharedPreferences("pref", MODE_PRIVATE);        //initialize the sharedpreference by specifying file name and MODE PRIVATE, means it can only be used by this app.
        SharedPreferences.Editor editor = sharedPreferences1.edit();   //make it possible to edit.

        editor.putInt("points", counter);       //save how many points  user quit with
        editor.commit();
}
}



