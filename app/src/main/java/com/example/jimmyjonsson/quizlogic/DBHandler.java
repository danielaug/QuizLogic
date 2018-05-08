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

    public void addPlayer(int idPlayer, String userName, String password, int highscore) {


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
                    String query = " insert into user (iduser,userName, password, highscore)"
                            + " values (?, ?, ?, ?)";

                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt(1, idPlayer);
                    preparedStmt.setString(2, userName);
                    preparedStmt.setString(3, password);
                    preparedStmt.setInt(4, highscore);

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




}
