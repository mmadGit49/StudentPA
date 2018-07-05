package com.example.mohammad.studentpa.db_classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ReminderEntity {

    public ReminderEntity(String reminderTitle, String reminderDetails, String reminderDate, String reminderTime) {
        this.reminderTitle = reminderTitle;
        this.reminderDetails = reminderDetails;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
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
}
