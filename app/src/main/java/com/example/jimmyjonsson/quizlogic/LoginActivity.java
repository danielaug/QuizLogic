package com.example.jimmyjonsson.quizlogic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    public static int userNameID;
    public ArrayList<User> userList;
    public static  DBHandler dbHandler;
    public static int [] continueButtonSaveHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userList = new ArrayList<>();
        dbHandler = new DBHandler();
        userList = dbHandler.getPLayer();


        Button button = (Button) findViewById(R.id.buttonloginMenu);
        Button registerButton = (Button) findViewById(R.id.registerButton);
        final EditText usernameField = findViewById(R.id.usernameField);
        final EditText passwordField = findViewById(R.id.passwordField);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, GameOptionActivity.class);
                Log.e("test","Knappen klcaskda");
                if (authentication(usernameField.getText().toString(),passwordField.getText().toString())){
                    userNameID = dbHandler.getIDofUserName(usernameField.getText().toString());
                    continueButtonSaveHolder = dbHandler.readFromSave(userNameID);
                    for(int i = 0; i < continueButtonSaveHolder.length; i++) {
                        Log.e("SICK MAYN", Integer.toString(continueButtonSaveHolder[i]));
                    }



                    startActivity(intent);


                }


            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"jimmyjnsson@gmail.com"});  //developer 's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "Hi!" + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Contact Developer"));
            }
        });


    }



    public boolean authentication (String username, String password){
        Log.e("test","Lösenord kollas");
        Log.e("Inskickat", username+ " " + password);
        for (int i = 0; i<userList.size();i++){
            Log.e("test",userList.get(i).getUsername());
            if(userList.get(i).getUsername().equals(username) && userList.get(i).getPassword().equals(password)){
                Log.e("test","Lösenord retuneras rätt");

                return true;
            }
        }

        return false;

    }




}

