package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the ParseUser
                ParseUser user = new ParseUser();

                String userName = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if(userName == null || userName.isEmpty() ){
                    Toast.makeText(LoginActivity.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password == null || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Password cannot be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Set core properties
                user.setUsername(userName);
                user.setPassword(password);

                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            goMainActivity();
                            Toast.makeText(LoginActivity.this, "Signup Success", Toast.LENGTH_SHORT).show();
                        } else {
                            ///Better error handling
                            Log.e(TAG, "issue with signup", e);
                            Toast.makeText(LoginActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(TAG,"onClick Login Button");
                String userName = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(userName, password);
            }
        });
    }

    private void login(String userName, String password) {
        Log.i(TAG, "Attempt to login:"+ userName+ " , Password:"+password);
        //Navigate to Main Activitiy if the login is successful
        ParseUser.logInInBackground(userName,password, new LogInCallback(){

            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){

                    //Better error handling
                    Log.e(TAG, "issue with login", e);
                    Toast.makeText(LoginActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    return;
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}