package com.example.assessment_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username, fullname, email, pwd, pwdrepeat;
    private Button register, signin;
    private TextView incorrectpwd, incorrectusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SessionManager sessionManager;

        Functions fun = new Functions();

        sessionManager = new SessionManager(getApplicationContext());
        username = (EditText) findViewById(R.id.username);
        fullname = (EditText) findViewById(R.id.fullname) ;
        email = (EditText) findViewById(R.id.email);
        pwd = (EditText) findViewById(R.id.pwd);
        pwdrepeat = (EditText) findViewById(R.id.pwdrepeat);
        register = (Button) findViewById(R.id.btnregister);
        signin = (Button) findViewById(R.id.btnsignin);
        incorrectpwd = (TextView) findViewById(R.id.incorrectPwd);
        incorrectusername = (TextView) findViewById(R.id.incorrectUsername);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseAccess dbAccess = DatabaseAccess.getInstance(getApplicationContext());
                dbAccess.open();

                String user = username.getText().toString();
                String name = fullname.getText().toString();
                String userEmail = email.getText().toString();
                String pass = pwd.getText().toString();
                String passRepeat = pwdrepeat.getText().toString();

                if(fun.emptyInputString(user, name, userEmail, pass, passRepeat) != false){
                    Toast.makeText(MainActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if(fun.valUsername(user) == false){ // Valid username?
                        Toast.makeText(MainActivity.this, "Invalid username", Toast.LENGTH_SHORT).show();
                        incorrectusername.setVisibility(View.VISIBLE);
                    } else {
                        if (fun.valEmail(userEmail) == false) { // Valid email?
                            Toast.makeText(MainActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                        } else {
                            if (fun.valPwd(pass) == false) { // Valid password?
                                Toast.makeText(MainActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                                incorrectpwd.setVisibility(View.VISIBLE);
                            } else {
                                if (pass.equals(passRepeat)) { // Passwords match?
                                    Boolean checkUser = dbAccess.checkUsername(user, userEmail);
                                    if (checkUser == false) { // Username exist?
                                        Boolean insert = dbAccess.insertData(user, name, userEmail, pass);
                                        if (insert == true) { // If all requirements are met, the information is added to the database
                                            Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                            sessionManager.setLogin(true);
                                            sessionManager.setUsername(user);
                                            Intent intent = new Intent(getApplicationContext(), Navigation.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
                dbAccess.close();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        });

    } // onCreate
}
