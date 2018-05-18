package com.example.jimmyjonsson.quizlogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by robin on 2018-05-09.
 */

public class DBHandler {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    public void addPlayer(String userName, String password, int highscore) {


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

    public void updateSaveTable(int high, int counter, int time, int userID) {


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

                    PreparedStatement pstm = conn.prepareStatement("UPDATE `save` SET `highscore`=?, `counter`=?, `time`=? WHERE `user_iduser`=?");
                    pstm.setInt(1, high);
                    pstm.setInt(2, counter);
                    pstm.setInt(3, time);
                    pstm.setInt(4,userID);
                    pstm.executeUpdate();

                    // execute the preparedstatement


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

    public void insertSaveTable(int high, int counter, int time, int userID) {


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

                    PreparedStatement pstm = conn.prepareStatement("INSERT INTO save (highscore,counter,time,user_iduser) VALUES (?,?,?,?)");
                    pstm.setInt(1, high);
                    pstm.setInt(2, counter);
                    pstm.setInt(3, time);
                    pstm.setInt(4,userID);
                    pstm.executeUpdate();

                    // execute the preparedstatement


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




    public ArrayList<User> getPLayer() {
        String m = null;
        ArrayList<User> viewPlayers = new ArrayList<>();


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

                    // create a sql date object so we can use it in our INSERT statement
                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `user`");

                    // the mysql insert statement
                    while (rs.next()) {
                        User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                        viewPlayers.add(user);

                    }


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

        return  viewPlayers;
    }

    public int getIDofUserName(String userName) {
        int theID = 0;
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


                    Statement statement = conn.createStatement();

                    ResultSet rs = statement.executeQuery("SELECT * FROM user where userName='" + userName + "'");

                    while (rs.next()) {
                        theID = rs.getInt(1);

                    }


                    // execute the preparedstatement
                    rs.close();

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
        return theID;
    }

    public void newHighScore ( int ID, int highscore){


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


                    PreparedStatement pstm = conn.prepareStatement("UPDATE `user` SET `highscore`=? WHERE `iduser`=?");
                    pstm.setInt(1, highscore);
                    pstm.setInt(2, ID);
                    pstm.executeUpdate();



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
    public int[] readFromSave ( int ID){
        int[] myArray = {0, 0, 0};


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

                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `save` where user_iduser='" + ID + "'");

                    // the mysql insert statement
                    while (rs.next()) {
                        myArray[0] = rs.getInt(2);
                        myArray[1] = rs.getInt(3);
                        myArray[2] = rs.getInt(4);

                    }


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
        return myArray;

    }


    public int getHighScore ( int ID){
        int highscore = 0;

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

                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT * FROM `user` where iduser='" + ID + "'");

                    // the mysql insert statement
                    while (rs.next()) {
                        highscore = rs.getInt(4);

                    }



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
        return highscore;

    }


    public boolean canInvitationBeSent(int playerOneID){
        boolean invite = false;
        boolean player = false;

        try {
            ConnectionHelper conStr = new ConnectionHelper();
            connect = conStr.connectionclasss();        // Connect to database
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access!";
            } else {
                try {
                    String myDriver = "com.mysql.jdbc.Driver";
                    String myUrl = "jdbc:mysql://mysql4.gear.host:3306/quiztime?user=quiztime&password=hejhejhej!";
                    Class.forName(myDriver);
                    Connection conn = DriverManager.getConnection(myUrl);

                    Statement statement1 = conn.createStatement();
                    ResultSet rs1 = statement1.executeQuery("SELECT * FROM `invite` WHERE user_iduser='" + playerOneID + "'");

                    while (rs1.next()){
                        player = rs1.getBoolean(1);
                    }





                    rs1.close();
                    conn.close();


                    if (player == true){
                        invite = true;
                    }
                    else {
                        System.out.println("Could not invite person.");
                    }


                } catch (Exception e){
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }

            }

            ConnectionResult = " successful";
            isSuccess = true;
            connect.close();

        } catch (Exception e) {
            isSuccess = false;
            ConnectionResult = e.getMessage();
        }

        return invite;
    }
    public void createMultiplayerTable(int playerOneID, String playerTwoID, int highscore1, int highscore2) {
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

                    PreparedStatement pstm = conn.prepareStatement("INSERT INTO multiplayer user_iduser, opponent, score_one, score_two) VALUES (?,?,?,?)");
                    pstm.setInt(1, playerOneID);
                    pstm.setString(2, playerTwoID);
                    pstm.setInt(3, highscore1);
                    pstm.setInt(4, highscore2);
                    pstm.executeUpdate();

                    conn.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }
            }


            ConnectionResult = " successful";
            isSuccess = true;
            connect.close();

        } catch (Exception e) {
            isSuccess = false;
            ConnectionResult = e.getMessage();
        }
    }


    /*


    public void respondToInvitation(boolean reply, int multiplayerID) { // Replace multiplayer ID with the respondents player ID?
        try {
            ConnectionHelper conStr = new ConnectionHelper();
            connect = conStr.connectionclasss();        // Connect to database
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access!";
            } else {
                try {
                    String myDriver = "com.mysql.jdbc.Driver";
                    String myUrl = "jdbc:mysql://mysql4.gear.host:3306/quiztime?user=quiztime&password=hejhejhej!";
                    Class.forName(myDriver);
                    Connection conn = DriverManager.getConnection(myUrl);

                    if (reply){
                        PreparedStatement pstm = conn.prepareStatement("UPDATE `multiplayer` SET `response`=? WHERE `idmultiplayer`=?");
                        pstm.setInt(1, reply);
                        pstm.setInt(2, multiplayerID);
                        pstm.executeUpdate();
                    } else {
                        PreparedStatement pstm = conn.prepareStatement("DELETE FROM `multiplayer` WHERE `idmultiplayer`=?");
                        pstm.setInt(1,multiplayerID);
                        pstm.executeUpdate();
                    }



                    conn.close();

                } catch (Exception e) {
                    System.err.println("Got an exception!");
                    System.err.println(e.getMessage());
                }
            }

            ConnectionResult = " successful";
            isSuccess = true;
            connect.close();

        } catch (Exception e) {
            isSuccess = false;
            ConnectionResult = e.getMessage();
        }
    }
*/




}
