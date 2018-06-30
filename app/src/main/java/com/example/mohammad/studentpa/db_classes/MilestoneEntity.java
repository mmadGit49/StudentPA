package com.example.mohammad.studentpa.db_classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity
public class MilestoneEntity {
    @PrimaryKey(autoGenerate = true) //Obvious,and it shall autoGenerate
    @NonNull//Obvious, SQl annotation for not_null
    @ColumnInfo(name = "milestoneID")
//Not necessary unless you want column name to differ from variable
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
