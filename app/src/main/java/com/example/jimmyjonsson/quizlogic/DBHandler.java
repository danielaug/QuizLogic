package com.example.jimmyjonsson.quizlogic;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import static com.example.jimmyjonsson.quizlogic.LoginActivity.connectionHelper;

/**
 * Created by robin on 2018-05-09.
 */

public class DBHandler {
    private final String connectionURL;
    Connection connect;



    public DBHandler() {

        connectionHelper = new ConnectionHelper();
        connect = connectionHelper.connectionclasss();

        connectionURL = "jdbc:mysql://mysql4.gear.host:3306/quiztime?user=quiztime&password=hejhejhej!";
    }



    String ConnectionResult = "";
    Boolean isSuccess = false;

    public synchronized void addPlayer(String userName, String password, int highscore) {


        try {
            ConnectionHelper conStr = new ConnectionHelper();
            connect = conStr.connectionclasss();        // Connect to database
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access!";
            } else {

                try {
                    // create a mysql database connection
                    String myDriver = "com.mysql.jdbc.Driver";
                    String myUrl = "jdbc:mysql://mysql4.gear.host:3306/quiztime?user=quiztime&password=hejhejhej!";
                    Class.forName(myDriver);
                    Connection conn = DriverManager.getConnection(myUrl);


                    // the mysql insert statement
                    String query = " insert into user (userName, password, highscore)"
                            + " values (?, ?, ?)";

                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1, userName);
                    preparedStmt.setString(2, password);
                    preparedStmt.setInt(3, highscore);

                    // execute the preparedstatement
                    preparedStmt.execute();

                    conn.close();
                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }
            }


            ConnectionResult = " successful";
            isSuccess = true;
            connect.close();

        } catch (Exception ex) {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }


    }

    public synchronized void updateMultiPlayerHighscore1(int high, int userID) {
        try  {


                    PreparedStatement pstm = connect.prepareStatement("UPDATE `multiplayer` SET `highscore1`=? WHERE `username`=?");
                    pstm.setInt(1, high);
                    pstm.setInt(2,userID);
                    pstm.executeUpdate();

                    // execute the preparedstatement

                    pstm.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }

    }



    public synchronized void updateMatchTable(int playerTwoID, int highscore, int opponentID) {


        try  {
                    PreparedStatement pstm = connect.prepareStatement("UPDATE quiztime.match SET `player2`=?,`h2`=? WHERE `user_iduser`=?");
                    pstm.setInt(1, playerTwoID);
                    pstm.setInt(2,highscore);
                    pstm.setInt(3,opponentID);
                    pstm.executeUpdate();

                    // execute the preparedstatement


            pstm.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }
    }

    public synchronized void updateSaveTable(int high, int counter, int time, int userID) {

        try  {
                    PreparedStatement pstm = connect.prepareStatement("UPDATE `save` SET `highscore`=?, `counter`=?, `time`=? WHERE `user_iduser`=?");
                    pstm.setInt(1, high);
                    pstm.setInt(2, counter);
                    pstm.setInt(3, time);
                    pstm.setInt(4,userID);
                    pstm.executeUpdate();

                    // execute the preparedstatement


            pstm.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }
    }


    public synchronized void insertSaveTable(int high, int counter, int time, int userID) {

        try  {
                    PreparedStatement pstm = connect.prepareStatement("INSERT INTO save (highscore,counter,time,user_iduser) VALUES (?,?,?,?)");
                    pstm.setInt(1, high);
                    pstm.setInt(2, counter);
                    pstm.setInt(3, time);
                    pstm.setInt(4,userID);        
                    pstm.executeUpdate();

                    // execute the preparedstatement


            pstm.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }

    }




    public synchronized ArrayList<User> getPLayer() {
        String m = null;
        ArrayList<User> viewPlayers = new ArrayList<>();
        try  {
                    Statement statement = connect.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `user`");

                    // the mysql insert statement
                    while (rs.next()) {
                        User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                        viewPlayers.add(user);

                    }

            rs.close();


                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }

        return  viewPlayers;
    }

    public synchronized int getIDofUserName(String userName) {
        int theID = 0;
        try  {
                    Statement statement = connect.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM user where userName='" + userName + "'");

                    while (rs.next()) {
                        theID = rs.getInt(1);

                    }

                    rs.close();

                    // execute the preparedstatement

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }


        return theID;
    }



    public synchronized void newHighScore ( int ID, int highscore){

        try  {


                    PreparedStatement pstm = connect.prepareStatement("UPDATE `user` SET `highscore`=? WHERE `iduser`=?");
                    pstm.setInt(1, highscore);
                    pstm.setInt(2, ID);
                    pstm.executeUpdate();



            pstm.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }

    }
    public synchronized int[] readFromSave ( int ID){
        int[] myArray = {0, 0, 0};

        try  {
            Statement statement = connect.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `save` where user_iduser='" + ID + "'");

                    // the mysql insert statement
                    while (rs.next()) {
                        myArray[0] = rs.getInt(2);
                        myArray[1] = rs.getInt(3);
                        myArray[2] = rs.getInt(4);

                    }

            rs.close();


                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }


        return myArray;

    }


    public synchronized int getHighScore ( int ID){
        int highscore = 0;
        try {
                    Statement statement = connect.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `user` where iduser='" + ID + "'");

                    // the mysql insert statement
                    while (rs.next()) {
                        highscore = rs.getInt(4);

                    }





                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }

        return highscore;

    }


    public synchronized int getHighScore1 ( int ID){
        int highscore = -1;
        try  {
                    Statement statement = connect.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `multiplayer` where username='" + ID + "'");

                    // the mysql insert statement
                    while (rs.next()) {
                        highscore = rs.getInt(3);

                    }



            rs.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }

        return highscore;

    }

    public synchronized int getOpponent (int ID){
        int opponentID = 0;
        try  {
                    Statement statement = connect.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `opponentable` where user_iduser='" + ID + "'");

                    // the mysql insert statement
                    while (rs.next()) {
                        opponentID = rs.getInt(1);

                    }

            rs.close();



                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }





        return opponentID;

    }

    public synchronized int insertToOpponent (int opponentID,int ID){

        try  {
                    PreparedStatement pstm = connect.prepareStatement("INSERT INTO opponentable (opponent,user_iduser) VALUES (?,?)");
                    pstm.setInt(1,opponentID);
                    pstm.setInt(2,ID);
                    pstm.execute();


            pstm.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }


        return opponentID;

    }


    public synchronized void deleteFromOpponent (int ID){
        try  {
                    PreparedStatement pstm = connect.prepareStatement("DELETE FROM opponentable WHERE user_iduser=" + ID);
                    pstm.execute();

                    // the mysql insert statement



            pstm.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }
    }


    public synchronized void createMultiplayerTable(int playerOneID, String playerTwoID, int highscore1, int highscore2) {
        try {
                    PreparedStatement pstm = connect.prepareStatement("INSERT INTO multiplayer (username, opponent, highscore1, highscore2) VALUES (?,?,?,?)");
                    pstm.setInt(1, playerOneID);
                    pstm.setString(2, playerTwoID);
                    pstm.setInt(3, highscore1);
                    pstm.setInt(4, highscore2);
                    pstm.executeUpdate();

            pstm.close();


                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }
    }

    public synchronized String getOpponentName(int ID){
        String user = null;
        try  {

                    Statement statement = connect.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `user` where iduser='" + ID + "'");

                    // the mysql insert statement
                    while (rs.next()) {
                        user = rs.getString(2);

                    }



            rs.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }


        return user;

    }


    public synchronized int getOpponentID(String ID){
        int user = 0;
        try  {
                    // create a mysql database connection

                    Statement statement = connect.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `multiplayer` where opponent='" + ID + "'");

                    // the mysql insert statement
                    while (rs.next()) {
                        user = rs.getInt(1);

                    }



            rs.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }

        return user;

    }
    public synchronized boolean checkInvite(int ID){
                boolean checker = false;
        try  {
                    Statement statement = connect.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `invite` where user_iduser='" + ID + "'");

                    // the mysql insert statement
                    while (rs.next()) {
                        checker = rs.getBoolean(3);

                    }



            rs.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }
        return checker;

    }


    public synchronized void createMatchTable(int playerID, int playerIDTwo, int highscore1,int highscore2) { // Replace multiplayer ID with the respondents player ID?
        try {

                    PreparedStatement pstm = connect.prepareStatement("INSERT INTO quiztime.match (user_iduser,player2,h1,h2) VALUES (?,?,?,?)");
                    pstm.setInt(1, playerID);
                    pstm.setInt(2, playerIDTwo);
                    pstm.setInt(3, highscore1);
                    pstm.setInt(4, highscore2);

                    pstm.execute();

            pstm.close();


                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }


    }
    public synchronized void setInviteToTrue(int playerID) { // Replace multiplayer ID with the respondents player ID?
            try {

                System.out.println("WOW");
                    PreparedStatement pstm = connect.prepareStatement("INSERT INTO invite (user_iduser,state) VALUES (?,?)");
                    pstm.setInt(1,playerID);
                    pstm.setBoolean(2,true);

                    pstm.execute();

                pstm.close();


                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }
            }





    public synchronized void deletePLayerFromInvite(int index) {
        try {
                    System.out.println("WOW");
                    PreparedStatement pstm = connect.prepareStatement("DELETE FROM invite WHERE user_iduser=" + index);

                    pstm.execute();


            pstm.close();


                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }


    }
    public synchronized void deletePLayerFrommultiplayer(int opponent) {
        try {

                    PreparedStatement pstm = connect.prepareStatement("DELETE FROM multiplayer WHERE username=" + opponent);
                    pstm.execute();


            pstm.close();


                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }





    }

    public synchronized int[] returnLatestMultiPlayerMatch(int playerID){
        int[] results = {0,0,0,0,0};
        System.out.println("The method to return the latest multi player match has been reached.");
        System.out.println("The user ID passed to the method: " + playerID);
                try {
                    Statement statement = connect.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `match` WHERE user_iduser='" + playerID + "' ORDER BY idmatch DESC LIMIT 1");


                    System.out.println("Inserting values...");
                    while (rs.next()) {
                        results[0] = rs.getInt(1);
                        System.out.println(results[0]);
                        results[1] = rs.getInt(2);
                        System.out.println(results[1]);
                        results[2] = rs.getInt(3);
                        System.out.println(results[2]);
                        results[3] = rs.getInt(4);
                        System.out.println(results[3]);
                        results[4] = rs.getInt(5);
                        System.out.println(results[4]);

                    }



                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }

        return results;
        //returns an int array with matchID (not important), player1_id, player2_id, highscore1 and highscore 2.

    }




}
