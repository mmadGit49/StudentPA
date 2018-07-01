package com.example.mohammad.studentpa.Milestones;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.mohammad.studentpa.R;

public class TakeMilestoneNote extends AppCompatActivity {

    private static final String TAG = "TakeMilestoneNote";

    private EditText editTextTitle;
    private EditText editTextDetails;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_milestones);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDetails = findViewById(R.id.editTextMilestone);

        fab = findViewById(R.id.fab_save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//TO save the note. TODO: improve note save onClick\
                //textUtils is an android class used on strings,apparently is preferred for
                //exception handling purposes
                Intent replyIntent = new Intent();

                if( TextUtils.isEmpty( editTextDetails.getText().toString() ) &&
                            TextUtils.isEmpty(editTextTitle.getText().toString() )  ){
                                setResult(RESULT_CANCELED, replyIntent);
                }else{
                    String noteTitle = editTextTitle.getText().toString();
                    String noteDetails = editTextDetails.getText().toString();
                    replyIntent.putExtra("note_title", noteTitle);
                    replyIntent.putExtra("note_details", noteDetails);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();

                /*if (editTextDetails != null || editTextTitle != null){
                    //getIncomingIntent();
                    String noteTitle = editTextTitle.getText().toString();
                    String noteDetails = editTextDetails.getText().toString();
                    setDetails(noteTitle, noteDetails);
                    finish();
                }*/
            }
        });

        getIncomingIntent();


    }

    public void getIncomingIntent(){//From adapter class
        Log.d(TAG, "getIncomingIntent: started");
        //checks if intent has extras, may crash if condition not set
        if(getIntent().hasExtra("note_title") && getIntent().hasExtra("note_details")){
            Log.d(TAG, "getIncomingIntent: foundIntentExtras");
            String noteTitle = getIntent().getStringExtra("note_title");
            String noteDetails = getIntent().getStringExtra("note_details");
            setDetails(noteTitle, noteDetails);
        }
    }

    public void setDetails(String noteTitle, String noteDetails){//Set the note as follows
        Log.d(TAG, "setDetails: started");
        //TODO: Save the note and refresh to display

        editTextTitle.setText(noteTitle);
        editTextDetails.setText(noteDetails);


    }




}
