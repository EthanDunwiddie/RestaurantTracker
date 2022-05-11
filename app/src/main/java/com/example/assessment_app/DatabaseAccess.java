package com.example.assessment_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.assessment_app.UserContract.*;

import java.nio.IntBuffer;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor cursor = null;

    // private constructor
    private DatabaseAccess(Context context) {
        this.openHelper = new DBHelper(context);
    }

    // return singe instance of database
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);

        }
        return instance;
    }

    // open the connection
    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    // close the connection
    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    /* -----query database----- */


    // Not sure how to get this to return an array
    /*
    public String[] getMeals(){
        cursor = db.rawQuery("SELECT meal FROM meals WHERE res_id = 1;", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            String meals = cursor.getString(0);
            buffer.append("" + meals);

        }
        return buffer.toString();
    }
    */

    public Cursor getAllItems() {
        return db.query(
                MealEntry.TABLE_NAME1,
                null,
                null,
                null,
                null,
                null,
                null );
    }

    // Checks database for given username or email, and password (username is used for both email and username)
    public Boolean checkUsernamepwd(String username, String pwd) {

        cursor = db.rawQuery("SELECT * FROM " +
                UserEntry.TABLE_NAME + " WHERE " +
                UserEntry.COLUMN_USERNAME + " = ? AND " +
                UserEntry.COLUMN_PWD + " = ? OR " +
                UserEntry.COLUMN_EMAIL + " = ? AND " +
                UserEntry.COLUMN_PWD + " = ?", new String[]{username, pwd});


        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    // Inserts registration info to database
    public Boolean insertData(String username, String fullname, String email, String pwd) {
        ContentValues contentValues = new ContentValues();

        /* Not sure how to properly hash passwords
        MessageDigest md = MessageDigest.getInstance("MD5");

        String pwdHash;
        byte[] messageDigest = md.digest(pwd.getBytes());

        BigInteger bigInt = new BigInteger(1, messageDigest);

        pwdHash = bigInt.toString(16); */

        contentValues.put(UserEntry.COLUMN_USERNAME, username);
        contentValues.put(UserEntry.COLUMN_FULLNAME, fullname);
        contentValues.put(UserEntry.COLUMN_EMAIL, email);
        contentValues.put(UserEntry.COLUMN_PWD, pwd);
        long result = db.insert(UserEntry.TABLE_NAME, null, contentValues);
        if (result==-1) {
            return false;
        } else {
            return true;
        }
    }

    // Checks if username and email already exist within database
    public Boolean checkUsername(String username, String email){

        Cursor cursor = db.rawQuery("SELECT * FROM " +
                UserEntry.TABLE_NAME + " WHERE " +
                UserEntry.COLUMN_USERNAME + " = ? or " +
                UserEntry.COLUMN_EMAIL + " = ?", new String[] {username, email});

        if(cursor.getCount()>0) {
            return true;
        } else{
            return false;
        }
    }

}
