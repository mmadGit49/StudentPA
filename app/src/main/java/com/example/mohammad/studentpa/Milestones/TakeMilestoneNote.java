package com.example.mohammad.studentpa.Milestones;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.AppDatabase;
import com.example.mohammad.studentpa.db_classes.MilestoneEntity;

import java.util.ArrayList;

public class TakeMilestoneNote extends AppCompatActivity {

    private static final String TAG = "TakeMilestoneNote";

    private EditText editTextTitle;
    private EditText editTextDetails;
    private FloatingActionButton fab;
    private ArrayList<MilestoneEntity> titleNames= new ArrayList<>();
    private ArrayList<MilestoneEntity> notes = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_milestones);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDetails = findViewById(R.id.editTextMilestone);

        fab = findViewById(R.id.fab_save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//TO save the note. TODO: improve note save onClick
                if (editTextDetails != null || editTextTitle != null){
                    //getIncomingIntent();
                    String noteTitle = editTextTitle.getText().toString();
                    String noteDetails = editTextDetails.getText().toString();
                    setDetails(noteTitle, noteDetails);
                    finish();
                }
            }
        });


    }

    public void getIncomingIntent(){//From adapter class
        Log.d(TAG, "getIncomingIntent: started");
        //checks if intent has extras, may crash if condition not set
        if(getIntent().hasExtra("note_title") && getIntent().hasExtra("note_details")){
            Log.d(TAG, "getIncomingIntent: foundIntentExtras");
            //String noteTitle = getIntent().getStringExtra("note_title");
            //String noteDetails = getIntent().getStringExtra("note_details");
            //setDetails(noteTitle, noteDetails);
        }

    }

    public void setDetails(String noteTitle, String noteDetails){//Set the note as follows
        Log.d(TAG, "setDetails: started");
        //TODO: Save the note and refresh to display

//        titleNames.add(noteTitle);
    }


}
