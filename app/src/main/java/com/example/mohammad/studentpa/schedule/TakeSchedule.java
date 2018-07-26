package com.example.mohammad.studentpa.schedule;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.ScheduleViewModel;
import com.example.mohammad.studentpa.db_classes.entities.ScheduleEntity;
import com.example.mohammad.studentpa.reminders.LocalData;
import com.example.mohammad.studentpa.util.DatePickerFragment;
import com.example.mohammad.studentpa.util.TimePickerFragment;

import java.util.List;

public class TakeSchedule extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener{

    private EditText editTextScheduleTitle;
    private TextView textViewScheduleDate;
    private TextView textViewScheduleTimeFrom;
    private EditText editTextSchedDuration;

    private ScheduleViewModel scheduleViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_schedule);

        editTextScheduleTitle = findViewById(R.id.editTextScheduleTitle);
        textViewScheduleDate = findViewById(R.id.textViewScheduleDate);
        textViewScheduleTimeFrom = findViewById(R.id.textViewScheduleTimeFrom);
        editTextSchedDuration = findViewById(R.id.editTextScheduleDuration);
        Button buttonScheduleDate = findViewById(R.id.buttonScheduleDate);
        Button buttonScheduleTimeFrom = findViewById(R.id.buttonScheduleTimeFrom);
        FloatingActionButton fab = findViewById(R.id.fab_save_schedule);

        scheduleViewModel = ViewModelProviders.of(TakeSchedule.this).
                get(ScheduleViewModel.class);

        if (getIntent().hasExtra("schedID")) {
            String title = getIntent().getStringExtra("schedTitle");
            String time = getIntent().getStringExtra("schedTime");
            String date = getIntent().getStringExtra("schedDate");
            String duration = getIntent().getStringExtra("schedDuration");
            final int schedID = getIntent().getIntExtra("schedID", 0);


            editTextScheduleTitle.setText(title);
            textViewScheduleTimeFrom.setText(time);
            textViewScheduleDate.setText(date);
            editTextSchedDuration.setText(duration);

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
                    if( !TextUtils.isEmpty( editTextScheduleTitle.getText().toString() ) ){
                        scheduleViewModel.getAllScheduleDays(schedID).observe(TakeSchedule.this,
                                new Observer<List<ScheduleEntity>>() {
                            @Override
                            public void onChanged(@Nullable List<ScheduleEntity> scheduleEntities) {
                                if(scheduleEntities != null){
                                    String scheduleTitle = editTextScheduleTitle.getText().toString();
                                    String scheduleTime = textViewScheduleTimeFrom.getText().toString();
                                    String optionalDate = textViewScheduleDate.getText().toString();
                                    String duration = editTextSchedDuration.getText().toString();
                                    String day = scheduleEntities.get(0).getDayOfWeek();
                                    LocalData localData = new LocalData(TakeSchedule.this);
                                    int user = localData.get_user();
                                    //To save data to the db via the ViewModel
                                    scheduleViewModel.update(new ScheduleEntity(schedID, scheduleTitle,
                                            day, scheduleTime, duration, optionalDate, user));
                                    Toast.makeText(getApplicationContext(), "Item updated!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    finish();
                }
            });
        } else {
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
                    if( !TextUtils.isEmpty( editTextScheduleTitle.getText().toString() ) ){
                        String scheduleTitle = editTextScheduleTitle.getText().toString();
                        String scheduleTime = textViewScheduleTimeFrom.getText().toString();
                        String optionalDate = textViewScheduleDate.getText().toString();
                        String duration = editTextSchedDuration.getText().toString();
                        LocalData localData = new LocalData(TakeSchedule.this);
                        int user = localData.get_user();


                        Bundle dayBundle = getIntent().getExtras();
                        String day;
                        if(dayBundle != null) {
                            day = dayBundle.getString("dayOfWeek");
                            //To save data to the db via the ViewModel
                            scheduleViewModel.insert(new ScheduleEntity(scheduleTitle, day,
                                    scheduleTime, duration, optionalDate, user));
                        }
                        //To save data to the db via the ViewModel
                        Toast.makeText(getApplicationContext(), "Item saved!",
                                Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            });
        }

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
        String timeFrom = hourOfDay + " : " + String.format("%02d", minute);
        textViewScheduleTimeFrom.setText(timeFrom);

    }

}
