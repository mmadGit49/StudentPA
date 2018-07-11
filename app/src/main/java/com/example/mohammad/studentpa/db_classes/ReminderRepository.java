package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mohammad.studentpa.db_classes.DAOs.ReminderDao;
import com.example.mohammad.studentpa.db_classes.Entities.ReminderEntity;

import java.util.List;

public class ReminderRepository {
    private ReminderDao repoReminderDao;
    private LiveData<List<ReminderEntity>> allReminders;
    private LiveData<List<ReminderEntity>> allReminderTitles;
    private LiveData<List<ReminderEntity>> allReminderDetails;
    private LiveData<List<ReminderEntity>> allReminderDates;
    private LiveData<List<ReminderEntity>> allReminderTimes;


    public ReminderRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        repoReminderDao = db.reminderDao();
        allReminders = repoReminderDao.getAllReminders();
        allReminderTitles = repoReminderDao.getAllReminderTitles();
        allReminderDetails = repoReminderDao.getAllReminderDetails();
        allReminderDates = repoReminderDao.getAllReminderDates();
        allReminderTimes = repoReminderDao.getAllReminderTimes();

    }

    //For abstraction, we wrap the getters as follows
    public LiveData<List<ReminderEntity>> getAllReminders() {
        return allReminders;
    }

    LiveData<List<ReminderEntity>> getAllReminderTitles (){
        return allReminderTitles;
    }

    LiveData<List<ReminderEntity>> getAllReminderDetails (){
        return allReminderDetails;
    }

    LiveData<List<ReminderEntity>> getAllReminderDates (){
        return allReminderDates;
    }

    LiveData<List<ReminderEntity>> getAllReminderTimes (){
        return allReminderTimes;
    }

    public void insertReminder (ReminderEntity reminderEntity) {
        new insertAsyncTask(repoReminderDao).execute(reminderEntity);
    }

    public void deleteReminder (ReminderEntity reminderEntity){
        new deleteAsyncTask(repoReminderDao).execute(reminderEntity);
    }

    public void updateReminder (ReminderEntity reminderEntity){
        new updateAsyncTask(repoReminderDao).execute(reminderEntity);
    }

    //Insert and delete from database Asynctask methods

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

    private static class deleteAsyncTask extends AsyncTask<ReminderEntity, Void, Void> {

        private ReminderDao mAsyncTaskDao;

        deleteAsyncTask(ReminderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ReminderEntity... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<ReminderEntity, Void, Void> {

        private ReminderDao mAsyncTaskDao;

        updateAsyncTask(ReminderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ReminderEntity... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

}

