package com.example.mohammad.studentpa.Milestones;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.MilestoneEntity;
import com.example.mohammad.studentpa.db_classes.MilestoneViewModel;

public class TakeMilestoneNote extends AppCompatActivity {

    private static final String TAG = "TakeMilestoneNote";

    private EditText editTextTitle;
    private EditText editTextDetails;
    private FloatingActionButton fab;

    private MilestoneViewModel milestoneViewModel;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_milestones);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDetails = findViewById(R.id.editTextMilestone);
        milestoneViewModel = ViewModelProviders.of(TakeMilestoneNote.this).
                get(MilestoneViewModel.class);

        fab = findViewById(R.id.fab_save);
        //To save the note.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //textUtils is an android class used on strings,apparently is preferred for
                //exception handling purposes
                Log.d(TAG, "onClick: save btn started");
                if( TextUtils.isEmpty( editTextDetails.getText().toString() ) &&
                            TextUtils.isEmpty(editTextTitle.getText().toString() )  ){
                               // setResult(RESULT_CANCELED, replyIntent);
                }else{
                    String noteTitle = editTextTitle.getText().toString();
                    String noteDetails = editTextDetails.getText().toString();
                    /*Bundle bundle = new Bundle();

                    if (bundle != null) {
                        bundle.putString("note_title", noteTitle);
                        bundle.putString("note_details", noteDetails);
                        Milestones milestones = new Milestones();
                        milestones.setArguments(bundle);
                    }*/
                    //To save data to the db via the ViewModel
                    milestoneViewModel.insert(new MilestoneEntity(noteTitle, noteDetails));
                    Toast.makeText(getApplicationContext(), "Milestone saved!",
                            Toast.LENGTH_SHORT).show();
                }

                finish();

            }
        });

    }
}
