package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class SpendingRepository {
    private SpendingDao repoSpendingDao;
    private LiveData<List<SpendingEntity>> allSpendingDates;
    private LiveData<List<SpendingEntity>> allSpendingAmounts;
    private LiveData<List<SpendingEntity>> allSpendingDetails;

    public SpendingRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        repoSpendingDao = db.spendingDao();
        allSpendingDates = repoSpendingDao.getAllSpendingDates();
        allSpendingAmounts = repoSpendingDao.getAllSpendingAmounts();
        allSpendingDetails = repoSpendingDao.getAllSpendingDetails();
    }

    //For abstraction, we wrap the getters as follows
    LiveData<List<SpendingEntity>> getAllSpendingDates (){
        return allSpendingDates;
    }

    LiveData<List<SpendingEntity>> getAllSpendingAmounts (){
        return allSpendingAmounts;
    }

    LiveData<List<SpendingEntity>> getAllSpendingDetails (){
        return allSpendingDetails;
    }

    public void insertSpending (SpendingEntity spendingEntity) {
        new insertAsyncTask(repoSpendingDao).execute(spendingEntity);
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
}
