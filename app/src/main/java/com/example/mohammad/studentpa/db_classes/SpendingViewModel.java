package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mohammad.studentpa.db_classes.entities.SpendingEntity;

import java.util.List;

public class SpendingViewModel extends AndroidViewModel {
    private SpendingRepository spendingRepository;

    public SpendingViewModel (Application application){
        super(application);
        spendingRepository = new SpendingRepository(application);
    }

    public LiveData<List<SpendingEntity>> getAllSpendingByDate(String date, int userID) {
    return spendingRepository.getAllSpendingbyDate(date, userID);
    }

    public LiveData<List<SpendingEntity>> getAllSpendingByUser(int userID) {
        return spendingRepository.getAllSpendingbyUser(userID);
    }

    public void insert(SpendingEntity spendingEntity){
        spendingRepository.insertSpending(spendingEntity);
    }

    public void delete(SpendingEntity spendingEntity){
        spendingRepository.deleteSpending(spendingEntity);
    }

    public void update(SpendingEntity spendingEntity){
        spendingRepository.updateSpending(spendingEntity);
    }



}
