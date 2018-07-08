package com.example.mohammad.studentpa.Reminders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mohammad.studentpa.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmReceiver class";
    
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class,
                        localData.get_hour(), localData.get_min(), localData.get_day(),
                        localData.get_month(), localData.get_year());
                return;
            }
        }
        //Trigger the notification
        NotificationScheduler.showNotification(context, MainActivity.class,
                "Organ Eyes", "Tap to view reminder");
    }


}
