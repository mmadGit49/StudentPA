package com.example.mohammad.studentpa.db_classes.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class MilestoneEntity {

    public MilestoneEntity(String milestoneTitle, String milestoneDetails) {
        this.milestoneTitle = milestoneTitle;
        this.milestoneDetails = milestoneDetails;
    }

    @Ignore
    public MilestoneEntity(int milestoneID, String milestoneTitle, String milestoneDetails) {
        this.milestoneID = milestoneID;
        this.milestoneTitle = milestoneTitle;
        this.milestoneDetails = milestoneDetails;
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

}
