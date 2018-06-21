package com.example.mohammad.studentpa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private Button register;
    private Button login;
    private EditText username;
    private EditText password;
    private TextView prompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Remember to add a security mechanism to avoid brute force logins
        //e.g. initialise a counter
        register= findViewById(R.id.buttonSignUp);
        login= findViewById(R.id.buttonLogin);
        username= findViewById(R.id.editTextEmail);
        password= findViewById(R.id.editTextPassword);

        String email;
        String passwordString;


        register.setOnClickListener(new View.OnClickListener() {//method to start next activity
            View regView;
            @Override
            public void onClick(View v) {//on button "Sign up" clicked, obvious
                //TODO: store details to DB
                startRegister(regView);//will start registration activity
            }
        });

        //onClick Listener,as stated, to listen for button clicks
        login.setOnClickListener(new View.OnClickListener() {
            View view;
            @Override
            public void onClick(View v) {
                //TODO: verification and authentication of details
                startMain(view);//will start main activity
            }
        });

    }


    public void startMain(View view){
        Intent mainIntent= new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();//prevents user from returning to this screen
    }

    public void startRegister(View view){
        Intent register= new Intent(this, Registration.class);
        startActivity(register);
        finish();//prevents user from returning to this screen
    }
}
