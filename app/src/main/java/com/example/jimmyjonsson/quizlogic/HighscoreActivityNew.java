package com.example.jimmyjonsson.quizlogic;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.jimmyjonsson.quizlogic.LoginActivity.userNameID;

public class HighscoreActivityNew extends AppCompatActivity {

    private TextView textView4;
    private Button backButton;
    ArrayList<User> userArrayList;

    DBHandler dbHandler;
    private ArrayList<String> userNames;
    private ArrayList<Integer> highscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_new);


        textView4 = (TextView) findViewById(R.id.textView4);

        backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighscoreActivityNew.this, GameOptionActivity.class);
                startActivity(intent);
            }
        });




        dbHandler = new DBHandler();

        userNames = new ArrayList<>();
        userArrayList = new ArrayList<>();
        highscore = new ArrayList<>();
        userArrayList = dbHandler.getPLayer();




        for (User user: userArrayList){
            userNames.add(user.getUsername());

        }

        for (User user: userArrayList){
            highscore.add(user.getHighScore());
        }

        String text="";
        for (String details : userNames) {
            text = text + details + "\n";
        }

        String text2="";
        for (Integer details : highscore) {
            text2 = text2 + details + "\n";
        }



        textView4.setText("---USERNAME---" + "\n" + text + "---HIGHSCORE---" + "\n" + text2);


    }
}
