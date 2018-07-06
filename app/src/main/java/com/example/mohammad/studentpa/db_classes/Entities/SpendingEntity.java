package com.example.mohammad.studentpa.db_classes.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class SpendingEntity {
    @PrimaryKey(autoGenerate = true) //Obvious,and it shall autoGenerate
    @ColumnInfo(name="spendingID")//Not necessary unless you want column name to differ from variable
    private int spendingID;
    public int getSpendingID() {
        return spendingID;
    }
    public void setSpendingID(int spendingID) {
        this.spendingID = spendingID;
    }

    @ColumnInfo (name = "spending_date")
    private String spendDate;
    public String getSpendDate() {
        return spendDate;
    }
    public void setSpendDate(String spendDate) {
        this.spendDate = spendDate;
    }

    @ColumnInfo (name = "spending_amount")
    private String[] spendAmount;
    public String[] getSpendAmount() {
        return spendAmount;
    }
    public void setSpendAmount(String[] spendAmount) {
        this.spendAmount = spendAmount;
    }

    @ColumnInfo (name = "spending_details")
    private String[] spendDetails;
    public String[] getSpendDetails() {
        return spendDetails;
    }
    public void setSpendDetails(String[] spendDetails) {
        this.spendDetails = spendDetails;
    }

    @ColumnInfo (name = "spending_total")
    private String spendTotal;
    public String getSpendTotal() {
        return spendTotal;
    }
    public void setSpendTotal(String spendTotal) {
        this.spendTotal = spendTotal;
    }
}

