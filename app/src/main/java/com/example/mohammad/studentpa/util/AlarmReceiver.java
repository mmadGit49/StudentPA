package com.example.mohammad.studentpa.util;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mohammad.studentpa.reminders.ReminderInfo;

public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, localData.get_hour(),
                        localData.get_min(), localData.get_day(), localData.get_month(),
                        localData.get_year());
                return;
            }
        }

        Log.d(TAG, "onReceive: ");
        //Trigger the notification

        if (context != null) {
            LocalData localData = new LocalData(context);
            NotificationScheduler.showNotification(context, ReminderInfo.class,
                    localData.get_title(), localData.get_details());
            }

    }
}