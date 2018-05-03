package com.example.jimmyjonsson.quizlogic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.jimmyjonsson.quizlogic.GameOptionActivity.countDownValueSaver;
import static com.example.jimmyjonsson.quizlogic.GameOptionActivity.counter;
import static com.example.jimmyjonsson.quizlogic.GameOptionActivity.currentScoreCounter;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.userName;

public class MainActivity extends AppCompatActivity {

    private QuizStorage questionLibrary = new QuizStorage();


    private Button buttonChoice1; // choice 1 for Question
    private Button buttonChoice2; // choice 2 for Question
    private Button buttonChoice3; // choice 3 for Question
    private Button buttonChoice4; // choice 4 for Question

    //private TextView scoreField;   // view for current total score, temporary code!
    private TextView questionView;  //current question to answer


    private int currentScore;  // current score
    private int currentQuestionNumber; // current question number based on were in the array it is
    private String correctAnswerCheck;  // correct answer for question in mQuestionView


    Context context = this;

    private Timer timer;
    private int countDownValue;
    private TextView countDownTextView;
    SharedPreferences sharedPreferences;

    String currentUser;
    int test;
    private DBHelper dbHelper;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentQuestionNumber = counter;
        currentScore = currentScoreCounter;
        countDownValue = countDownValueSaver;
        dbHelper = new DBHelper(this);





        sharedPreferences = getSharedPreferences("pref",MODE_PRIVATE);
        currentUser = sharedPreferences.getString("userName",null);

        // setup screen for the first question with four alternative to answer

        questionView = (TextView)findViewById(R.id.question); //TextView for the Question
        buttonChoice1 = (Button)findViewById(R.id.choice1);
        buttonChoice2 = (Button)findViewById(R.id.choice2);
        buttonChoice3 = (Button)findViewById(R.id.choice3);
        buttonChoice4 = (Button)findViewById(R.id.choice4);
        //scoreField = (TextView)findViewById(R.id.score); //Not in use for the moment
        countDownTextView = (TextView)findViewById(R.id.counter);


        Button buttonQ = (Button) findViewById(R.id.qButton);

        buttonQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateSharedPref();
                Intent intent = new Intent(MainActivity.this, GameOptionActivity.class);
                startActivity(intent);


            }
        });








        //Countdown from 100,
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        countDownValue--;
                        countDownTextView.setText("" + countDownValue);

                        if (countDownValue == 0){
                            counter=0;
                            updateSharedPref();
                            if (checkHighScore(currentUser, currentScore)) {
                                updateHighScore(currentUser,currentScore);
                        }
                            Intent intentToResult = new Intent(context, HighscoreActivity.class);
                            intentToResult.putExtra("score",currentScore);
                            context.startActivity(intentToResult);
                            timer.cancel();
                        }
                    }
                });
            }
        },0, 1000);

        updateQuestion(); //invokes as soon as a button is pressed
        //updateScore(currentScore);// show current total score for the user
    }

    private void updateQuestion(){
        // check if we are not outside array bounds for questions
        if (currentQuestionNumber < questionLibrary.getLength() ){



            questionView.setText(questionLibrary.getQuestion(currentQuestionNumber)); //Update questionfield

            //Update 4 different choices based on the question
            buttonChoice1.setText(questionLibrary.getChoice(currentQuestionNumber, 1));
            buttonChoice2.setText(questionLibrary.getChoice(currentQuestionNumber, 2));
            buttonChoice3.setText(questionLibrary.getChoice(currentQuestionNumber, 3));
            buttonChoice4.setText(questionLibrary.getChoice(currentQuestionNumber,4));

            correctAnswerCheck = questionLibrary.getCorrectAnswer(currentQuestionNumber);
            currentQuestionNumber++;

        }
        else {
            countDownValue = 0;
            counter = 0;
            timer.cancel();
            if (checkHighScore(currentUser, currentScore)) {
                updateHighScore(currentUser,currentScore);
            }
            Intent intent = new Intent(MainActivity.this, HighscoreActivity.class);
            intent.putExtra("score", currentScore); // pass the current score to the second screen
            startActivity(intent);

        }
    }



    public void onClick(View view) {
        //all buttons in the same onClick to save space
        counter++;

        Button answer = (Button) view;

        // if the answer is correct, increase the score
        if (answer.getText() == correctAnswerCheck) {
            currentScore = currentScore + 1; //adding the new score to the total amount
            currentScoreCounter = currentScore;
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show(); //Toast to see if you answer correct or wrong
        }else
            Toast.makeText(MainActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();

        //updateScore(currentScore); // show current total score for the user
        updateQuestion(); // moving on the next answer once the user have chosen a answer
    }

    private void displaySplash(){

        if (currentScore == 10){

            //displaying code for the Splashview when all questions are fulfilled

        }
    }
    private boolean checkHighScore(String currentUser,int currentScore){


        DBHelper dbHelper;
        ArrayList<User> userList;
        dbHelper = new DBHelper(this);
        userList = dbHelper.getUsers();
        Log.e("KÖRS","Kollar om det är nytt highscore");



        for (int i = 0;i<userList.size();i++){
            Log.e("ajaja",currentScore + " Gammalt "+userList.get(i).getHighScore());
            if (currentUser.equals(userList.get(i).getUsername())&& userList.get(i).getHighScore()<currentScore){
                Log.e("KÖRS","Det var ett nytt HS return true");
                return true;
            }
        }

        return  false;

    }
    private void updateHighScore(String currentUser,int higScore){
        Log.e("KÖRS","Lägger in nytt highscore");
        dbHelper.newHighScore(currentUser,higScore);
    }
    private void updateSharedPref(){
        SharedPreferences sharedPreferences1 = getSharedPreferences("pref", MODE_PRIVATE);        //initialize the sharedpreference by specifying file name and MODE PRIVATE, means it can only be used by this app.
        SharedPreferences.Editor editor = sharedPreferences1.edit();   //make it possible to edit.
        editor.putInt("score", currentScoreCounter);
        editor.putInt("countDownValue", countDownValue);
        editor.putInt("points", counter);       //save how many points  user quit with
        editor.commit();
        timer.cancel();
    }

}


