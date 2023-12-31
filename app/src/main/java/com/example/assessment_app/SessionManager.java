package com.example.assessment_app;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences("appkey", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.commit();
    }

    public void setLogin(boolean login){
        editor.putBoolean("key_login", login);
        editor.commit();
    }

    public boolean getLogin(){
        return sharedPreferences.getBoolean("key_login", false);
    }

    public void setUsername(String username){
        editor.putString("key_username", username);
        editor.commit();

    }

    public String getUsername(){
        return sharedPreferences.getString("key_username", "");
    }

}
