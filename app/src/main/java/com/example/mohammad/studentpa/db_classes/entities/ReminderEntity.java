package com.example.mohammad.studentpa.db_classes.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity

public class ReminderEntity {

    public ReminderEntity(String reminderTitle, String reminderDetails, String reminderDate,
                          String reminderTime, int hourOfDay, int minute, int dayOfMonth,
                          int month, int year, int userID) {
        this.reminderTitle = reminderTitle;
        this.reminderDetails = reminderDetails;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        this.userID = userID;

    }

    @Ignore
    public ReminderEntity(int reminderID, String reminderTitle, String reminderDetails,
                          String reminderDate, String reminderTime, int hourOfDay, int minute,
                          int dayOfMonth, int month, int year, int userID) {
        this.reminderID = reminderID;
        this.reminderTitle = reminderTitle;
        this.reminderDetails = reminderDetails;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        this.userID = userID;
    }

    @PrimaryKey(autoGenerate = true) //Obvious,and it shall autoGenerate
    @ColumnInfo(name="reminderID")//Not necessary unless you want column name to differ from variable
    private int reminderID;
    public int getReminderID() {
        return reminderID;
    }
    public void setReminderID(int reminderID) {
        this.reminderID = reminderID;
    }

    @ColumnInfo(name = "reminder_title")
    private String reminderTitle;
    public String getReminderTitle() {
        return reminderTitle;
    }
    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }

    @ColumnInfo(name = "reminder_details")
    private String reminderDetails;
    public String getReminderDetails() {
        return reminderDetails;
    }
    public void setReminderDetails(String reminderDetails) {
        this.reminderDetails = reminderDetails;
    }

    @ColumnInfo(name ="reminder_date")
    private String reminderDate;
    public String getReminderDate() {
        return reminderDate;
    }
    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }


    @ColumnInfo(name = "reminder_time")
    private String reminderTime;
    public String getReminderTime() {
        return reminderTime;
    }
    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    //********************************************************************************************/
    //To "cheat" i am storing all ints independently for my reminders
    @ColumnInfo(name = "hour")
    private int hourOfDay;
    public int getHourOfDay() {
        return hourOfDay;
    }
    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    @ColumnInfo(name = "minute")
    private int minute;
    public int getMinute() {
        return minute;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }

    @ColumnInfo(name = "day")
    private int dayOfMonth;
    public int getDayOfMonth() {
        return dayOfMonth;
    }
    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @ColumnInfo(name = "month")
    private int month;
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }

    @ColumnInfo(name = "year")
    private int year;
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    //************************************FOREIGN KEYS*********************************************
    @ForeignKey(entity = User.class, parentColumns = "userID", childColumns = "reminderID",
            onUpdate = CASCADE, deferred = true)
    @ColumnInfo(name = "userID")
    private int userID;
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

}
