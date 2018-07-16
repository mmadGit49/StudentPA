package com.example.mohammad.studentpa.reminders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import com.example.mohammad.studentpa.db_classes.ReminderViewModel;
import com.example.mohammad.studentpa.db_classes.entities.ReminderEntity;
import com.example.mohammad.studentpa.util.DatePickerFragment;
import com.example.mohammad.studentpa.util.TimePickerFragment;

//For the not_reminder_layout xml layout
public class TakeReminder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener{

    private static final String TAG = "TakeReminder";

    private EditText editTextReminderTitle;
    private EditText editTextReminderDetails;
    private TextView textViewSetReminderDate;
    private TextView textViewSetReminderTime;
    private FloatingActionButton fab;

    private ReminderViewModel reminderViewModel;
    private LocalData localData;
//    private Bundle bundles = getIntent().getExtras();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_reminders);

        editTextReminderTitle = findViewById(R.id.editTextReminderTitle);
        editTextReminderDetails = findViewById(R.id.editTextReminderDetails);
        textViewSetReminderDate = findViewById(R.id.textViewReminderDate);
        Button buttonSetReminderDate = findViewById(R.id.buttonReminderDate);
        textViewSetReminderTime = findViewById(R.id.textViewReminderTime);
        Button buttonSetReminderTime = findViewById(R.id.buttonReminderTime);

        localData = new LocalData(getApplicationContext());

        reminderViewModel = ViewModelProviders.of
                (TakeReminder.this).get(ReminderViewModel.class);

        if (getIntent().hasExtra("remindID")) {
            String reminderTitle = getIntent().getStringExtra("remindTitle");
            String reminderDetails = getIntent().getStringExtra("remindDetails");
            String reminderDate= getIntent().getStringExtra("remindDate");
            String reminderTime = getIntent().getStringExtra("remindTime");

            editTextReminderTitle.setText(reminderTitle);
            editTextReminderDetails.setText(reminderDetails);
            textViewSetReminderDate.setText(reminderDate);
            textViewSetReminderTime.setText(reminderTime);

            buttonSetReminderDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog(v);
                }
            });

            buttonSetReminderTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTimePickerDialog(v);
                }
            });

            fab = findViewById(R.id.fab_save_reminder);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( !TextUtils.isEmpty( editTextReminderTitle.getText().toString() ) &&
                            !TextUtils.isEmpty(editTextReminderDetails.getText().toString() )  ){

                        String reminderTitle = editTextReminderTitle.getText().toString();
                        String reminderDetails = editTextReminderDetails.getText().toString();
                        String reminderDate= textViewSetReminderDate.getText().toString();
                        String reminderTime = textViewSetReminderTime.getText().toString();

                        int remindID = getIntent().getIntExtra("remindID", 0);

//                        if(!bundles.isEmpty()){

//                            int hour = getIntent().getIntExtra("hour", 0);
//                            int min = getIntent().getIntExtra("minute", 0);
//                            int day = getIntent().getIntExtra("day", 0);
//                            int month = getIntent().getIntExtra("month", 0);
//                            int year = getIntent().getIntExtra("year", 0);

                            int hour = localData.get_hour();
                            int min = localData.get_min();
                            int day = localData.get_day();
                            int month = localData.get_month();
                            int year = localData.get_year();

                            reminderViewModel.update(new ReminderEntity
                                    (remindID, reminderTitle, reminderDetails, reminderDate,
                                            reminderTime, hour, min, day, month, year));
//                        }else{
//                            Log.d(TAG, "onClick: No incoming time Intent found!");
//
//                        }

//                        localData.setTitle(reminderTitle);//For notification

                        Toast.makeText(getApplicationContext(),
                                "Reminder updated!", Toast.LENGTH_SHORT).show();

                    }
                    finish();
                }
            });

        } else {
            buttonSetReminderDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog(v);
                }
            });

            buttonSetReminderTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTimePickerDialog(v);
                }
            });

            fab = findViewById(R.id.fab_save_reminder);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( !TextUtils.isEmpty( editTextReminderTitle.getText().toString() ) &&
                            !TextUtils.isEmpty(editTextReminderDetails.getText().toString() )  ){

                        String reminderTitle = editTextReminderTitle.getText().toString();
                        String reminderDetails = editTextReminderDetails.getText().toString();
                        String reminderDate= textViewSetReminderDate.getText().toString();
                        String reminderTime = textViewSetReminderTime.getText().toString();

//                        if(getIntent().hasExtra("hour")){
//                            int hour = getIntent().getIntExtra("hour", 0);
//                            int min = getIntent().getIntExtra("minute", 0);
//                            int day = getIntent().getIntExtra("day", 0);
//                            int month = getIntent().getIntExtra("month", 0);
//                            int year = getIntent().getIntExtra("year", 0);

                            int hour = localData.get_hour();
                            int min = localData.get_min();
                            int day = localData.get_day();
                            int month = localData.get_month();
                            int year = localData.get_year();

                            reminderViewModel.insert(new ReminderEntity
                                    (reminderTitle, reminderDetails, reminderDate, reminderTime,
                                            hour, min, day, month, year));

//                        }else{
//                            Log.d(TAG, "onClick: No incoming time Intent found!");
//
//                        }

//                        localData.setTitle(reminderTitle);
                        Toast.makeText(getApplicationContext(),
                                "Reminder saved!", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            });
        }

    }

    public void showTimePickerDialog(View v) {//onClick for set date
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }
    public void showDatePickerDialog(View v) {//onClick for set time
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String stringTime = hourOfDay + " : " + String.format("%02d", minute);
        textViewSetReminderTime.setText(stringTime);

//        Intent timeIntent = new Intent(this, TakeReminder.class);
//        Bundle bundle = new Bundle();
//        bundle.putInt("hour", hourOfDay);
//        bundle.putInt("minute", minute);
//        timeIntent.putExtras(bundle);
//        timeIntent.putExtra("hour", hourOfDay);
//        timeIntent.putExtra("minute", minute);
//
        localData.set_hour(hourOfDay);
        localData.set_min(minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String stringDate = dayOfMonth + " / " + month + " / " + year;
        textViewSetReminderDate.setText(stringDate);
//        Intent dateIntent = new Intent(this, TakeReminder.class);
//        Bundle bundle = new Bundle();
//        bundle.putInt("day", dayOfMonth);
//        bundle.putInt("month", month);
//        bundle.putInt("year", year);
//        dateIntent.putExtras(bundle);
//
//        dateIntent.putExtra("day", dayOfMonth);
//        dateIntent.putExtra("month", month);
//        dateIntent.putExtra("year", year);

        localData.set_day(dayOfMonth);
        localData.set_month(month);
        localData.set_year(year);
    }

}
