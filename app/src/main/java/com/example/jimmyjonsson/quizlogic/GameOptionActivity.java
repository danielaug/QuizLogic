package com.example.jimmyjonsson.quizlogic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class GameOptionActivity extends AppCompatActivity {

    public static int counter;
    public static int currentScoreCounter;
    public static int countDownValueSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_option);
        setValue();

        final String[] menuItems = {"Continue", "New Game", "Highscore", "Quit"};

        ListView listView = (ListView) findViewById(R.id.listviewGameOption);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(GameOptionActivity.this, android.R.layout.simple_list_item_1, menuItems);

        listView.setAdapter(listViewAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {

                    Log.e("asdas", String.valueOf(counter));
                    if(counter <= 0 || counter > 9) {
                        Toast.makeText(GameOptionActivity.this, "Nothing here", Toast.LENGTH_SHORT).show();
                    }
                 else  {
                        Intent intent = new Intent(GameOptionActivity.this, MainActivity.class);
                        startActivity(intent);
                    }


                    }
                if (position == 1) {
                    counter = 0;
                    countDownValueSaver = 100;
                    currentScoreCounter = 0;

                    Intent intent = new Intent(GameOptionActivity.this, MainActivity.class);
                    startActivity(intent);


                }
                if(position == 2) {
                    Intent intent = new Intent(GameOptionActivity.this, HighscoreActivity.class);
                    startActivity(intent);
                }

                if (position == 3){
                    SharedPreferences sharedPreferences1 = getSharedPreferences("pref", MODE_PRIVATE);        //initialize the sharedpreference by specifying file name and MODE PRIVATE, means it can only be used by this app.
                    SharedPreferences.Editor editor = sharedPreferences1.edit();   //make it possible to edit.
                    editor.putInt("points", counter);       //save how many points  user quit with
                    editor.commit();

                    Intent intent = new Intent(GameOptionActivity.this, LoginActivity.class);
                    startActivity(intent);



                }


                }

            }
        );


    }
    public void setValue() {
        SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        //click first button, go to a new fragment

        this.counter = sharedPreferences.getInt("points", 0);
        this.currentScoreCounter = sharedPreferences.getInt("score", 0);
        this.countDownValueSaver = sharedPreferences.getInt("countDownValue", 0);
    }
}

