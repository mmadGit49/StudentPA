package com.example.mohammad.studentpa;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mohammad.studentpa.db_classes.UserViewModel;
import com.example.mohammad.studentpa.db_classes.entities.User;
import com.example.mohammad.studentpa.reminders.LocalData;

public class Login extends AppCompatActivity {
    private EditText username;
    private EditText password;

    private UserViewModel userViewModel;

    private static final String TAG = "Login Class started";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button register = findViewById(R.id.buttonSignUp);
        final Button login = findViewById(R.id.buttonLogin);
        username= findViewById(R.id.editTextEmail);
        password= findViewById(R.id.editTextPassword);

        register.setOnClickListener(new View.OnClickListener() {//method to start next activity
            View regView;
            @Override
            public void onClick(View v) {//on button "Sign up" clicked
                startRegister(regView);//will start registration activity
            }
        });

        //onClick Listener,as stated, to listen for button clicks
        login.setOnClickListener(new View.OnClickListener() {
            View view;
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText()) || TextUtils.isEmpty(password.getText())){
                    Toast.makeText(Login.this, "Missing Required Fields!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    final String emailEntered = username.getText().toString();
                    final String passwordString = password.getText().toString();

                    userViewModel = ViewModelProviders.of(Login.this).get(UserViewModel.class);

                    userViewModel.getCredentials(emailEntered, passwordString).observe(Login.this, new Observer<User>() {
                        @Override
                        public void onChanged(@Nullable User user) {
                            if (user != null) {
                                String checkEmail = user.getEmail();
                                String checkPw = user.getPassword();
                                if (checkEmail.equals(emailEntered)){
                                    if(checkPw.equals(passwordString)){
//                                        SavedUserLogin.setUserName(Login.this, checkEmail);
                                        startMain(view, checkEmail);
                                        Toast.makeText(Login.this, "Welcome " + user.getFirstName(),
                                                Toast.LENGTH_SHORT).show();
                                    }

                                }else{
                                    Toast.makeText(Login.this, "Username or password incorrect",
                                            Toast.LENGTH_SHORT).show();
                                }
                                LocalData localData = new LocalData(Login.this);
                                int userID = user.getUserID();
                                localData.set_user(userID);
                                Log.d(TAG, "onChanged: UserID set");

                            }else{
                                Toast.makeText(Login.this, "Username or password incorrect",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }

    public void startMain(View view, String email){
        Intent mainIntent= new Intent(this, MainActivity.class);
        mainIntent.putExtra("user", email);
        startActivity(mainIntent);
        finish();//prevents user from returning to this screen
    }

    public void startRegister(View view){
        Intent register= new Intent(this, Registration.class);
        startActivity(register);
        finish();//prevents user from returning to this screen
    }

}
