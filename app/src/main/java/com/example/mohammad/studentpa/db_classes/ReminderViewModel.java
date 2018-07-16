package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mohammad.studentpa.db_classes.entities.ReminderEntity;

import java.util.List;

public class ReminderViewModel extends AndroidViewModel {
    private ReminderRepository reminderRepository;
    private LiveData<List<ReminderEntity>> allReminders;
/*
    private LiveData<List<ReminderEntity>> allReminderTitles;
    private LiveData<List<ReminderEntity>> allReminderDetails;
    private LiveData<List<ReminderEntity>> allReminderDates;
    private LiveData<List<ReminderEntity>> allReminderTimes;*/

    public ReminderViewModel (Application application){
        super(application);
        reminderRepository = new ReminderRepository(application);
        allReminders = reminderRepository.getAllReminders();/*
        allReminderTitles = reminderRepository.getAllReminderTitles();
        allReminderDetails = reminderRepository.getAllReminderDetails();
        allReminderDates = reminderRepository.getAllReminderDates();
        allReminderTimes = reminderRepository.getAllReminderTimes();*/
    }

    //The following getters are used to add EVEN MORE abstraction to the repository and DB

    public LiveData<List<ReminderEntity>> getAllReminders() {
        return allReminders;
    }
/*

    public LiveData<List<ReminderEntity>> getAllReminderTitles() {
        return allReminderTitles;
    }

    public LiveData<List<ReminderEntity>> getAllReminderDetails() {
        return allReminderDetails;
    }


    public LiveData<List<ReminderEntity>> getAllReminderDates() {
        return allReminderDates;
    }

    public LiveData<List<ReminderEntity>> getAllReminderTimes() {
        return allReminderTimes;
    }*/

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
