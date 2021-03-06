package com.example.mohammad.studentpa.util;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalData {

    private static final String APP_SHARED_PREFS = "RemindMePref";

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    //Reminders
    private static final String hour="hour";
    private static final String min="min";
    private static final String day="day";
    private static final String month="month";
    private static final String year="year";
    private static final String remindKey= "remindID";
    private static final String titleKey= "title";
    private static final String detailsKey= "details";
    //Budget
    private static final String amountKey= "amount";
    private static final String weekKey= "weekAmount";
    private static final String monthKey= "monthAmount";


    //Users
    private static final String userKey = "user";
    private static final String nameKey= "name";
    //Budget
    private static final String budgetKey= "budget";



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
    public void set_hour(int h) {
        prefsEditor.putInt(hour, h);
        prefsEditor.commit();
    }
    // Settings Page Reminder Time (Minutes)
    public int get_min()
    {
        return appSharedPrefs.getInt(min, 0);
    }
    public void set_min(int m) {
        prefsEditor.putInt(min, m);
        prefsEditor.commit();
    }
    // Settings Page Reminder Day
    public int get_day()
    {
        return appSharedPrefs.getInt(day, 0);
    }
    public void set_day(int m) {
        prefsEditor.putInt(day, m);
        prefsEditor.commit();
    }
    // Settings Page Reminder Month
    public int get_month()
    {
        return appSharedPrefs.getInt(month, 0);
    }
    public void set_month(int m) {
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

    //For notification

    public int get_remindID()
    {
        return appSharedPrefs.getInt(remindKey, 0);
    }
    public void set_remindID(int h) {
        prefsEditor.putInt(remindKey, h);
        prefsEditor.commit();
    }

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

    public float get_amount()
    {
        return appSharedPrefs.getFloat(amountKey, 0);
    }
    public void set_amount(float m) {
        prefsEditor.putFloat(amountKey, m);
        prefsEditor.commit();
    }

    public float get_weekAmount()
    {
        return appSharedPrefs.getFloat(weekKey, 0);
    }
    public void set_weekAmount(float m) {
        prefsEditor.putFloat(weekKey, m);
        prefsEditor.commit();
    }

    public float get_monthAmount()
    {
        return appSharedPrefs.getFloat(monthKey, 0);
    }
    public void set_monthAmount(float m) {
        prefsEditor.putFloat(monthKey, m);
        prefsEditor.commit();
    }

    //To get current userID for reference
    public int get_user()
    {
        return appSharedPrefs.getInt(userKey, 0);
    }
    public void set_user(int user) {
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

    //Set and retrieve daily budget
    public float get_budget()
    {
        return appSharedPrefs.getFloat(budgetKey, 0);
    }
    public void set_budget(float m) {
        prefsEditor.putFloat(budgetKey, m);
        prefsEditor.commit();
    }

}
