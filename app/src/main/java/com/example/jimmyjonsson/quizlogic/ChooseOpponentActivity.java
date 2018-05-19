package com.example.jimmyjonsson.quizlogic;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.jimmyjonsson.quizlogic.LoginActivity.dbHandler;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.continueButtonSaveHolder;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.userNameID;

public class ChooseOpponentActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<User> userList;
    private DBHandler dbHandler;
    private ArrayList<String> userNameList;
    private String spinnerSelection;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_opponent);

        dbHandler = new DBHandler();
        spinner = (Spinner) findViewById(R.id.opponentsSpinner);
        Button challengeButton = (Button) findViewById(R.id.challengeButton);
        Button backButton = (Button) findViewById(R.id.backButton);
        TextView description = (TextView) findViewById(R.id.chooseOpponentTextView);

        spinnerSelection = "";
        String s = "Select opponent: ";
        description.setText(s);
        userNameList = new ArrayList<>();
        userList = new ArrayList<>();
        userList = dbHandler.getPLayer();

        for (User user: userList){
            userNameList.add(user.getUsername());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userNameList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerSelection = spinner.getSelectedItem().toString();
                System.out.println(spinnerSelection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerSelection = "";
                System.out.println(spinnerSelection);
            }
        });


        challengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinnerSelection.equals("")){

                } else {
                    int playerOneID = userNameID;
                    String playerTwoName = spinnerSelection;
                    int playerTwoID = dbHandler.getIDofUserName(spinnerSelection);
                    System.out.println(playerTwoID);

                     boolean confirmInvite =  dbHandler.checkInvite(playerTwoID);
                    System.out.println(confirmInvite);

                     if(!confirmInvite) {


                         System.out.println("Inside if statement");
                         dbHandler.setInviteToTrue(playerTwoID);
                         dbHandler.createMultiplayerTable(playerOneID, playerTwoName, 0,0);


                         String toastText = "Invitation sent to " + spinnerSelection + "!";
                         Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_LONG).show();
                         Intent intent = new Intent(ChooseOpponentActivity.this, GameOptionActivity.class);
                         startActivity(intent);
                     } else {
                         String toastText = "This user is already playing!";
                         Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_LONG).show();
                         Intent intent = new Intent(ChooseOpponentActivity.this, GameOptionActivity.class);
                         startActivity(intent);
                     }
                }
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseOpponentActivity.this, GameOptionActivity.class);
                startActivity(intent);
            }
        });

    }
}
