package com.example.jimmyjonsson.quizlogic;

/**
 * Created by jimmy on 2018-03-19.
 */

public class User {
    private String username;
    private String password;
    private int highScore;
    private int ID;

    public User(int ID ,String username, String password,int highScore) {
        this.username = username;
        this.password = password;
        this.highScore = highScore;
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getID() {
        return ID;
    }
}
