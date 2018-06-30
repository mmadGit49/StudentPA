package com.example.mohammad.studentpa.db_classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Time;

import io.reactivex.annotations.NonNull;

@Entity
public class ScheduleEntity {
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
    private Time timeFrom;
    public Time getTimeFrom() {
        return timeFrom;
    }
    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    @ColumnInfo (name = "time_to")
    private Time timeTo;
    public Time getTimeTo() {
        return timeTo;
    }
    public void setTimeTo(Time timeTo) {
        this.timeTo = timeTo;
    }

    @ColumnInfo (name = "optional_date")
    private String date;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
