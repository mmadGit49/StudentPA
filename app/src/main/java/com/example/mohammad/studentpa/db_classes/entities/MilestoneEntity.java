package com.example.mohammad.studentpa.db_classes.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity

public class MilestoneEntity {

    public MilestoneEntity(String milestoneTitle, String milestoneDetails, int userID) {
        this.milestoneTitle = milestoneTitle;
        this.milestoneDetails = milestoneDetails;
        this.userID = userID;
    }

    @Ignore
    public MilestoneEntity(int milestoneID, String milestoneTitle, String milestoneDetails, int userID) {
        this.milestoneID = milestoneID;
        this.milestoneTitle = milestoneTitle;
        this.milestoneDetails = milestoneDetails;
        this.userID = userID;

    }

    @PrimaryKey(autoGenerate = true) //Obvious,and it shall autoGenerate
    @ColumnInfo(name = "milestoneID")
    private int milestoneID;
    public int getMilestoneID() {
        return milestoneID;
    }
    public void setMilestoneID(int milestoneID) {
        this.milestoneID = milestoneID;
    }

    @ColumnInfo(name = "milestone_title")
    private String milestoneTitle;
    public String getMilestoneTitle() {
        return milestoneTitle;
    }
    public void setMilestoneTitle(String milestoneTitle) {
        this.milestoneTitle = milestoneTitle;
    }

    @ColumnInfo(name = "milestone_details")
    private String milestoneDetails;
    public String getMilestoneDetails() {
        return milestoneDetails;
    }
    public void setMilestoneDetails(String milestoneDetails) {
        this.milestoneDetails = milestoneDetails;
    }

    //*************************************FOREIGN KEYS*********************************************
    @ForeignKey(entity = User.class, parentColumns = "userID", childColumns = "milestoneID",
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
