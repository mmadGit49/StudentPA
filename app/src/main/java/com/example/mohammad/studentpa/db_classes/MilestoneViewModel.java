package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mohammad.studentpa.db_classes.entities.MilestoneEntity;

import java.util.List;

public class MilestoneViewModel extends AndroidViewModel {

    private MilestoneRepository milestoneRepository;
//    private LiveData<List<MilestoneEntity>> allMilestoneTitles;
//    private LiveData<List<MilestoneEntity>> allMilestoneNotes;
    private LiveData<List<MilestoneEntity>> allMilestones;

    public MilestoneViewModel (Application application){
        super(application);
        milestoneRepository = new MilestoneRepository(application);
        allMilestones = milestoneRepository.getAllMilestones();
    }

    //The following getters are used to add EVEN MORE abstraction to the repository and DB

//    public LiveData<List<MilestoneEntity>> getAllMilestoneTitles() {
//        return allMilestoneTitles;
//    }
//    public LiveData<List<MilestoneEntity>> getAllMilestoneNotes() {
//        return allMilestoneNotes;
//    }
    public LiveData<List<MilestoneEntity>> getAllMilestones() {
        return allMilestones;
    }

    //Again, for abstraction
    public void insert(MilestoneEntity milestoneEntity) {
        milestoneRepository.insertMilestone(milestoneEntity);
    }

    public  void delete(MilestoneEntity milestoneEntity){
        milestoneRepository.deleteMilestone(milestoneEntity);
    }

    public  void update(MilestoneEntity milestoneEntity){
        milestoneRepository.updateMilestone(milestoneEntity);
    }

}
