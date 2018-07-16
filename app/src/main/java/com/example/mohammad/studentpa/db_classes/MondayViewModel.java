//package com.example.mohammad.studentpa.db_classes;
//
//import android.app.Application;
//import android.arch.lifecycle.AndroidViewModel;
//import android.arch.lifecycle.LiveData;
//
//import com.example.mohammad.studentpa.db_classes.entities.MondaySchedule;
//
//import java.util.List;
//
//public class MondayViewModel extends AndroidViewModel {
//    private MondayRepository mondayRepository;
//    private LiveData<List<MondaySchedule>> allSchedules;
//
//    public MondayViewModel (Application application){
//        super(application);
//        mondayRepository = new MondayRepository(application);
//        allSchedules = mondayRepository.getAllSchedules();
//    }
//
//    public LiveData<List<MondaySchedule>> getAllSchedules() {
//        return allSchedules;
//    }
//
//    public void insert(MondaySchedule mondaySchedule) {
//        mondayRepository.insertSchedule(mondaySchedule);    }
//
//    public  void delete(MondaySchedule mondaySchedule){
//        mondayRepository.deleteSchedule(mondaySchedule);    }
//
//    public  void update(MondaySchedule mondaySchedule){
//        mondayRepository.updateSchedule(mondaySchedule);    }
//
//}
