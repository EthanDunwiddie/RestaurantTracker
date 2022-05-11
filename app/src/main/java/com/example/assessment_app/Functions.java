package com.example.assessment_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assessment_app.UserContract.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions {


/* ---- Input validation ---- */

    // Checks for empty inputs
    public boolean emptyInputString(String username, String fullname, String email, String pwd, String pwdrepeat){
        if(username.equals("")||fullname.equals("")||email.equals("")||pwd.equals("")||pwdrepeat.equals("")) {
            return true;
        } else{
            return false;
        }
    }

    // Checks for regular username format
    public boolean valUsername(String username){
        String userRegex = "^[a-zA-Z0-9._-]{3,20}$";
        Pattern userPat = Pattern.compile(userRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = userPat.matcher(username);
        return matcher.find();
    }

    // Checks for regular email format
    public boolean valEmail(String email){
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(email);
        return matcher.find();
    }

    // Checks for regular password format
    public boolean valPwd(String pwd){
        String pwdRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,64}$";
        Pattern pwdPat = Pattern.compile(pwdRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pwdPat.matcher(pwd);
        return matcher.find();
    }

}
