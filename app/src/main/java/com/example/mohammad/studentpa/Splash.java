package com.example.mohammad.studentpa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private TextView textv;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Animation splashAnim= AnimationUtils.loadAnimation(this, R.anim.mytransition);
        imageView= findViewById(R.id.imageViewLogo);//finds the logo
        imageView.startAnimation(splashAnim);//starts the animation transition
        final Intent intent= new Intent(this, Login.class);

        /**
         * A thread is a thread of execution in a program.
         * The Java Virtual Machine allows an application to have multiple
         * threads of execution running concurrently.
         */
        Thread timer = new Thread(){
            public void run(){//function to time how long the splash screen appears
                try{
                    sleep(1000);
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
