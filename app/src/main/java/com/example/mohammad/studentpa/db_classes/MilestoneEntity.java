package com.example.mohammad.studentpa.db_classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class MilestoneEntity {
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
