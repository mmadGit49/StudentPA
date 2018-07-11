package com.example.mohammad.studentpa.db_classes.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Update;

import com.example.mohammad.studentpa.db_classes.Entities.ReminderEntity;

import java.util.List;

@Dao
public interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReminder(ReminderEntity reminderEntity);

    @Query("SELECT * FROM reminderentity")//Method to get all notes
    LiveData<List<ReminderEntity>> getAllReminders();

    @Query("SELECT reminderID, reminder_title FROM reminderentity")//Method to get all notes
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<ReminderEntity>> getAllReminderTitles();

    @Query("SELECT reminderID, reminder_details FROM reminderentity")//Method to get all notes
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<ReminderEntity>> getAllReminderDetails();

    @Query("SELECT reminderID, reminder_date FROM reminderentity")//Method to get all notes
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<ReminderEntity>> getAllReminderDates();

    @Query("SELECT reminderID, reminder_time FROM reminderentity")//Method to get all notes
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<ReminderEntity>> getAllReminderTimes();

    @Update
    void update(ReminderEntity reminderEntity);//Update the table

    @Delete
    void delete(ReminderEntity reminderEntity);//Delete the row passed to param, I guess?*/

}

