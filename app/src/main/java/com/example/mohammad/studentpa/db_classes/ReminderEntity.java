package com.example.mohammad.studentpa.db_classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Time;

import io.reactivex.annotations.NonNull;

@Entity
public class ReminderEntity {
    @PrimaryKey(autoGenerate = true) //Obvious,and it shall autoGenerate
    @NonNull//Obvious
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

    @ColumnInfo(name ="reminderDate")
    private String reminderDate;
    public String getReminderDate() {
        return reminderDate;
    }
    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    @ColumnInfo(name = "reminderTime")
    private Time reminderTime;
    public Time getReminderTime() {
        return reminderTime;
    }
    public void setReminderTime(Time reminderTime) {
        this.reminderTime = reminderTime;
    }


}
