package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mohammad.studentpa.db_classes.DAOs.MilestoneDao;
import com.example.mohammad.studentpa.db_classes.Entities.MilestoneEntity;

import java.util.List;

public class MilestoneRepository {
    private MilestoneDao repoMilestoneDao;
    private LiveData<List<MilestoneEntity>> allMilestoneTitles;
    private LiveData<List<MilestoneEntity>> allMilestoneDetails;
    private LiveData<List<MilestoneEntity>> allMilestones;


    public MilestoneRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        repoMilestoneDao = db.milestoneDao();
        allMilestoneTitles = repoMilestoneDao.getAllMilestoneTitles();
        allMilestoneDetails = repoMilestoneDao.getAllMilestoneDetails();
        allMilestones = repoMilestoneDao.getAllMilestones();
    }

    public LiveData<List<MilestoneEntity>> getAllMilestoneTitles (){
        return allMilestoneTitles;
    }
    public LiveData<List<MilestoneEntity>> getAllMilestoneDetails (){
        return allMilestoneDetails;
    }
    public LiveData<List<MilestoneEntity>> getAllMilestones() {
        return allMilestones;
    }

    public void insertMilestone (MilestoneEntity milestoneEntity) {
        //starts the async task which, in this case, inserts a milestone to the db
        new insertAsyncTask(repoMilestoneDao).execute(milestoneEntity);
    }

    public void deleteMilestone (MilestoneEntity milestoneEntity) {
        //starts the async task which, in this case, deletes a milestone to the db
        new deleteAsyncTask(repoMilestoneDao).execute(milestoneEntity);
    }

    public void updateMilestone (MilestoneEntity milestoneEntity) {
        //starts the async task which, in this case, updates a milestone to the db
        new deleteAsyncTask(repoMilestoneDao).execute(milestoneEntity);
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

    private static class deleteAsyncTask extends AsyncTask<MilestoneEntity, Void, Void> {

        private MilestoneDao mAsyncTaskDao;

        deleteAsyncTask(MilestoneDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MilestoneEntity... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<MilestoneEntity, Void, Void> {

        private MilestoneDao mAsyncTaskDao;

        updateAsyncTask(MilestoneDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MilestoneEntity... params) {
            mAsyncTaskDao.updateList(params[0]);
            return null;
        }
    }

}
