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
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

                public static String userName;
                public ArrayList<User> userList;
                public static int [] tester;
                public static DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userList = new ArrayList<>();
        dbHelper = new DBHelper(this);
        dbHelper.preDefinedUsers();
        userList = dbHelper.getUsers();


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
                    Log.e("test","Lösenord stämmde");
                    userName = usernameField.getText().toString();
                    tester = dbHelper.readFromSave(userName);
                    for(int i = 0; i < tester.length; i++) {
                        Log.e("H", Integer.toString(tester[i]));
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


    //Original manifest in case of errors

    /* <?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
            package="com.example.jimmyjonsson.quizlogic">

<uses-permission android:name="android.permission.INTERNET" />

<application
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:roundIcon="@mipmap/ic_launcher_round"
    android:supportsRtl="true"
    android:theme="@style/AppTheme">
<activity android:name=".MainActivity" />
<activity android:name=".HighscoreActivity" />
<activity android:name=".SplashScreenStart" />
<activity
    android:name=".LoginActivity"
    android:label="@string/title_activity_login"

    android:theme="@style/AppTheme">
<intent-filter>
<action android:name="android.intent.action.MAIN" />

<category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
</activity>
<activity
    android:name=".GameOptionActivity"
    android:label="@string/title_activity_game_option"
    android:theme="@style/AppTheme" />
<activity android:name=".SplashScreenStart"></activity>
</application>

</manifest> */


}

