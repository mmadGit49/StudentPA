package com.example.mohammad.studentpa.db_classes;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SpendingDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSpending(SpendingEntity spendingEntity);

    @Query("SELECT * FROM spendingentity")//Method to get all notes
    LiveData<List<SpendingEntity>> getAllSpendingItems();

    @Query("SELECT spendingID, spending_date FROM spendingentity")
    LiveData<List<SpendingEntity>> getAllSpendingDates();

    @Query("SELECT spendingID, spending_amount FROM spendingentity")
    LiveData<List<SpendingEntity>> getAllSpendingAmounts();

    @Query("SELECT spendingID, spending_details FROM spendingentity")
    LiveData<List<SpendingEntity>> getAllSpendingDetails();

    @Update
    void updateList(SpendingEntity spendingEntity);//Update the table

    @Delete
    void delete(SpendingEntity spendingEntity);//Delete the row passed to param, I guess?*/
}
