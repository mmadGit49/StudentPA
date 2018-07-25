package com.example.mohammad.studentpa.reminders;

//        if (intent.getAction() != null && context != null) {
//            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
//                // Set the alarm here.
//                Log.d(TAG, "onReceive: BOOT_COMPLETED");
//                LocalData localData = new LocalData(context);
//                NotificationScheduler.setReminder(context, AlarmReceiver.class,
//                        localData.get_hour(), localData.get_min(), localData.get_day(),
//                        localData.get_month(), localData.get_year());
////                int hour = reminder.getHourOfDay();
////                int min = reminder.getMinute();
////                int day = reminder.getDayOfMonth();
////                int month = reminder.getMonth();
////                int year = reminder.getYear();
////
////                NotificationScheduler.setReminder(context, AlarmReceiver.class, hour, min,
////                        day, month, year);
//
//                return;
//            }
//

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mohammad.studentpa.MainActivity;

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
            if (intent.hasExtra("remindID")) {
                NotificationScheduler.showNotification(context, MainActivity.class,
                        intent.getStringExtra("title"), intent.getStringExtra("details"));
            } else {
                NotificationScheduler.showNotification(context, MainActivity.class,
                        localData.get_title(), localData.get_details());
            }
        }else{
            NotificationScheduler.showNotification(context, MainActivity.class,
                   "Talk to me" , "Watch them now?");
        }

    }
}