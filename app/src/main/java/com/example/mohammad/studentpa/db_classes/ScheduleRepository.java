package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

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
        //starts the async task which, in this case, inserts a milestone to the db
        new insertAsyncTask(repoScheduleDao).execute(scheduleEntity);
    }

    public void deleteSchedule (ScheduleEntity scheduleEntity) {
        //starts the async task which, in this case, inserts a milestone to the db
        new insertDeleteAsyncTask(repoScheduleDao).execute(scheduleEntity);
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

    private static class insertDeleteAsyncTask extends AsyncTask<ScheduleEntity, Void, Void> {

        private ScheduleDao mAsyncTaskDao;

        insertDeleteAsyncTask(ScheduleDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ScheduleEntity... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }


}
