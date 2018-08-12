package com.example.mohammad.studentpa.reminders;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.ReminderViewModel;
import com.example.mohammad.studentpa.db_classes.entities.ReminderEntity;
import com.example.mohammad.studentpa.util.LocalData;

import java.util.List;

public class ReminderInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_info);

        final TextView textViewReminderTitle = findViewById(R.id.textViewReminderTitle);
        final TextView textViewReminderDetails = findViewById(R.id.textViewReminderDetails);
        final TextView textViewSetReminderDate = findViewById(R.id.textViewReminderDate);
        final TextView textViewSetReminderTime = findViewById(R.id.textViewReminderTime);
        FloatingActionButton fab = findViewById(R.id.fab_save_reminder);

        ReminderViewModel reminderViewModel = ViewModelProviders.of
                (ReminderInfo.this).get(ReminderViewModel.class);
        final LocalData localData = new LocalData(this);

        reminderViewModel.getAllRemindersByID(localData.get_user()).observe(this,
                new Observer<List<ReminderEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<ReminderEntity> reminderEntities) {
                        int remindID = localData.get_remindID();
                        textViewReminderTitle.setText(reminderEntities.get(remindID).getReminderTitle());
                        textViewReminderDetails.setText(reminderEntities.get(remindID).getReminderDetails());
                        textViewSetReminderDate.setText(reminderEntities.get(remindID).getReminderDate());
                        textViewSetReminderTime.setText(reminderEntities.get(remindID).getReminderTime());
                    }
            });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
