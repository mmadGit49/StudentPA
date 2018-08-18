package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mohammad.studentpa.db_classes.DAOs.SpendingDao;
import com.example.mohammad.studentpa.db_classes.entities.SpendingEntity;

import java.util.List;

public class SpendingRepository {
    private SpendingDao repoSpendingDao;
    private LiveData<List<SpendingEntity>> allSpending;


    SpendingRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        repoSpendingDao = db.spendingDao();
        allSpending = repoSpendingDao.getAllSpendingItems();
    }

    public LiveData<List<SpendingEntity>> getAllSpending() {
        return allSpending;
    }

    public LiveData<List<SpendingEntity>> getAllSpendingbyDate(String date, int userID) {
        return repoSpendingDao.getAllSpendingItemsByDate(date, userID);
    }

    public LiveData<List<SpendingEntity>> getAllSpendingbyUser(int userID) {
        return repoSpendingDao.getAllSpendingItemsByUser(userID);
    }

    public void insertSpending (SpendingEntity spendingEntity) {
        new insertAsyncTask(repoSpendingDao).execute(spendingEntity);
    }

    public void deleteSpending (SpendingEntity spendingEntity) {
        new deleteAsyncTask(repoSpendingDao).execute(spendingEntity);
    }

    public void updateSpending (SpendingEntity spendingEntity){
        new updateAsyncTask(repoSpendingDao).execute(spendingEntity);
    }

    private static class insertAsyncTask extends AsyncTask<SpendingEntity, Void, Void> {

        private SpendingDao mAsyncTaskDao;

        insertAsyncTask(SpendingDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(SpendingEntity... spendingEntities) {
            mAsyncTaskDao.insertSpending(spendingEntities[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<SpendingEntity, Void, Void> {

        private SpendingDao mAsyncTaskDao;

        deleteAsyncTask(SpendingDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(SpendingEntity... spendingEntities) {
            mAsyncTaskDao.delete(spendingEntities[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<SpendingEntity, Void, Void> {

        private SpendingDao mAsyncTaskDao;

        updateAsyncTask(SpendingDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(SpendingEntity... spendingEntities) {
            mAsyncTaskDao.update(spendingEntities[0]);
            return null;
        }
    }

}
