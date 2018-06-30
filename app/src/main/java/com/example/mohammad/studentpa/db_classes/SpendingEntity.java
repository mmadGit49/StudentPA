package com.example.mohammad.studentpa.db_classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity
public class SpendingEntity {
    @PrimaryKey(autoGenerate = true) //Obvious,and it shall autoGenerate
    @NonNull//Obvious
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
    private double spendAmount;
    public double getSpendAmount() {
        return spendAmount;
    }
    public void setSpendAmount(double spendAmount) {
        this.spendAmount = spendAmount;
    }

    @ColumnInfo (name = "spending_details")
    private String spendDetails;
    public String getSpendDetails() {
        return spendDetails;
    }
    public void setSpendDetails(String spendDetails) {
        this.spendDetails = spendDetails;
    }


}

