package com.example.jimmyjonsson.quizlogic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import static com.example.jimmyjonsson.quizlogic.GameOptionActivity.countDownValueSaver;
import static com.example.jimmyjonsson.quizlogic.GameOptionActivity.counter;
import static com.example.jimmyjonsson.quizlogic.GameOptionActivity.currentScoreCounter;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.continueButtonSaveHolder;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.userNameID;
import static com.example.jimmyjonsson.quizlogic.LoginActivity.dbHandler;

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


    Random rng = new Random();
    List<Integer> rngchoices;
    List<Integer> rngQuestions =  rng.ints(0,10).distinct().limit(10).boxed().collect(Collectors.<Integer>toList());








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentQuestionNumber = counter;
        currentScore = currentScoreCounter;
        countDownValue = countDownValueSaver;


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
                dbHandler.updateSaveTable(currentScore,currentQuestionNumber,countDownValue,userNameID);
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
                            if (checkHighScore(userNameID, currentScore)) {
                                updateHighScore(userNameID, currentScore);
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

            rngchoices = rng.ints(1, 5).distinct().limit(4).boxed().collect(Collectors.<Integer>toList());

            questionView.setText(questionLibrary.getQuestion(rngQuestions.get(currentQuestionNumber))); //Update questionfield

            /*Randomize the contents of the multiple choices*/

            if (rngchoices.get(0) == 1)
                buttonChoice1.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 1));
            else if (rngchoices.get(0) == 2)
                buttonChoice1.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 2));
            else if (rngchoices.get(0) == 3)
                buttonChoice1.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 3));
            else if (rngchoices.get(0) == 4)
                buttonChoice1.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 4));

            if (rngchoices.get(1) == 1)
                buttonChoice2.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 1));
            else if (rngchoices.get(1) == 2)
                buttonChoice2.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 2));
            else if (rngchoices.get(1) == 3)
                buttonChoice2.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 3));
            else if (rngchoices.get(1) == 4)
                buttonChoice2.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 4));

            if (rngchoices.get(2) == 1)
                buttonChoice3.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 1));
            else if (rngchoices.get(2) == 2)
                buttonChoice3.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 2));
            else if (rngchoices.get(2) == 3)
                buttonChoice3.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 3));
            else if (rngchoices.get(2) == 4)
                buttonChoice3.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 4));

            if (rngchoices.get(3) == 1)
                buttonChoice4.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 1));
            else if (rngchoices.get(3) == 2)
                buttonChoice4.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 2));
            else if (rngchoices.get(3) == 3)
                buttonChoice4.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 3));
            else if (rngchoices.get(3) == 4)
                buttonChoice4.setText(questionLibrary.getChoice(rngQuestions.get(currentQuestionNumber), 4));


            correctAnswerCheck = questionLibrary.getCorrectAnswer(rngQuestions.get(currentQuestionNumber));
            currentQuestionNumber++;

        }
        else {
            countDownValue = 0;
            counter = 0;
            timer.cancel();
            if (checkHighScore(userNameID, currentScore)) {
                updateHighScore(userNameID, currentScore);
            }
            Intent intent = new Intent(MainActivity.this, HighscoreActivity.class);
            continueButtonSaveHolder[0] = currentScore;
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


    private boolean checkHighScore(int user,int currentScore) {
        int high = dbHandler.getHighScore(user);
        Log.e("The highscore", Integer.toString(high));

        if (high < currentScore) {
            return true;
        } else {
            return false;
        }

    }
    private void updateHighScore(int currentUser,int higScore){
        Log.e("KÖRS","Lägger in nytt highscore");
        dbHandler.newHighScore(currentUser,higScore);
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


