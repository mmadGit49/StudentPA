package com.example.mohammad.studentpa;

import android.arch.persistence.room.Room;
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
    //to create an instance of the database
    private AppDatabase db= Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "App_data").build();


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
        gender= findViewById(R.id.spinnerGender);
        dob= findViewById(R.id.editTextDob);

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
