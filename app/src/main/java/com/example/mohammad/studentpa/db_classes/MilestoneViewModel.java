package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class MilestoneViewModel extends AndroidViewModel {

    private MilestoneRepository milestoneRepository;
    private LiveData<List<MilestoneEntity>> allMilestoneTitles;
    private LiveData<List<MilestoneEntity>> allMilestoneNotes;

    public MilestoneViewModel (Application application){
        super(application);
        milestoneRepository = new MilestoneRepository(application);
        allMilestoneTitles = milestoneRepository.getAllMilestoneTitles();
        allMilestoneNotes = milestoneRepository.getAllMilestoneDetails();

    }

    //The following getters are used to add EVEN MORE abstraction to the repository and DB


    public LiveData<List<MilestoneEntity>> getAllMilestoneTitles() {
        return allMilestoneTitles;
    }

    public LiveData<List<MilestoneEntity>> getAllMilestoneNotes() {
        return allMilestoneNotes;
    }

    //Again, for abstraction
    public void insert(MilestoneEntity milestoneEntity) {
        milestoneRepository.insertMilestone(milestoneEntity);

    }

}
