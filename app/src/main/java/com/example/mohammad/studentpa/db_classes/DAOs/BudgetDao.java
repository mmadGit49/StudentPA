package com.example.mohammad.studentpa.db_classes.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mohammad.studentpa.db_classes.entities.BudgetEntity;

import java.util.List;

@Dao
public interface BudgetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BudgetEntity budgetEntity);

    @Query("SELECT * FROM budgetentity")//Method to get all notes
    LiveData<List<BudgetEntity>> getAllBudgets();

    @Query("SELECT * FROM budgetentity WHERE userID = :userID")//Method to get all notes
    LiveData<List<BudgetEntity>> getAllBudgetsPerUser(int userID);

    @Query("SELECT * FROM budgetentity WHERE userID = :userID AND budget_date = :date")//Method to get all notes
    LiveData<List<BudgetEntity>> getAllBudgetsPerDate(int userID, String date);

    @Update
    void update(BudgetEntity budgetEntity);//Update the table

    @Delete
    void delete(BudgetEntity budgetEntity);//Delete the milestone passed to param, I guess?

}
