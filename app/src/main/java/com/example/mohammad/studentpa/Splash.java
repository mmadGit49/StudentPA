package com.example.mohammad.studentpa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private TextView textv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Animation splashAnim= AnimationUtils.loadAnimation(this, R.anim.mytransition);
        textv= findViewById(R.id.textViewLogo);//finds the logo
        textv.startAnimation(splashAnim);//starts the animation transition
        final Intent intent= new Intent(this, MainActivity.class);

        Thread timer = new Thread(){
            public void run(){//funtion to time how long the splash screen appears
                try{
                    sleep(1500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();

    }
}
