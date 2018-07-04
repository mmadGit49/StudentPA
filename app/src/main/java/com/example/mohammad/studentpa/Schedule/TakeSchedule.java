package com.example.mohammad.studentpa.Schedule;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.Util.DatePickerFragment;
import com.example.mohammad.studentpa.Util.TimePickerFragment;

public class TakeSchedule extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener{

    private EditText editTextScheduleTitle;
    private TextView textViewScheduleDate;
    private TextView textViewScheduleTimeFrom;
    private TextView textViewScheduleDuration;
    private Button buttonScheduleDate;
    private Button buttonScheduleTimeFrom;
    private FloatingActionButton fab;
    private String timeFrom;

    String classTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_schedule);

        editTextScheduleTitle = findViewById(R.id.editTextScheduleTitle);
        textViewScheduleDate = findViewById(R.id.textViewScheduleDate);
        textViewScheduleTimeFrom = findViewById(R.id.textViewScheduleTimeFrom);
        textViewScheduleDuration = findViewById(R.id.textViewScheduleDuration);
        buttonScheduleDate = findViewById(R.id.buttonScheduleDate);
        buttonScheduleTimeFrom = findViewById(R.id.buttonScheduleTimeFrom);
        fab = findViewById(R.id.fab_save_schedule);

        buttonScheduleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        buttonScheduleTimeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeFromPickerDialog(v);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        classTitle = editTextScheduleTitle.getText().toString();

    }


    public void showTimeFromPickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String stringDate = dayOfMonth + " / " + month + " / " + year;
        textViewScheduleDate.setText(stringDate);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timeFrom = hourOfDay + " : " + String.format("%02d", minute);//just to format minute to 00
        textViewScheduleTimeFrom.setText(timeFrom);

    }

}
