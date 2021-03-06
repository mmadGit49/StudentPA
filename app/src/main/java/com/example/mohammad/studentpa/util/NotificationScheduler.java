package com.example.mohammad.studentpa.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.Calendar;
import java.util.Random;

import static android.content.Context.ALARM_SERVICE;


public class NotificationScheduler
{
    private static final int REMINDER_REQUEST_CODE = 0;
    public static final String TAG="NotificationScheduler";

    public static void setReminder(Context context,Class<?> cls,int hour, int min,
                                   int day, int month, int year)
    {
        Calendar calendar = Calendar.getInstance();

        Calendar setCalendar = Calendar.getInstance();
        setCalendar.set(Calendar.HOUR_OF_DAY, hour);
        setCalendar.set(Calendar.MINUTE, min);
        setCalendar.set(Calendar.SECOND, 0);
        setCalendar.set(Calendar.DAY_OF_MONTH, day);
        setCalendar.set(Calendar.MONTH, month);
        setCalendar.set(Calendar.YEAR, year);

        // cancel already scheduled reminders
        cancelReminder(context,cls);

        //if time is set on already elapsed time set reminder to next day
        if(setCalendar.before(calendar))
            setCalendar.add(Calendar.DATE,1);

        // Enable a receiver

        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);


        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REMINDER_REQUEST_CODE,
                intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.setExact(AlarmManager.RTC_WAKEUP, setCalendar.getTimeInMillis(), pendingIntent);

    }

    public static void cancelReminder(Context context,Class<?> cls) {
        // Disable a receiver

        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REMINDER_REQUEST_CODE,
                intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    public static void showNotification(Context context,Class<?> cls,String title,String content) {
        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(REMINDER_REQUEST_CODE,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationSetup notificationSetup = new NotificationSetup(context);
        NotificationCompat.Builder nb;
        nb = notificationSetup.getChannelNotification(title, content, pendingIntent);
        notificationSetup.getManager().notify(generateRandom(), nb.build());

    }

    //Generate random number so each notification has a unique ID,
    //This way we can display multiple notifications at a time
    private static int generateRandom(){
        Random random = new Random();
        return random.nextInt(9999 - 1000) + 1000;
    }

}