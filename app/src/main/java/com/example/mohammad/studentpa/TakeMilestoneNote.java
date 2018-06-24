package com.example.mohammad.studentpa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

public class TakeMilestoneNote extends AppCompatActivity {

    private static final String TAG = "TakeMilestoneNote";

    private EditText editTextTitle = findViewById(R.id.editTextTitle);
    private EditText editTextDetails = findViewById(R.id.editTextMilestone);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_milestones);

        //getIncomingIntent();

    }

    public void getIncomingIntent(){//From adapter class
        Log.d(TAG, "getIncomingIntent: started");
        //checks if intent has extras, may crash if condition not set
        if(getIntent().hasExtra("note_title") && getIntent().hasExtra("note_details")){
            Log.d(TAG, "getIncomingIntent: foundIntentExtras");
/*
            String noteTitle = getIntent().getStringExtra("note_title");
            String noteDetails = getIntent().getStringExtra("note_details");
            setDetails(noteTitle, noteDetails);*/
        }

    }

    public void setDetails(String noteTitle, String imageName){//Set the note as follows
        Log.d(TAG, "setDetails: started");

        /*TextView textViewTitle = findViewById(R.id.textViewTitle);
        TextView textViewNoteDetails = findViewById(R.id.textViewMilestone);

        String title = editTextTitle.getText().toString();
        String details = editTextDetails.getText().toString();

        textViewTitle.setText(title);
        textViewNoteDetails.setText(details);*/

    }
}
