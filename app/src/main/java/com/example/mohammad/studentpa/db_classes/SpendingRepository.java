package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mohammad.studentpa.db_classes.DAOs.SpendingDao;
import com.example.mohammad.studentpa.db_classes.entities.SpendingEntity;

import java.util.List;

public class SpendingRepository {
    private SpendingDao repoSpendingDao;
    private LiveData<List<SpendingEntity>> allSpendingDates;
    private LiveData<List<SpendingEntity>> allSpendingAmounts;
    private LiveData<List<SpendingEntity>> allSpendingDetails;
    private LiveData<List<SpendingEntity>> allSpendings;


    public SpendingRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        repoSpendingDao = db.spendingDao();
        allSpendingDates = repoSpendingDao.getAllSpendingDates();
        allSpendingAmounts = repoSpendingDao.getAllSpendingAmounts();
        allSpendingDetails = repoSpendingDao.getAllSpendingDetails();
        allSpendings = repoSpendingDao.getAllSpendingItems();
    }

    //For abstraction, we wrap the getters as follows
    public LiveData<List<SpendingEntity>> getAllSpendingDates (){
        return allSpendingDates;
    }

    public LiveData<List<SpendingEntity>> getAllSpendingAmounts (){
        return allSpendingAmounts;
    }

    public LiveData<List<SpendingEntity>> getAllSpendingDetails (){
        return allSpendingDetails;
    }

    public LiveData<List<SpendingEntity>> getAllSpendings() {
        return allSpendings;
    }

    public void insertSpending (SpendingEntity spendingEntity) {
        new insertAsyncTask(repoSpendingDao).execute(spendingEntity);
    }
    public void deleteSpending (SpendingEntity spendingEntity) {
        new deleteAsyncTask(repoSpendingDao).execute(spendingEntity);
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
}
