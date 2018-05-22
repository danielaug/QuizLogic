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

                    PreparedStatement pstm = conn.prepareStatement("UPDATE `multiplayer` SET `highscore1`=?, WHERE `username`=?");
                    pstm.setInt(1, high);
                    pstm.setInt(2,userID);
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

    public synchronized void updateSaveTable(int high, int counter, int time, int userID) {


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


    public synchronized void insertSaveTable(int high, int counter, int time, int userID) {


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




    public synchronized ArrayList<User> getPLayer() {
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

    public synchronized  int getIDofUserName(String userName) {
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





    public synchronized void newHighScore ( int ID, int highscore){


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
    public synchronized int[] readFromSave ( int ID){
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


    public synchronized int getHighScore ( int ID){
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


    public synchronized int getHighScore1 ( int ID){
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
                    ResultSet rs = statement.executeQuery("SELECT * FROM `multiplayer` where username='" + ID + "'");

                    // the mysql insert statement
                    while (rs.next()) {
                        highscore = rs.getInt(3);

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



    public synchronized int getHighScore2 ( int ID){
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
                    ResultSet rs = statement.executeQuery("SELECT * FROM `multiplayer` where username='" + ID + "'");

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


    public synchronized void createMultiplayerTable(int playerOneID, String playerTwoID, int highscore1, int highscore2) {
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

                    PreparedStatement pstm = conn.prepareStatement("INSERT INTO multiplayer (username, opponent, highscore1, highscore2) VALUES (?,?,?,?)");
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

    public synchronized String getOpponentName(int ID){
        String user = null;
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
                        user = rs.getString(2);

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
        return user;

    }


    public synchronized int getOpponentID(String ID){
        int user = 0;
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
                    ResultSet rs = statement.executeQuery("SELECT * FROM `multiplayer` where opponent='" + ID + "'");

                    // the mysql insert statement
                    while (rs.next()) {
                        user = rs.getInt(1);

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
        return user;

    }
    public synchronized boolean checkInvite(int ID){
                boolean checker = false;
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
                    ResultSet rs = statement.executeQuery("SELECT * FROM `invite` where user_iduser='" + ID + "'");

                    // the mysql insert statement
                    while (rs.next()) {
                        checker = rs.getBoolean(3);

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
        return checker;

    }


    public synchronized void setInviteToTrue(int playerID) { // Replace multiplayer ID with the respondents player ID?
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

                    System.out.println("WOW");
                    PreparedStatement pstm = conn.prepareStatement("INSERT INTO invite (user_iduser,state) VALUES (?,?)");
                    pstm.setInt(1,playerID);
                    pstm.setBoolean(2,true);

                    pstm.execute();

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


    public synchronized void deletePLayerFromInvite(int index) {
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

                    System.out.println("WOW");
                    PreparedStatement pstm = conn.prepareStatement("DELETE FROM invite WHERE user_iduser=" + index);

                    pstm.execute();


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
    public synchronized void deletePLayerFrommultiplayer(int opponent) {
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


                    PreparedStatement pstm = conn.prepareStatement("DELETE FROM multiplayer WHERE username=" + opponent);
                    pstm.execute();


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




}
