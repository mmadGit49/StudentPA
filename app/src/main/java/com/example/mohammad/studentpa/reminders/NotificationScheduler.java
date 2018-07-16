package com.example.mohammad.studentpa.reminders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.Calendar;

public class NotificationScheduler {
    public static final String TAG="NotificationScheduler";
    public static final int DAILY_REMINDER_REQUEST_CODE=100;

    public static void setReminder(Context context, Class<?> cls,
                                   int hour, int min, int day, int month, int year)
    {
//        Calendar calendar = Calendar.getInstance();
        Calendar setCalendar = Calendar.getInstance();

        setCalendar.set(Calendar.HOUR_OF_DAY, hour);
        setCalendar.set(Calendar.MINUTE, min);
        setCalendar.set(Calendar.SECOND, 0);
        setCalendar.set(Calendar.DAY_OF_MONTH, day);
        setCalendar.set(Calendar.MONTH, month);
        setCalendar.set(Calendar.YEAR, year);

        // cancel already scheduled reminders
//        cancelReminder(context, cls);

//        if(setCalendar.before(calendar)){
//            setCalendar.add(Calendar.DATE,1);
//        }

        // Enable a receiver
        //Because AndroidManifests specifies it as android:enabled=”false”, we do it explicitly
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                DAILY_REMINDER_REQUEST_CODE, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //if(am != null){
            am.setExact(AlarmManager.RTC_WAKEUP, setCalendar.getTimeInMillis(), pendingIntent);
//        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, setCalendar.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, pendingIntent);

        //}

    }

    public static void showNotification(Context context, Class<?> cls, String title, String content) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "showNotification: Notification");
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, title, importance);
            channel.setDescription(content);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }

//            notificationManager = NotificationManagerCompat.from(context);
            notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(DAILY_REMINDER_REQUEST_CODE, notification);

        }else{*/
     /*       Log.d(TAG, "showNotification: Notification");
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Intent notificationIntent = new Intent(context, cls);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(cls);
            stackBuilder.addNextIntent(notificationIntent);

            PendingIntent pendingIntent = stackBuilder.getPendingIntent(DAILY_REMINDER_REQUEST_CODE,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            //Setting up the notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            Notification notification = builder.setContentTitle(title)
                    .setContentText(content)
                    .setSound(alarmSound)
                    .setSmallIcon(R.mipmap.ic_icon_image)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();

//            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(DAILY_REMINDER_REQUEST_CODE, notification);*/

        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(DAILY_REMINDER_REQUEST_CODE,
                PendingIntent.FLAG_UPDATE_CURRENT);

        ReminderNotification reminderNotification = new ReminderNotification(context);
            NotificationCompat.Builder nb;
            nb = reminderNotification.getChannelNotification(title, content);
            reminderNotification.getManager().notify(DAILY_REMINDER_REQUEST_CODE,
                    nb.setContentIntent(pendingIntent).build());

    }

    public static void cancelReminder(Context context,Class<?> cls)
    {   // Disable a receiver
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                DAILY_REMINDER_REQUEST_CODE, intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //if (am != null) {
            am.cancel(pendingIntent);
        //}
        pendingIntent.cancel();
    }


}