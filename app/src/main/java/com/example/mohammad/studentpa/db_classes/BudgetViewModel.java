package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mohammad.studentpa.db_classes.entities.BudgetEntity;

import java.util.List;

public class BudgetViewModel extends AndroidViewModel{
    private BudgetRepository budgetRepository;
    private LiveData<List<BudgetEntity>> allBudgets;

    public BudgetViewModel (Application application){
        super (application);
        budgetRepository = new BudgetRepository(application);
        allBudgets = budgetRepository.getAllBudgets();
    }

    public LiveData<List<BudgetEntity>> getAllBudgets() {
        return allBudgets;
    }

    public LiveData<List<BudgetEntity>> getAllBudgetsPerUser(int userID) {
        return budgetRepository.getAllBudgetsPerUser(userID);
    }

    public LiveData<List<BudgetEntity>> getAllBudgetsByDay(int userID, String date){
        return budgetRepository.getBudgetForDay(userID, date);
    }

    //Again, for abstraction
    public void insert(BudgetEntity budgetEntity) {
        budgetRepository.insertBudget(budgetEntity);
    }

    public  void delete(BudgetEntity budgetEntity){
        budgetRepository.deleteBudget(budgetEntity);
    }

    public  void update(BudgetEntity budgetEntity){
        budgetRepository.updateBudget(budgetEntity);
    }
}
