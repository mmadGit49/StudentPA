package com.example.mohammad.studentpa.db_classes.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mohammad.studentpa.db_classes.entities.ReminderEntity;

import java.util.List;

@Dao
public interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReminder(ReminderEntity reminderEntity);

    @Query("SELECT * FROM reminderentity")//Method to get all reminders
    LiveData<List<ReminderEntity>> getAllReminders();

    @Query("SELECT * FROM reminderentity WHERE userID = :userID")//Method to get all specific
    LiveData<List<ReminderEntity>> getAllRemindersFromID(int userID);

    @Update
    void update(ReminderEntity reminderEntity);//Update the table

    @Delete
    void delete(ReminderEntity reminderEntity);//Delete the row passed to param, I guess?*/

}


