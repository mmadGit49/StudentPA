package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mohammad.studentpa.db_classes.DAOs.UserDao;
import com.example.mohammad.studentpa.db_classes.entities.User;

import java.util.List;

public class UserRepository {

    private UserDao repoUserDao;
    private LiveData<List<User>> allUsers;

    UserRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        repoUserDao = db.userDao();
        allUsers = repoUserDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<User> getUserCredentials(String email, String password){
        return repoUserDao.getUserCredentialsLogin(email, password);
    }

    public void insert (User user) {
        //starts the async task which, in this case, inserts a user to the db
        new UserRepository.insertAsyncTask(repoUserDao).execute(user);
    }

    public void delete (User user) {
        //starts the async task which, in this case, deletes a user from the db
        new UserRepository.deleteAsyncTask(repoUserDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insertUser(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        deleteAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

}
