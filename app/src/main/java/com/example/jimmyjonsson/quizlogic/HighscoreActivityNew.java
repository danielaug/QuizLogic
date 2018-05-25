package com.example.jimmyjonsson.quizlogic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.jimmyjonsson.quizlogic.LoginActivity.userNameID;

public class HighscoreActivityNew extends AppCompatActivity {

    private TextView textView2;
    private Button backButton;
    ArrayList<User> userArrayList;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_new);


        textView2 = (TextView) findViewById(R.id.textView2);



        backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighscoreActivityNew.this, GameOptionActivity.class);
                startActivity(intent);
            }
        });

        int highscore = 0;


        dbHandler = new DBHandler();
        userArrayList = dbHandler.getPLayer();
        highscore = dbHandler.getHighScore(userNameID);

        textView2.setText(highscore);


    }
}
