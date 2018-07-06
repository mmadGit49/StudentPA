package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mohammad.studentpa.db_classes.Entities.SpendingEntity;

import java.util.List;

public class SpendingViewModel extends AndroidViewModel {
    private SpendingRepository spendingRepository;
    private LiveData<List<SpendingEntity>> allSpendings;
    private LiveData<List<SpendingEntity>> allSpendingDates;
    private LiveData<List<SpendingEntity>> allSpendingAmounts;
    private LiveData<List<SpendingEntity>> allSpendingDetails;

    public SpendingViewModel (Application application){
        super(application);
        spendingRepository = new SpendingRepository(application);
        allSpendings = spendingRepository.getAllSpendings();
        allSpendingDates = spendingRepository.getAllSpendingDates();
        allSpendingAmounts = spendingRepository.getAllSpendingAmounts();
        allSpendingDetails = spendingRepository.getAllSpendingDetails();
    }

    public LiveData<List<SpendingEntity>> getAllSpendings() {
        return allSpendings;
    }
    public LiveData<List<SpendingEntity>> getAllSpendingDates() {
        return allSpendingDates;
    }
    public LiveData<List<SpendingEntity>> getAllSpendingAmounts() {
        return allSpendingAmounts;
    }
    public LiveData<List<SpendingEntity>> getAllSpendingDetails() {
        return allSpendingDetails;
    }

    public void insert(SpendingEntity spendingEntity){
        spendingRepository.insertSpending(spendingEntity);
    }

    public  void  delete(SpendingEntity spendingEntity){
        spendingRepository.deleteSpending(spendingEntity);
    }

}
