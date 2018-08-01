package com.example.mohammad.studentpa.db_classes.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity

public class SpendingEntity {

    public SpendingEntity(String spendDate, double spendAmount, String spendDetails,
                          int userID) {
        this.spendDate = spendDate;
        this.spendAmount = spendAmount;
        this.spendDetails = spendDetails;
        this.userID = userID;
    }

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


    //************************************FOREIGN KEYS*********************************************
    @ForeignKey(entity = User.class, parentColumns = "userID", childColumns = "spendingID",
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

