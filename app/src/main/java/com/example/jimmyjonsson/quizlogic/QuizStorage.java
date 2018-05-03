package com.example.jimmyjonsson.quizlogic;



public class QuizStorage {


    private String quizQuestions [] = {
            "1. What is the capital city of India?",
            "2. What is the name of the president currently in charge of Russia?",
            "3. Which country won the World Championship in football, 2010?",
            "4. Which is the biggest mountain in the world",
            "5. Which artist painted the famous picture 'Skriet'? ",
            "6. Name the largest ocean in the world",
            "7. Who played the character Neo in the movie, The Matrix?",
            "8. Which color is a Welsh poppy?",
            "9. In 2011, which country hosted a Formula 1 race for the first time?",
            "10. Name the three primary colours",
    };

    private String quizAnswers [][] = {
            {"Mumbai", "New Delhi", "Bangalore", "Hyderabad"},
            {"Donald Trump", "Stefan Löfvén", "Dmitry Medvedev", "Vladimir Putin"},
            {"Spain", "Brasil", "Germany", "Italy"},
            {"K2", "Kebenekaise", "Mount Everest", "Hallandsåsen"},
            {"Edvard Munch", "Picasso", "Michelangelo", "van Gogh"},
            {"Pacific Ocean", "Baltic Sea", "Mediterranean", "Atlantic Ocean"},
            {"Leonardo DiCaprio", "Mel Gibson", "Jackie Chan", "Keanu Reeves"},
            {"Blue", "Green", "Brown", "Yellow"},
            {"Bahrain", "China", "India", "Singapore"},
            {"Red, yellow and blue", "Black, white and pink", "Brown, Green and Red", "Red, yellow and green"},
    };

    //Answers are numbered in the order they are displayed for the user
    private String correctAnswers[] = {
            "New Delhi", "Vladimir Putin", "Spain", "Mount Everest","Edvard Munch", "Pacific Ocean",
            "Keanu Reeves", "Yellow",  "India", "Red, yellow and blue",
    };


    // returns the questions in the array based on the index
    public String getQuestion(int a) {
        String question = quizQuestions[a];
        return question;
    }


    // return the choices based which question in the array
    // based on number of multiple choices in the Quiz, in this case 4
    public String getChoice(int index, int num) {

        String choices = quizAnswers[index][num-1];

        return choices;
    }



    //  returns the correct answer based on the index in the "correct" array
    public String getCorrectAnswer(int a) {

        String answer = correctAnswers[a];

        return answer;
    }

    //total length of the array with all the questions
    public int getLength(){
        return quizQuestions.length;
    }
}
