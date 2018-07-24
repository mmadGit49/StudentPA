package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mohammad.studentpa.db_classes.entities.ReminderEntity;

import java.util.List;

public class ReminderViewModel extends AndroidViewModel {
    private ReminderRepository reminderRepository;
    private LiveData<List<ReminderEntity>> allReminders;


    public ReminderViewModel (Application application){
        super(application);
        reminderRepository = new ReminderRepository(application);
        allReminders = reminderRepository.getAllReminders();
    }

    //The following getters are used to add EVEN MORE abstraction to the repository and DB

    public LiveData<List<ReminderEntity>> getAllReminders() {
        return allReminders;
    }

    public LiveData<List<ReminderEntity>> getAllRemindersByID(int id) {
        return reminderRepository.getAllRemindersbyID(id);
    }


    //Again, for abstraction
    public void insert(ReminderEntity milestoneEntity) {
        reminderRepository.insertReminder(milestoneEntity);
    }

    public  void delete(ReminderEntity milestoneEntity){
        reminderRepository.deleteReminder(milestoneEntity);
    }

    public  void update(ReminderEntity milestoneEntity){
        reminderRepository.updateReminder(milestoneEntity);
    }


}
