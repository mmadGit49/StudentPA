//package com.example.mohammad.studentpa.db_classes;
//
//import android.app.Application;
//import android.arch.lifecycle.LiveData;
//import android.os.AsyncTask;
//
//import com.example.mohammad.studentpa.db_classes.DAOs.MondayDao;
//import com.example.mohammad.studentpa.db_classes.entities.MondaySchedule;
//
//import java.util.List;
//
//public class MondayRepository {
//    private MondayDao repoMondayDao;
//    private LiveData<List<MondaySchedule>> allSchedules;
//
//    public MondayRepository(Application application) {
//        AppDatabase db = AppDatabase.getDatabase(application);
//        repoMondayDao = db.mondayDao();
//        allSchedules = repoMondayDao.getAllSchedules();
//    }
//
//    public LiveData<List<MondaySchedule>> getAllSchedules() {
//        return allSchedules;
//    }
//
//    public void insertSchedule (MondaySchedule mondaySchedule) {
//        new insertAsyncTask(repoMondayDao).execute(mondaySchedule);
//    }
//
//    public void deleteSchedule (MondaySchedule mondaySchedule) {
//        new deleteAsyncTask(repoMondayDao).execute(mondaySchedule);
//    }
//
//    public void updateSchedule (MondaySchedule mondaySchedule) {
//        new updateAsyncTask(repoMondayDao).execute(mondaySchedule);
//    }
//
//    private static class insertAsyncTask extends AsyncTask<MondaySchedule, Void, Void> {
//
//        private MondayDao mAsyncTaskDao;
//
//        insertAsyncTask(MondayDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final MondaySchedule... params) {
//            mAsyncTaskDao.insertSchedule(params[0]);
//            return null;
//        }
//    }
//
//    private static class deleteAsyncTask extends AsyncTask<MondaySchedule, Void, Void> {
//
//        private MondayDao mAsyncTaskDao;
//
//        deleteAsyncTask(MondayDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final MondaySchedule... params) {
//            mAsyncTaskDao.delete(params[0]);
//            return null;
//        }
//    }
//
//    private static class updateAsyncTask extends AsyncTask<MondaySchedule, Void, Void> {
//
//        private MondayDao mAsyncTaskDao;
//
//        updateAsyncTask(MondayDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final MondaySchedule... params) {
//            mAsyncTaskDao.update(params[0]);
//            return null;
//        }
//    }
//
//}
