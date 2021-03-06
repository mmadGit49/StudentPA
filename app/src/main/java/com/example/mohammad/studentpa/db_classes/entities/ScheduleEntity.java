package com.example.mohammad.studentpa.db_classes.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity

public class ScheduleEntity {

    public ScheduleEntity(String scheduleTitle, String dayOfWeek, String timeFrom, String duration,
                          String date, int userID) {
        this.scheduleTitle = scheduleTitle;
        this.dayOfWeek = dayOfWeek;
        this.timeFrom = timeFrom;
        this.duration = duration;
        this.date = date;
        this.userID = userID;

    }

    @Ignore
    public ScheduleEntity(int scheduleID, String scheduleTitle, String dayOfWeek, String timeFrom,
                          String duration, String date, int userID) {
        this.scheduleID = scheduleID;
        this.scheduleTitle = scheduleTitle;
        this.dayOfWeek = dayOfWeek;
        this.timeFrom = timeFrom;
        this.duration = duration;
        this.date = date;
        this.userID = userID;

    }

    @PrimaryKey(autoGenerate = true) //Obvious,and it shall autoGenerate
    @NonNull//Obvious
    @ColumnInfo(name="scheduleID")//Not necessary unless you want column name to differ from variable
    private int scheduleID;
    public int getScheduleID() {
        return scheduleID;
    }
    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    @ColumnInfo (name = "schedule_title")
    private String scheduleTitle;
    public String getScheduleTitle() {
        return scheduleTitle;
    }
    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    @ColumnInfo (name = "day_of_week")
    private String dayOfWeek;
    public String getDayOfWeek() {
        return dayOfWeek;
    }
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @ColumnInfo (name = "time_from")
    private String timeFrom;
    public String getTimeFrom() {
        return timeFrom;
    }
    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    @ColumnInfo (name = "duration")
    private String duration;
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    @ColumnInfo (name = "optional_date")
    private String date;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    //************************************FOREIGN KEYS*********************************************
    @ForeignKey(entity = User.class, parentColumns = "userID", childColumns = "scheduleID",
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
