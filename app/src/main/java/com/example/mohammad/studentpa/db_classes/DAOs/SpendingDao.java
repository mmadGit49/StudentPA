package com.example.mohammad.studentpa.db_classes.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Update;

import com.example.mohammad.studentpa.db_classes.entities.SpendingEntity;

import java.util.List;

@Dao
public interface SpendingDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSpending(SpendingEntity spendingEntity);

    @Query("SELECT * FROM spendingentity")//Method to get all notes
    LiveData<List<SpendingEntity>> getAllSpendingItems();

    @Query("SELECT * FROM spendingentity WHERE spending_date = :date")//Method to get all notes
    LiveData<List<SpendingEntity>> getAllSpendingItemsByDate(String date);

    @Query("SELECT spendingID, spending_date FROM spendingentity")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<SpendingEntity>> getAllSpendingDates();

    @Query("SELECT spendingID, spending_amount FROM spendingentity")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<SpendingEntity>> getAllSpendingAmounts();

    @Query("SELECT spendingID, spending_details FROM spendingentity")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<List<SpendingEntity>> getAllSpendingDetails();

    @Update
    void update(SpendingEntity spendingEntity);//Update the table

    @Delete
    void delete(SpendingEntity spendingEntity);//Delete the row passed to param, I guess?*/
}
