package com.example.mohammad.studentpa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mohammad.studentpa.Util.DatePickerFragment;

public class Registration extends AppCompatActivity {
    private Button register;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private TextView dob;//date of birth
    private Button setDob;
    private EditText confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        register= findViewById(R.id.buttonRegister);
        firstName= findViewById(R.id.editTextName);
        lastName= findViewById(R.id.editTextSurname);
        email= findViewById(R.id.editTextEmail);
        password= findViewById(R.id.editTextNewPassword);
        confirmPassword= findViewById(R.id.editTextconfirmNewPassword);
        dob= findViewById(R.id.textViewDob);
        setDob = findViewById(R.id.buttonRegistrationDob);

        setDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment pickDate= new DatePickerFragment();
                showDatePickerDialog(v);
            }
        });

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

    public void showDatePickerDialog(View v) {//onClick for set time
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");


    }

}
