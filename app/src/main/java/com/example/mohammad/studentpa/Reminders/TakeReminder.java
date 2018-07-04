package com.example.mohammad.studentpa.Reminders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.Util.DatePickerFragment;
import com.example.mohammad.studentpa.Util.TimePickerFragment;

//For the not_reminder_layout xml layout
public class TakeReminder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener{

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

        buttonSetReminderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);

            }
        });

        fab = findViewById(R.id.fab_save_reminder);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( TextUtils.isEmpty( editTextReminderTitle.getText().toString() ) &&
                        TextUtils.isEmpty(editTextReminderDetails.getText().toString() )  ){
                    // setResult(RESULT_CANCELED, replyIntent);
                }else{
                    Bundle bundle = new Bundle();
                    String reminderTitle = editTextReminderTitle.getText().toString();
                    String reminderDetails = editTextReminderDetails.getText().toString();
                    String reminderDate= textViewSetReminderDate.getText().toString();
                    String reminderTime = textViewSetReminderTime.getText().toString();
                    if (bundle != null) {
                        bundle.putString("reminder_title", reminderTitle);
                        bundle.putString("reminder_details", reminderDetails);
                        bundle.putString("reminder_date", reminderDate);
                        bundle.putString("reminder_time", reminderTime);
                        Reminders reminders = new Reminders();
                        reminders.setArguments(bundle);
                    }
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

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String stringTime = hourOfDay + " : " + String.format("%02d", minute);
        textViewSetReminderTime.setText(stringTime);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String stringDate = dayOfMonth + " / " + month + " / " + year;
        textViewSetReminderDate.setText(stringDate);
    }
}
