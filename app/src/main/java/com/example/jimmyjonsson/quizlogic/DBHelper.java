package com.example.jimmyjonsson.quizlogic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by jimmy on 2018-03-19.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "GAMEDB";
    public static final int DBVERSION = 1;
    public static final String TABLE_NAME = "USER";
    public static final String COL_ID = "ID";
    public static final String COL_USERNAME = "USERNAME";
    public static final String COL_PASSWORD = "PASSWORD";
    public static final String COL_HIGHSCORE = "HIGHSCORE";

    SQLiteDatabase gameDatabaseWrite;
    SQLiteDatabase gameDatabaseRead;
    public DBHelper(Context context) {
        super(context,DBNAME,null,DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+ TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY," + COL_USERNAME + " TEXT," + COL_PASSWORD + " TEXT," + COL_HIGHSCORE + " INTEGER);";
        db.execSQL(createTable);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addUser (String username, String password){
        gameDatabaseWrite = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME,username);
        contentValues.put(COL_PASSWORD,password);
        contentValues.put(COL_HIGHSCORE,0);

        gameDatabaseWrite.insert(TABLE_NAME,null,contentValues);
        gameDatabaseWrite.close();
    }
    public void preDefinedUsers (){
        Log.e("EGEN","RUNNING PREDEFINED USERS");

        gameDatabaseRead = this.getReadableDatabase();
        String selectAll = "Select * from " + TABLE_NAME;

        Cursor cursor = gameDatabaseRead.rawQuery(selectAll,null);
        if (!cursor.moveToFirst()) {
            Log.e("EGEN", "INNE I IF STATMENT");
            addUser("JimmyP", "JimmyP");
            addUser("JimmyJ", "JimmyJ");
            addUser("DanielA", "DanielA");
            addUser("RobinF", "RobinF");
        }
        gameDatabaseRead.close();
    }
    public void newHighScore(String username, int highScore){
        gameDatabaseWrite = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_HIGHSCORE,highScore);

        gameDatabaseWrite.update(TABLE_NAME,contentValues,COL_USERNAME+ "=" + "'"+username+"'",null);
        Log.e("EGEN",username +" got new HIGHSCORE = "+ highScore);

    }
    public ArrayList<User> getUsers (){
        ArrayList<User> userArrayList = new ArrayList<>();
        gameDatabaseRead = this.getReadableDatabase();
        String selectAll = "Select * from " + TABLE_NAME;

        Cursor cursor = gameDatabaseRead.rawQuery(selectAll,null);
        if (cursor.moveToFirst()){
            do {
                User user = new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
                userArrayList.add(user);

            }while (cursor.moveToNext());
        }
        return userArrayList;

    }
}
