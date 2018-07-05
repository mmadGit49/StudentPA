//This is the app database class
package com.example.mohammad.studentpa.db_classes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities={User.class,
                    MilestoneEntity.class,
                    ScheduleEntity.class,
                    SpendingEntity.class,
                    ReminderEntity.class}, version= 3, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract  MilestoneDao milestoneDao();
    public abstract ScheduleDao scheduleDao();
    public abstract ReminderDao reminderDao();
    public abstract SpendingDao spendingDao();

    private static AppDatabase DB_INSTANCE;//Database instance

    static AppDatabase getDatabase(final Context context) {
        //To prevent multiple instances of the DB from being created simultaneously:
        if (DB_INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (DB_INSTANCE == null) {
                    DB_INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                            "organ_eyes_db").build();

                }
            }
        }
        return DB_INSTANCE;
    }

}
