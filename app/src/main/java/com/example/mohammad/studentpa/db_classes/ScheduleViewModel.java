package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mohammad.studentpa.db_classes.entities.ScheduleEntity;

import java.util.List;

public class ScheduleViewModel extends AndroidViewModel {
    private ScheduleRepository scheduleRepository;
    private LiveData<List<ScheduleEntity>> allSchedules;

    public ScheduleViewModel (Application application){
        super(application);
        scheduleRepository = new ScheduleRepository(application);
        allSchedules = scheduleRepository.getAllSchedules();
    }

    public LiveData<List<ScheduleEntity>> getAllSchedules() {
        return allSchedules;
    }

    public LiveData<List<ScheduleEntity>> getAllSchedulesByDay(String dayOfWeek, int userID){
        return scheduleRepository.getAllSpecificDaySchedules(dayOfWeek, userID);
    }

    public LiveData<List<ScheduleEntity>> getAllScheduleDays(int schedID){
        return scheduleRepository.getAllSpecificDays(schedID);
    }

    public void insert(ScheduleEntity scheduleEntity) {
        scheduleRepository.insertSchedule(scheduleEntity);    }

    public  void delete(ScheduleEntity scheduleEntity){
        scheduleRepository.deleteSchedule(scheduleEntity);    }

    public  void update(ScheduleEntity scheduleEntity){
        scheduleRepository.updateSchedule(scheduleEntity);    }
}
