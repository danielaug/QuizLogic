package com.example.jimmyjonsson.quizlogic;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by robin on 2018-05-09.
 */

public class ConnectionHelper {
    String ip,db,DBUserNameStr,DBPasswordStr;

    @SuppressLint("NewApi")
    public Connection connectionclasss()
    {

        // Declaring Server ip, username, database name and password
        ip = "127.0.0.1";
        db = "atmdb";
        DBUserNameStr = "root";
        DBPasswordStr = "root";
        // Declaring Server ip, username, database name and password


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            ConnectionURL = "jdbc:mysql://mysql4.gear.host:3306/quiztime?user=quiztime&password=hejhejhej!";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}



