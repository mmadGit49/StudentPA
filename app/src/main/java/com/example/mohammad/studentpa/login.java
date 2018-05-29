package com.example.mohammad.studentpa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Remember to add a security mechanism to avoid brute force logins
        //e.g. initialise a counter
    }
}
