package com.example.mohammad.studentpa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Registration extends AppCompatActivity {
    private Button register;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private Spinner gender;
    private EditText dob;//date of birth
    private EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        register= (Button) findViewById(R.id.buttonRegister);
        firstName= (EditText) findViewById(R.id.editTextName);
        lastName= (EditText) findViewById(R.id.editTextSurname);
        email= (EditText) findViewById(R.id.editTextEmail);
        password= (EditText) findViewById(R.id.editTextNewPassword);
        confirmPassword=(EditText) findViewById(R.id.editTextconfirmNewPassword);
        gender=(Spinner) findViewById(R.id.spinnerGender);
        dob= (EditText) findViewById(R.id.editTextDob);

        String fName, lName, email, pword, confirmPword;

        register.setOnClickListener(new View.OnClickListener() {
            View view;
            @Override
            public void onClick(View v) {
                startLogin(view);
            }
        });


    }

    public void startLogin(View view){
        Intent loginIntent= new Intent(this, Login.class);
        startActivity(loginIntent);
        finish();//prevents user from returning to this screen
    }
}
