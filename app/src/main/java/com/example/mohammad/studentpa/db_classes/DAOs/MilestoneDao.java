package com.example.mohammad.studentpa.db_classes.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mohammad.studentpa.db_classes.entities.MilestoneEntity;

import java.util.List;

@Dao
public interface MilestoneDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMilestone(MilestoneEntity milestoneEntity);

    @Query("SELECT * FROM milestoneentity")//Method to get all notes
    LiveData<List<MilestoneEntity>> getAllMilestones();

    @Query("SELECT * FROM milestoneentity WHERE userID = :userID")//Method to get all notes
    LiveData<List<MilestoneEntity>> getAllMilestonesPerUser(int userID);

    @Update
    void updateList(MilestoneEntity milestoneEntity);//Update the table

    @Delete
    void delete(MilestoneEntity milestoneEntity);//Delete the milestone passed to param, I guess?
}
