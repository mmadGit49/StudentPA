package com.example.mohammad.studentpa.Reminders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mohammad.studentpa.DatePickerFragment;
import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.TimePickerFragment;

//For the not_reminder_layout xml layout
public class TakeReminder extends AppCompatActivity{

    private static final String TAG = "TakeReminder";

    private EditText editTextReminderTitle;
    private EditText editTextReminderDetails;
    private TextView textViewSetReminderDate;
    private Button buttonSetReminderDate;
    private TextView textViewSetReminderTime;
    private Button buttonSetReminderTime;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_reminders);
        editTextReminderTitle = findViewById(R.id.editTextReminderTitle);
        editTextReminderDetails = findViewById(R.id.editTextReminderDetails);
        textViewSetReminderDate = findViewById(R.id.textViewReminderDate);
        buttonSetReminderDate = findViewById(R.id.buttonReminderDate);
        textViewSetReminderTime = findViewById(R.id.textViewReminderTime);
        buttonSetReminderTime = findViewById(R.id.buttonReminderTime);
        fab = findViewById(R.id.fab_save_reminder);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextReminderTitle != null && editTextReminderDetails != null){

                }
                finish();
            }
        });
    }

    public void showTimePickerDialog(View v) {//onCliok for set date
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }
    public void showDatePickerDialog(View v) {//onClick for set time
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}
