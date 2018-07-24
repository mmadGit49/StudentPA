package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mohammad.studentpa.db_classes.DAOs.ReminderDao;
import com.example.mohammad.studentpa.db_classes.entities.ReminderEntity;

import java.util.List;

public class ReminderRepository {
    private ReminderDao repoReminderDao;
    private LiveData<List<ReminderEntity>> allReminders;

    ReminderRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        repoReminderDao = db.reminderDao();
        allReminders = repoReminderDao.getAllReminders();

    }

    //For abstraction, we wrap the getters as follows
    public LiveData<List<ReminderEntity>> getAllReminders() {
        return allReminders;
    }

    public LiveData<List<ReminderEntity>> getAllRemindersbyID(int id) {
        return repoReminderDao.getAllRemindersFromID(id);
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

    //Insert and delete from database AsyncTask methods

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

