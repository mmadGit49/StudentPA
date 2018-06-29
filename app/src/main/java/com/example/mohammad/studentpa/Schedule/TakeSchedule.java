package com.example.mohammad.studentpa.Schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.Reminders.DatePickerFragment;
import com.example.mohammad.studentpa.Reminders.TimePickerFragment;

public class TakeSchedule extends AppCompatActivity {

    private EditText editTextScheduleTitle;
    private TextView textViewScheduleDate;
    private TextView textViewScheduleTimeFrom;
    private TextView getTextViewScheduleTimeTo;
    private Button buttonScheduleDate;
    private Button buttonScheduleTimeFrom;
    private Button buttonScheduleTimeTo;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_spending);

        editTextScheduleTitle = findViewById(R.id.editTextScheduleTitle);
        textViewScheduleDate = findViewById(R.id.textViewScheduleDate);
        textViewScheduleTimeFrom = findViewById(R.id.textViewScheduleTimeFrom);
        getTextViewScheduleTimeTo = findViewById(R.id.textViewScheduleTimeTo);
        buttonScheduleDate = findViewById(R.id.buttonScheduleDate);
        buttonScheduleTimeFrom = findViewById(R.id.buttonScheduleTimeFrom);
        buttonScheduleTimeTo = findViewById(R.id.buttonScheduleTimeTo);
        FloatingActionButton fab = findViewById(R.id.fab_save_schedule);

        buttonScheduleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        buttonScheduleTimeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });
        buttonScheduleTimeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
