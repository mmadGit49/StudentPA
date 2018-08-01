package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mohammad.studentpa.db_classes.DAOs.ScheduleDao;
import com.example.mohammad.studentpa.db_classes.entities.ScheduleEntity;

import java.util.List;

public class ScheduleRepository {
    private ScheduleDao repoScheduleDao;
    private LiveData<List<ScheduleEntity>> allSchedules;

    ScheduleRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        repoScheduleDao = db.scheduleDao();
        allSchedules = repoScheduleDao.getAllSchedules();
    }

    public LiveData<List<ScheduleEntity>> getAllSchedules() {
        return allSchedules;
    }

    public LiveData<List<ScheduleEntity>> getAllSpecificDaySchedules(String dayOfWeek, int userID){
        return repoScheduleDao.getAllSchedulesSpecificDay(dayOfWeek, userID);
    }

    public LiveData<List<ScheduleEntity>> getAllSpecificDays(int schedID){
        return repoScheduleDao.getAllScheduleDays(schedID);
    }

    public void insertSchedule (ScheduleEntity scheduleEntity) {
        new insertAsyncTask(repoScheduleDao).execute(scheduleEntity);
    }

    public void deleteSchedule (ScheduleEntity scheduleEntity) {
        new deleteAsyncTask(repoScheduleDao).execute(scheduleEntity);
    }

    public void updateSchedule (ScheduleEntity scheduleEntity) {
        new updateAsyncTask(repoScheduleDao).execute(scheduleEntity);
    }

    private static class insertAsyncTask extends AsyncTask<ScheduleEntity, Void, Void> {

        private ScheduleDao mAsyncTaskDao;

        insertAsyncTask(ScheduleDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ScheduleEntity... params) {
            mAsyncTaskDao.insertSchedule(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<ScheduleEntity, Void, Void> {

        private ScheduleDao mAsyncTaskDao;

        deleteAsyncTask(ScheduleDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ScheduleEntity... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<ScheduleEntity, Void, Void> {

        private ScheduleDao mAsyncTaskDao;

        updateAsyncTask(ScheduleDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ScheduleEntity... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

}
