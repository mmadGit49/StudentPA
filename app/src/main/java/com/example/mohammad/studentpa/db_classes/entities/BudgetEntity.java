package com.example.mohammad.studentpa.db_classes.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(indices = {@Index(value = {"budget_date"}, unique = true)})

public class BudgetEntity {

    public BudgetEntity(String budgetDate, float totalSpentDay, int userID) {
        this.budgetDate = budgetDate;
        this.totalSpentDay = totalSpentDay;
        this.userID = userID;
    }

    @Ignore
    public BudgetEntity(int budgetID, String budgetDate, float totalSpentDay, int userID) {
        this.budgetID = budgetID;
        this.budgetDate = budgetDate;
        this.totalSpentDay = totalSpentDay;
        this.userID = userID;
    }

    @PrimaryKey(autoGenerate = true) //Obvious,and it shall autoGenerate
    @ColumnInfo(name = "budgetID")
    private int budgetID;
    public int getBudgetID() {
        return budgetID;
    }
    public void setBudgetID(int budgetID) {
        this.budgetID = budgetID;
    }

    @ColumnInfo(name = "budget_date")
    private String budgetDate;
    public String getBudgetDate() {
        return budgetDate;
    }
    public void setBudgetDate(String budgetDate) {
        this.budgetDate = budgetDate;
    }

    @ColumnInfo(name = "budget_total")
    private float totalSpentDay;
    public float getTotalSpentDay() {
        return totalSpentDay;
    }
    public void setTotalSpentDay(float totalSpentDay) {
        this.totalSpentDay = totalSpentDay;
    }

    //*************************************FOREIGN KEYS********************************************/
    @ForeignKey(entity = User.class, parentColumns = "userID", childColumns = "budgetID",
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
