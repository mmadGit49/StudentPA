package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MilestoneRepository {
    private MilestoneDao repoMilestoneDao;
    private LiveData<List<MilestoneEntity>> allMilestoneTitles;
    private LiveData<List<MilestoneEntity>> allMilestoneDetails;

    public MilestoneRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        repoMilestoneDao = db.milestoneDao();
        allMilestoneTitles = repoMilestoneDao.getAllMilestoneTitles();
        allMilestoneDetails = repoMilestoneDao.getAllMilestoneDetails();
    }

    LiveData<List<MilestoneEntity>> getAllMilestoneTitles (){
        return allMilestoneTitles;
    }

    LiveData<List<MilestoneEntity>> getAllMilestoneDetails (){
        return allMilestoneDetails;
    }

    public void insertMilestone (MilestoneEntity milestoneEntity) {
        new insertAsyncTask(repoMilestoneDao).execute(milestoneEntity);
    }

    private static class insertAsyncTask extends AsyncTask<MilestoneEntity, Void, Void> {

        private MilestoneDao mAsyncTaskDao;

        insertAsyncTask(MilestoneDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MilestoneEntity... params) {
            mAsyncTaskDao.insertMilestone(params[0]);
            return null;
        }
    }


}
