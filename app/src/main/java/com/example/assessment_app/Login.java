package com.example.assessment_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    SessionManager sessionManager;
    private EditText username, pwd;
    private Button loginbtn, signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(getApplicationContext());
        username = (EditText) findViewById(R.id.username1);
        pwd = (EditText) findViewById(R.id.pwd1);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        signupbtn = (Button) findViewById(R.id.signupbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseAccess dbAccess = DatabaseAccess.getInstance(getApplicationContext());
                dbAccess.open();

                String user = username.getText().toString();
                String pass = pwd.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(Login.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = dbAccess.checkUsernamepwd(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                        sessionManager.setLogin(true);
                        sessionManager.setUsername(user);
                        Intent intent = new Intent(getApplicationContext(), Navigation.class);//Navigation
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

                dbAccess.close();
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }

}