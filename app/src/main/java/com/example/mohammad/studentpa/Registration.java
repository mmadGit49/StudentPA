package com.example.mohammad.studentpa;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammad.studentpa.db_classes.UserViewModel;
import com.example.mohammad.studentpa.db_classes.entities.User;
import com.example.mohammad.studentpa.util.DatePickerFragment;

public class Registration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button register;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private TextView dob;//date of birth
    private Button setDob;
    private EditText confirmPassword;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        register= findViewById(R.id.buttonRegister);
        firstName= findViewById(R.id.editTextName);
        lastName= findViewById(R.id.editTextSurname);
        email= findViewById(R.id.editTextNewEmail);
        password= findViewById(R.id.editTextNewPassword);
        confirmPassword= findViewById(R.id.editTextconfirmNewPassword);
        dob= findViewById(R.id.textViewDob);
        setDob = findViewById(R.id.buttonRegistrationDob);

        userViewModel = ViewModelProviders.of(Registration.this).get(UserViewModel.class);

        setDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            View view;
            @Override
            public void onClick(View v) {
                boolean checkEmptyFname = TextUtils.isEmpty(firstName.getText());
                boolean checkEmptySurname = TextUtils.isEmpty(lastName.getText());
                boolean checkEmptyEmail = TextUtils.isEmpty(email.getText());
                boolean checkEmptyPassword = TextUtils.isEmpty(password.getText());
                boolean checkEmptyConfirm = TextUtils.isEmpty(confirmPassword.getText());
                boolean checkEmptyDob = TextUtils.isEmpty(dob.getText());

                if(checkEmptyFname || checkEmptySurname || checkEmptyEmail || checkEmptyPassword ||
                        checkEmptyConfirm || checkEmptyDob){
                    Toast.makeText(Registration.this, "Ensure all details are filled in!",
                            Toast.LENGTH_SHORT).show();

                }else{
                    String fName = firstName.getText().toString();
                    String surname= lastName.getText().toString();
                    String emailString = email.getText().toString();
                    String passWord = password.getText().toString();
                    String dateOfBirth = dob.getText().toString();
                    //check if correctly entered password
                    if(!passWord.equals(confirmPassword.getText().toString())){
                        Toast.makeText(Registration.this, "Passwords do not match!",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        userViewModel.insert(new User(fName, surname, emailString, passWord, dateOfBirth));
                        Toast.makeText(Registration.this, "Successfully Registered!",
                                Toast.LENGTH_SHORT).show();
                        startLogin(view);
                    }

                }
            }
        });

    }

    public void startLogin(View view){
        Intent loginIntent= new Intent(this, Login.class);
        startActivity(loginIntent);
        finish();//prevents user from returning to this screen
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date= dayOfMonth + " / " + month + " / " + year;
        dob.setText(date);
    }

    public void showDatePickerDialog(View v) {//onClick for set time
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

}
