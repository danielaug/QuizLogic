package com.example.jimmyjonsson.quizlogic;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



import static com.example.jimmyjonsson.quizlogic.LoginActivity.dbHandler;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.continueButtonSaveHolder;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.userName;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.userNameID;


public class GameOptionActivity extends AppCompatActivity {


    public static int counter;
    public static int currentScoreCounter;
    public static int countDownValueSaver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_option);



        continueButtonSaveHolder = dbHandler.readFromSave(userNameID);
        currentScoreCounter = continueButtonSaveHolder[0];
        countDownValueSaver = continueButtonSaveHolder[2];
        counter = continueButtonSaveHolder[1];



    final Handler handler = new Handler(); // CHECKS IF USER RECEIVED INVITE
        handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    boolean inviteChecker = dbHandler.checkInvite(userNameID);
                   int opponentID2 = dbHandler.getOpponentID(userName);
                    boolean inviteCheckerOpponent = dbHandler.checkInvite(opponentID2);

                    if (inviteCheckerOpponent && inviteChecker) {
                        Intent intent = new Intent(GameOptionActivity.this, MultiplayerGameplay.class);
                        startActivity(intent);
                    } else if (inviteChecker && !inviteCheckerOpponent) {
                        notifyMe();
                        handler.removeCallbacksAndMessages(this);
                    } else {
                        System.out.println("Nothing thread 1");
                        handler.postDelayed(this, 40000);

                    }

                }
            }, 40000);  //the time is in miliseconds


        final String[] menuItems = {"Continue", "New Game", "Challenge Another Player", "Highscore", "Quit"};

        ListView listView = (ListView) findViewById(R.id.listviewGameOption);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(GameOptionActivity.this, android.R.layout.simple_list_item_1, menuItems);

        listView.setAdapter(listViewAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    handler.removeCallbacksAndMessages(null);
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
                    handler.removeCallbacksAndMessages(null);
                    counter = 0;
                    countDownValueSaver = 100;
                    currentScoreCounter = 0;

                    Intent intent = new Intent(GameOptionActivity.this, MainActivity.class);
                    startActivity(intent);


                }
                if (position == 2){
                    handler.removeCallbacksAndMessages(null);
                    Intent intent = new Intent(GameOptionActivity.this, ChooseOpponentActivity.class);
                    startActivity(intent);
                }

                if(position == 3) {
                    handler.removeCallbacksAndMessages(null);
                    Intent intent = new Intent(GameOptionActivity.this, ChooseHighscore.class);
                    startActivity(intent);
                }

                if (position == 4){
                    SharedPreferences sharedPreferences1 = getSharedPreferences("pref", MODE_PRIVATE);        //initialize the sharedpreference by specifying file name and MODE PRIVATE, means it can only be used by this app.
                    SharedPreferences.Editor editor = sharedPreferences1.edit();   //make it possible to edit.
                    editor.putInt("points", counter);       //save how many points  user quit with
                    userNameID = -1;
                    handler.removeCallbacksAndMessages(null);


                    editor.commit();

                    Intent intent = new Intent(GameOptionActivity.this, LoginActivity.class);
                    startActivity(intent);



                }


                }

            }
        );



    }
    private void notifyMe() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final int opponentID = dbHandler.getOpponentID(userName);
        final String opponent = dbHandler.getOpponentName(opponentID);
        builder.setTitle("You have been challenged by " + opponent);
        builder.setMessage("Do you wish to accept?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                System.out.println("UserID" + userNameID);
                System.out.println("opponent" + opponent);
                System.out.println("opponent id" + opponentID);

                try {
                    dbHandler.deleteFromOpponent(userNameID);
                } catch (Exception e) {

                }



                dbHandler.insertToOpponent(opponentID, userNameID);
                dbHandler.deletePLayerFrommultiplayer(opponentID);
                dbHandler.setInviteToTrue(opponentID);
                dbHandler.createMultiplayerTable(userNameID,opponent,-1,-1);
                Intent intent = new Intent(GameOptionActivity.this, MultiplayerGameplay.class);
                startActivity(intent);

                dialog.dismiss();

            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHandler.deletePLayerFromInvite(userNameID);
                int opponentID2 = dbHandler.getOpponentID(userName);
                dbHandler.deletePLayerFrommultiplayer(opponentID2);
                dialog.dismiss();


            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }



}

