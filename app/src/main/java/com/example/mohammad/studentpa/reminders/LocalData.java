package com.example.mohammad.studentpa.reminders;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalData {

    private static final String APP_SHARED_PREFS = "RemindMePref";

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    private static final String hour="hour";
    private static final String min="min";
    private static final String day="day";
    private static final String month="month";
    private static final String year="year";
    private static final String titleKey= "title";
    private static final String detailsKey= "details";
    private static final String dateKey= "date";
    private static final String amountKey= "amount";
    private static final String userKey = "user";
    private static final String nameKey= "name";


    public LocalData(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.apply();

    }

    // Settings Page Reminder Time (Hour)

    public int get_hour()
    {
        return appSharedPrefs.getInt(hour, 0);
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

    public void set_year(int m) {
        prefsEditor.putInt(year, m);
        prefsEditor.commit();
    }

    //Probably useless
    public void reset() {
        prefsEditor.clear();
        prefsEditor.commit();

    }

    //For notification
    public String get_title() {
        return appSharedPrefs.getString(titleKey, "placeholder");
    }

    public void set_title(String title) {
        prefsEditor.putString(titleKey, title);
        prefsEditor.commit();
    }

    //For notification
    public String get_details() {
        return appSharedPrefs.getString(detailsKey, "placeholder");
    }

    public void set_details(String details) {
        prefsEditor.putString(detailsKey, details);
        prefsEditor.commit();
    }

    //for spending
    public String get_date() {
        return appSharedPrefs.getString(dateKey, "placeholder");
    }

    public void set_date(String date) {
        prefsEditor.putString(dateKey, date);
        prefsEditor.commit();
    }

    public float get_amount()
    {
        return appSharedPrefs.getFloat(amountKey, 0);
    }

    public void set_amount(float m) {
        prefsEditor.putFloat(amountKey, m);
        prefsEditor.commit();
    }

    //To get current userID for reference
    public int get_user()
    {
        return appSharedPrefs.getInt(userKey, 0);
    }

    public void set_user(int user)
    {
        prefsEditor.putInt(userKey, user);
        prefsEditor.commit();
    }

    //For user
    public String get_name() {
        return appSharedPrefs.getString(nameKey, "User");
    }

    public void set_name(String name) {
        prefsEditor.putString(nameKey, name);
        prefsEditor.commit();
    }
}
