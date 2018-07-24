package com.example.mohammad.studentpa.db_classes.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mohammad.studentpa.db_classes.entities.ScheduleEntity;

import java.util.List;

@Dao
public interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSchedule(ScheduleEntity scheduleEntity);

    @Query("SELECT * FROM scheduleentity")
    LiveData<List<ScheduleEntity>> getAllSchedules();

    @Query("SELECT * FROM scheduleentity WHERE day_of_week = :dayOfWeek")//For specific day
    LiveData<List<ScheduleEntity>> getAllSchedulesSpecificDay(String dayOfWeek);

    @Update
    void update(ScheduleEntity scheduleEntity);//Update the table

    @Delete
    void delete(ScheduleEntity scheduleEntity);//Delete the row passed to param, I guess?*/
}
