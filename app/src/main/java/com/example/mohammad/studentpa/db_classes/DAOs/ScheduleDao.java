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

    @Query("SELECT * FROM scheduleentity")
    LiveData<List<ScheduleEntity>> getAllSchedules();

    //For specific day, specific user
    @Query("SELECT * FROM scheduleentity WHERE day_of_week = :dayOfWeek AND userID = :userID")
    LiveData<List<ScheduleEntity>> getAllSchedulesSpecificDay(String dayOfWeek, int userID);

    //For specific day, to display respectively on fragments
    @Query("SELECT scheduleID, day_of_week, userID FROM scheduleentity WHERE scheduleID = :schedID")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<ScheduleEntity>> getAllScheduleDays(int schedID);

    @Update
    void update(ScheduleEntity scheduleEntity);//Update the table

    @Delete
    void delete(ScheduleEntity scheduleEntity);//Delete the row passed to param*/
}
