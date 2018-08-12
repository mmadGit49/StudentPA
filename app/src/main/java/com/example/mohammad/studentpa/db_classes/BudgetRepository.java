package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mohammad.studentpa.db_classes.DAOs.BudgetDao;
import com.example.mohammad.studentpa.db_classes.entities.BudgetEntity;

import java.util.List;

public class BudgetRepository {

    private BudgetDao repoBudgetDao;
    private LiveData<List<BudgetEntity>> allBudgets;

    BudgetRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        repoBudgetDao = db.budgetDao();
        allBudgets = repoBudgetDao.getAllBudgets();
    }

    public LiveData<List<BudgetEntity>> getAllBudgets() {
        return allBudgets;
    }

    public LiveData<List<BudgetEntity>> getAllBudgetsPerUser(int userID) {
        return repoBudgetDao.getAllBudgetsPerUser(userID);
    }

    public LiveData<List<BudgetEntity>> getBudgetForDay(int userID, String date){
        return repoBudgetDao.getAllBudgetsPerDate(userID, date);
    }

    public void insertBudget (BudgetEntity budgetEntity) {
        //starts the async task which, in this case, inserts a milestone to the db
        new insertAsyncTask(repoBudgetDao).execute(budgetEntity);
    }

    public void deleteBudget (BudgetEntity budgetEntity) {
        //starts the async task which, in this case, deletes a milestone to the db
        new deleteAsyncTask(repoBudgetDao).execute(budgetEntity);
    }

    public void updateBudget (BudgetEntity budgetEntity) {
        //starts the async task which, in this case, updates a milestone to the db
        new updateAsyncTask(repoBudgetDao).execute(budgetEntity);
    }

    private static class insertAsyncTask extends AsyncTask<BudgetEntity, Void, Void> {

        private BudgetDao mAsyncTaskDao;

        insertAsyncTask(BudgetDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final BudgetEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<BudgetEntity, Void, Void> {

        private BudgetDao mAsyncTaskDao;

        deleteAsyncTask(BudgetDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final BudgetEntity... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<BudgetEntity, Void, Void> {

        private BudgetDao mAsyncTaskDao;

        updateAsyncTask(BudgetDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final BudgetEntity... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

}
