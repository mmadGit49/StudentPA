package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mohammad.studentpa.db_classes.DAOs.ScheduleDao;
import com.example.mohammad.studentpa.db_classes.Entities.ScheduleEntity;

import java.util.List;

public class ScheduleRepository {
    private ScheduleDao repoScheduleDao;
    private LiveData<List<ScheduleEntity>> allScheduleTitles;
    private LiveData<List<ScheduleEntity>> allScheduleDayOfWeek;
    private LiveData<List<ScheduleEntity>> allScheduleTimeFrom;
    private LiveData<List<ScheduleEntity>> allScheduleDuration;
    private LiveData<List<ScheduleEntity>> allScheduleDate;
    private LiveData<List<ScheduleEntity>> allSchedules;

    public ScheduleRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        repoScheduleDao = db.scheduleDao();
        allScheduleTitles = repoScheduleDao.getAllScheduleTitles();
        allScheduleDayOfWeek = repoScheduleDao.getAllScheduleDayOfWeeks();
        allScheduleTimeFrom = repoScheduleDao.getAllScheduleTimes();
        allScheduleDuration = repoScheduleDao.getAllScheduleDurations();
        allScheduleDate = repoScheduleDao.getAllScheduleOptionalDates();
        allSchedules = repoScheduleDao.getAllSchedules();
    }

    public LiveData<List<ScheduleEntity>> getAllScheduleTitles() {
        return allScheduleTitles;
    }
    public LiveData<List<ScheduleEntity>> getAllScheduleDayOfWeek() {
        return allScheduleDayOfWeek;
    }
    public LiveData<List<ScheduleEntity>> getAllScheduleTimeFrom() {
        return allScheduleTimeFrom;
    }
    public LiveData<List<ScheduleEntity>> getAllScheduleDuration() {
        return allScheduleDuration;
    }
    public LiveData<List<ScheduleEntity>> getAllScheduleDate() {
        return allScheduleDate;
    }
    public LiveData<List<ScheduleEntity>> getAllSchedules() {
        return allSchedules;
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
