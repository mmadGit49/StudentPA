package com.example.mohammad.studentpa.Reminders;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalData {

    private static final String APP_SHARED_PREFS = "RemindMePref";

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    private static final String reminderStatus="reminderStatus";
    private static final String hour="hour";
    private static final String min="min";
    private static final String day="day";
    private static final String month="month";
    private static final String year="year";
    private String title;


    public LocalData(Context context)
    {
        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    // Settings Page Set Reminder

    public boolean getReminderStatus()
    {
        return appSharedPrefs.getBoolean(reminderStatus, false);
    }

    public void setReminderStatus(boolean status)
    {
        prefsEditor.putBoolean(reminderStatus, status);
        prefsEditor.commit();
    }

    // Settings Page Reminder Time (Hour)

    public int get_hour()
    {
        return appSharedPrefs.getInt(hour, 20);
    }

    public void set_hour(int h)
    {
        prefsEditor.putInt(hour, h);
        prefsEditor.commit();
    }

    // Settings Page Reminder Time (Minutes)

    public int get_min()
    {
        return appSharedPrefs.getInt(min, 0);
    }

    public void set_min(int m)
    {
        prefsEditor.putInt(min, m);
        prefsEditor.commit();
    }

    // Settings Page Reminder Day

    public int get_day()
    {
        return appSharedPrefs.getInt(day, 0);
    }

    public void set_day(int m)
    {
        prefsEditor.putInt(day, m);
        prefsEditor.commit();
    }

    // Settings Page Reminder Month

    public int get_month()
    {
        return appSharedPrefs.getInt(month, 0);
    }

    public void set_month(int m)
    {
        prefsEditor.putInt(month, m);
        prefsEditor.commit();
    }

    // Settings Page Reminder Year

    public int get_year()
    {
        return appSharedPrefs.getInt(year, 0);
    }

    public void set_year(int m)
    {
        prefsEditor.putInt(year, m);
        prefsEditor.commit();
    }

    public void reset()
    {
        prefsEditor.clear();
        prefsEditor.commit();

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
