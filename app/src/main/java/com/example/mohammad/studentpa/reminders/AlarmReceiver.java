package com.example.mohammad.studentpa.reminders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mohammad.studentpa.db_classes.entities.ReminderEntity;

import java.util.ArrayList;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmReceiver class";

    @Override
    public void onReceive(Context context, Intent intent) {

        RemindersRecyclerViewAdapter adapter = new RemindersRecyclerViewAdapter(context,
                new ArrayList<ReminderEntity>());
        int position = adapter.getReminderPosition();
        ReminderEntity reminder = adapter.getReminderAtPosition(position);

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
//                LocalData localData = new LocalData(context);
//                NotificationScheduler.setReminder(context, AlarmReceiver.class,
//                        localData.get_hour(), localData.get_min(), localData.get_day(),
//                        localData.get_month(), localData.get_year());
                int hour = reminder.getHourOfDay();
                int min = reminder.getMinute();
                int day = reminder.getDayOfMonth();
                int month = reminder.getMonth();
                int year = reminder.getYear();

                NotificationScheduler.setReminder(context, AlarmReceiver.class, hour, min,
                        day, month, year);

                return;
            }

        }
        //Trigger the notification
        NotificationScheduler.showNotification(context, Reminders.class,
                reminder.getReminderTitle(), reminder.getReminderDetails());
        }
}