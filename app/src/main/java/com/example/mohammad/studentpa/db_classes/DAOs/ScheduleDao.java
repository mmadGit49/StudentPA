package com.example.mohammad.studentpa.db_classes.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Update;

import com.example.mohammad.studentpa.db_classes.entities.ScheduleEntity;

import java.util.List;

@Dao
public interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSchedule(ScheduleEntity scheduleEntity);

    @Query("SELECT * FROM scheduleentity")//Method to get all notes
    LiveData<List<ScheduleEntity>> getAllSchedules();

    @Query("SELECT scheduleID, schedule_title FROM scheduleentity")//Method to get all notes
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<ScheduleEntity>> getAllScheduleTitles();

    @Query("SELECT scheduleID, day_of_week FROM scheduleentity")//Method to get all notes
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<ScheduleEntity>> getAllScheduleDayOfWeeks();

    @Query("SELECT scheduleID, time_from FROM scheduleentity")//Method to get all notes
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<ScheduleEntity>> getAllScheduleTimes();

    @Query("SELECT scheduleID, optional_date FROM scheduleentity")//Method to get all notes
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<ScheduleEntity>> getAllScheduleOptionalDates();

    @Query("SELECT scheduleID, duration FROM scheduleentity")//Method to get all notes
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<ScheduleEntity>> getAllScheduleDurations();

    @Update
    void update(ScheduleEntity scheduleEntity);//Update the table

    @Delete
    void delete(ScheduleEntity scheduleEntity);//Delete the row passed to param, I guess?*/
}
