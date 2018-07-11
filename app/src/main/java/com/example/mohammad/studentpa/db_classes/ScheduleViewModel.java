package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mohammad.studentpa.db_classes.Entities.ScheduleEntity;

import java.util.List;

public class ScheduleViewModel extends AndroidViewModel {
    private ScheduleRepository scheduleRepository;
    private LiveData<List<ScheduleEntity>> allScheduleTitles;
    private LiveData<List<ScheduleEntity>> allScheduleDayOfWeek;
    private LiveData<List<ScheduleEntity>> allScheduleTimeFrom;
    private LiveData<List<ScheduleEntity>> allScheduleDuration;
    private LiveData<List<ScheduleEntity>> allScheduleDates;
    private LiveData<List<ScheduleEntity>> allSchedules;

    public ScheduleViewModel (Application application){
        super(application);
        scheduleRepository = new ScheduleRepository(application);
        allScheduleTitles = scheduleRepository.getAllScheduleTitles();
        allScheduleDayOfWeek = scheduleRepository.getAllScheduleDayOfWeek();
        allScheduleTimeFrom = scheduleRepository.getAllScheduleTimeFrom();
        allScheduleDuration = scheduleRepository.getAllScheduleDuration();
        allScheduleDates = scheduleRepository.getAllScheduleDate();
        allSchedules = scheduleRepository.getAllSchedules();
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
    public LiveData<List<ScheduleEntity>> getAllScheduleDates() {
        return allScheduleDates;
    }
    public LiveData<List<ScheduleEntity>> getAllSchedules() {
        return allSchedules;
    }

    public void insert(ScheduleEntity scheduleEntity) {
        scheduleRepository.insertSchedule(scheduleEntity);
    }

    public  void delete(ScheduleEntity scheduleEntity){
        scheduleRepository.deleteSchedule(scheduleEntity);    }

    public  void update(ScheduleEntity scheduleEntity){
        scheduleRepository.updateSchedule(scheduleEntity);    }
}
