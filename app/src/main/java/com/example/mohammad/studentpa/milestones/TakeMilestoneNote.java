package com.example.mohammad.studentpa.milestones;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.MilestoneViewModel;
import com.example.mohammad.studentpa.db_classes.entities.MilestoneEntity;
import com.example.mohammad.studentpa.util.LocalData;

public class TakeMilestoneNote extends AppCompatActivity {
    private static final String TAG = "TakeMilestoneNote";
    private EditText editTextTitle;
    private EditText editTextDetails;

    private MilestoneViewModel milestoneViewModel;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_milestones);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDetails = findViewById(R.id.editTextMilestone);
        TextView textViewUser = findViewById(R.id.textViewCurrentUser);

        FloatingActionButton fab = findViewById(R.id.fab_save);

        milestoneViewModel = ViewModelProviders.of(TakeMilestoneNote.this).
                get(MilestoneViewModel.class);

        final LocalData localData = new LocalData(this);

        textViewUser.setText(localData.get_name());

        if(getIntent().hasExtra("mileID")){

            editTextTitle.setText(getIntent().getStringExtra("title"));
            editTextDetails.setText(getIntent().getStringExtra("details"));
            final int mileID = getIntent().getIntExtra("mileID" , 0);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //textUtils is an android class used on strings,apparently is preferred for
                    //exception handling purposes
                    Log.d(TAG, "onClick: save btn started: update");
                    if(!TextUtils.isEmpty( editTextDetails.getText().toString() ) &&
                            !TextUtils.isEmpty(editTextTitle.getText().toString() )  ){
                        String noteTitle = editTextTitle.getText().toString();
                        String noteDetails = editTextDetails.getText().toString();
                        int userID = localData.get_user();

                        //To update data to the db via the ViewModel
                        milestoneViewModel.update
                                (new MilestoneEntity(mileID, noteTitle, noteDetails, userID));

                        Toast.makeText(getApplicationContext(), "Milestone updated!",
                                Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            });
        }else{//No incoming intent, new note:
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: save btn started");
                    if( !TextUtils.isEmpty( editTextDetails.getText().toString() ) &&
                                !TextUtils.isEmpty(editTextTitle.getText().toString() )  ){

                        String noteTitle = editTextTitle.getText().toString();
                        String noteDetails = editTextDetails.getText().toString();
                        int userID = localData.get_user();
                        //To save data to the db via the ViewModel
                        milestoneViewModel.insert(new MilestoneEntity(noteTitle, noteDetails, userID));
                        Toast.makeText(getApplicationContext(), "Milestone saved!",
                                Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            });
        }

    }

}
