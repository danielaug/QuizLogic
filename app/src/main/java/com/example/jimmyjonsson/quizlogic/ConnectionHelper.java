package com.example.jimmyjonsson.quizlogic;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by robin on 2018-05-09.
 */

public class ConnectionHelper {
     Connection connection=null;
    String ConnectionURL = null;

    @SuppressLint("NewApi")
    public  Connection connectionclasss() {

        if(connection!=null) {
            System.out.println("If");
            return connection;
        }

        else {
            System.out.println("else");

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {


                Class.forName("com.mysql.jdbc.Driver");
                ConnectionURL = "jdbc:mysql://mysql4.gear.host:3306/quiztime?connectTimeout=0&socketTimeout=0&autoReconnect=true&user=quiztime&password=hejhejhej!";
                connection = DriverManager.getConnection(ConnectionURL);

            } catch (SQLException se) {
                Log.e("error here 1 : ", se.getMessage());
            } catch (ClassNotFoundException e) {
                Log.e("error here 2 : ", e.getMessage());
            } catch (Exception e) {

            }
            return connection;
        }
    }



}



