package com.example.jimmyjonsson.quizlogic;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Daniel on 2018-05-02.
 */

public class RegisterActivity extends AppCompatActivity {

    private DBHandler dbHandler;
    private String regUser = "Username:";
    private String regPass = "Password: ";
    public ArrayList<User> userList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHandler = new DBHandler();
        Button okButton = (Button) findViewById(R.id.okButton);
        Button backButton = (Button) findViewById(R.id.backButton);
        final TextView userNameTextView = (TextView) findViewById(R.id.userNameTextView);
        final TextView passwordTextView = (TextView) findViewById(R.id.passwordTextView);
        final EditText textUser = (EditText) findViewById(R.id.plainTextUser);
        final EditText textPassword = (EditText) findViewById(R.id.plainTextPass);

        userList = new ArrayList<>();
        userList = dbHandler.getPLayer();

        userNameTextView.setText(regUser);
        passwordTextView.setText(regPass);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUser = textUser.getText().toString();
                String newPass = textPassword.getText().toString();

                int duplicates = 0;
                for (User user: userList){
                    if (user.getUsername().equals(newUser)){
                        duplicates++;
                    }
                }
                if (newUser.length() < 3 || newPass.length() < 3 || duplicates > 0){
                    AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
                    alertDialog.setTitle("Invalid input");
                    alertDialog.setMessage("The username and password must be at least 3 characters long and your username must be unique.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                } else {
                    dbHandler.addPlayer(2,newUser, newPass,0);
                   int idOfPlayer = dbHandler.getIDofUserName(newUser);
                    dbHandler.updateSaveTable(0,0,0,idOfPlayer);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });




    }



}
