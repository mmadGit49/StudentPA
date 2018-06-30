package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ReminderRepository {
    private ReminderDao repoReminderDao;
    private LiveData<List<ReminderEntity>> allRemiderTitles;
    private LiveData<List<ReminderEntity>> allReminderDetails;
    private LiveData<List<ReminderEntity>> allReminderDates;
    private LiveData<List<ReminderEntity>> allReminderTimes;


    public ReminderRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        repoReminderDao = db.reminderDao();
        allRemiderTitles = repoReminderDao.getAllReminderTitles();
        allReminderDetails = repoReminderDao.getAllReminderDetails();
        allReminderDates = repoReminderDao.getAllReminderDates();
        allReminderTimes = repoReminderDao.getAllReminderTimes();

    }

    //For abstraction, we wrap the getters as follows
    LiveData<List<ReminderEntity>> getAllReminderTitles (){
        return allRemiderTitles;
    }

    LiveData<List<ReminderEntity>> getAllReminderDetails (){
        return allReminderDetails;
    }

    LiveData<List<ReminderEntity>> getAllReminderDates (){
        return allReminderDates;
    }

    LiveData<List<ReminderEntity>> getAllReminderTime (){
        return allReminderTimes;
    }

    public void insertReminder (ReminderEntity reminderEntity) {
        new insertAsyncTask(repoReminderDao).execute(reminderEntity);
    }

    private static class insertAsyncTask extends AsyncTask<ReminderEntity, Void, Void> {

        private ReminderDao mAsyncTaskDao;

        insertAsyncTask(ReminderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(ReminderEntity... reminderEntities) {
            mAsyncTaskDao.insertReminder(reminderEntities[0]);
            return null;
        }
    }
}
