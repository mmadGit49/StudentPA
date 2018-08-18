package com.example.mohammad.studentpa.spending;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.BudgetViewModel;
import com.example.mohammad.studentpa.db_classes.SpendingViewModel;
import com.example.mohammad.studentpa.db_classes.entities.BudgetEntity;
import com.example.mohammad.studentpa.db_classes.entities.SpendingEntity;
import com.example.mohammad.studentpa.util.LocalData;
import com.example.mohammad.studentpa.util.NotificationScheduler;

import java.util.Calendar;
import java.util.List;

public class Budget extends Fragment{
    private TextView currentBudget;
    private TextView weeklyBudget;
    private TextView monthlyBudget;
    private TextView spentToday;
    private TextView spentWeek;
    private TextView spentMonth;
    private TextView budgetStatus;
    private EditText editTextBudget;

    private LocalData localData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View spendView = inflater.inflate(R.layout.fragment_spending, container, false);

        Toolbar toolbar = spendView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);//sets menu icon to action bar
        toolbar.setTitle("Budget");

        FloatingActionButton fab = spendView.findViewById(R.id.fab_spend);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spendingIntent = new Intent(getActivity(), Spending.class);
                startActivity(spendingIntent);
            }
        });

        currentBudget = spendView.findViewById(R.id.textViewCurrentDailyBudget);
        weeklyBudget = spendView.findViewById(R.id.textViewWeeklyBudget);
        monthlyBudget = spendView.findViewById(R.id.textViewMonthlyBudget);
        spentToday = spendView.findViewById(R.id.textViewCurrentlySpent);
        spentWeek = spendView.findViewById(R.id.textViewWeeklySpent);
        spentMonth = spendView.findViewById(R.id.textViewMonthlySpent);
        editTextBudget = spendView.findViewById(R.id.editTextDailyBudget);
        Button updateBudget = spendView.findViewById(R.id.buttonSetBudget);
        budgetStatus = spendView.findViewById(R.id.textViewAlertBudget);

        localData = new LocalData(getActivity());

        final float dayBudget = localData.get_budget();
        String dailyBudget = "Current daily budget: "+ Float.toString(dayBudget);
        currentBudget.setText(dailyBudget);

        float weeklyBudg = dayBudget * 7;
        String weekBudget = "Weekly Budget:\n" + Float.toString(weeklyBudg);
        weeklyBudget.setText(weekBudget);

        float monthlyBudg = dayBudget * 30;
        String monthBudget = "Monthly Budget:\n" + Float.toString(monthlyBudg);
        monthlyBudget.setText(monthBudget);

        String spent = "Total spent today: " + localData.get_amount();
        spentToday.setText(spent);


        updateBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextBudget.getText().toString())){
                    Toast.makeText(getActivity(), "Enter new value",
                            Toast.LENGTH_SHORT).show();
                }else{
                    String budget = editTextBudget.getText().toString();
                    float budgetValue = Float.parseFloat(budget);
                    localData.set_budget(budgetValue);

                    String dailyBudget = "Current daily budget: "+ Float.toString(budgetValue);
                    currentBudget.setText(dailyBudget);

                    float weeklyBudg = budgetValue * 7;
                    String weekBudget = "Weekly Budget:\n" + Float.toString(weeklyBudg);
                    weeklyBudget.setText(weekBudget);

                    float monthlyBudg = budgetValue * 30;
                    String monthBudget = "Monthly Budget:\n" + Float.toString(monthlyBudg);
                    monthlyBudget.setText(monthBudget);

                    editTextBudget.setText("");
                    Toast.makeText(getActivity(), "Budget updated successfully!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        getBudgetStatus();

        getMonthlyExpenditure();

        getWeeklyExpenditure();

        return spendView;
    }

    public String getDate(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return day + " / " + (month + 1) + " / " + year;
    }

    public void getWeeklyExpenditure() {
        BudgetViewModel budgetViewModel = ViewModelProviders.of(this).get(BudgetViewModel.class);

        budgetViewModel.getAllBudgetsPerUser(localData.get_user()).observe(this,
                new Observer<List<BudgetEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<BudgetEntity> budgetEntities) {
                        if (budgetEntities != null) {
                            double storedAmount;
                            float total = 0;
                            if (budgetEntities.size() >= 7) {//Bad algorithm, assumes days are stored incrementally
                                for(int i = budgetEntities.size() - 7; i < budgetEntities.size(); i++){
                                    storedAmount = budgetEntities.get(i).getTotalSpentDay();
                                    total += storedAmount;
                                }

                                String weekText = "Last 7 days:\n" + total;
                                spentWeek.setText(weekText);
                            } else {
                                for(int i = 0; i < budgetEntities.size(); i++){
                                    storedAmount = budgetEntities.get(i).getTotalSpentDay();
                                    total += storedAmount;
                                }
                                String weekText = "Last 7 days:\n" + total;
                                spentWeek.setText(weekText);
                            }
                            localData.set_weekAmount(total);

                        }
                    }
                });
    }

    public void getMonthlyExpenditure(){
        BudgetViewModel budgetViewModel = ViewModelProviders.of(this).get(BudgetViewModel.class);

        budgetViewModel.getAllBudgetsPerUser(localData.get_user()).observe(this,
                new Observer<List<BudgetEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<BudgetEntity> budgetEntities) {
                        if (budgetEntities != null) {
                            double storedAmount;
                            float total = 0;
                            if (budgetEntities.size() >= 30) {
                                for(int i = budgetEntities.size() - 30; i < budgetEntities.size(); i++){
                                    storedAmount = budgetEntities.get(i).getTotalSpentDay();
                                    total += storedAmount;
                                }
                                String monthText = "Last 30 days:\n" + total;
                                spentMonth.setText(monthText);
                            } else {
                                for(int i = 0; i < budgetEntities.size(); i++){
                                    storedAmount = budgetEntities.get(i).getTotalSpentDay();
                                    total += storedAmount;
                                }
                                String monthText = "Last 30 days:\n" + total;
                                spentMonth.setText(monthText);
                            }
                            localData.set_monthAmount(total);
                        }
                    }
                });
    }

    public void getBudgetStatus(){
        SpendingViewModel spendingViewModel = ViewModelProviders.of(this).get(SpendingViewModel.class);

        spendingViewModel.getAllSpendingByDate(getDate(), localData.get_user())
                .observe(getActivity(), new Observer<List<SpendingEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<SpendingEntity> spendingEntities) {
                        double storedAmount;
                        float total = 0;
                        if(spendingEntities != null){
                            for(int i = 0; i < spendingEntities.size(); i++){
                                storedAmount = spendingEntities.get(i).getSpendAmount();
                                total += storedAmount;
                            }

                            String totalAmount = "Total spent today: " + Float.toString(total);
                            spentToday.setText(totalAmount);

                            if(total <= localData.get_budget()){
                                budgetStatus.setText("You are currently WITHIN budget \n Keep it up!");
                            }else{
                                budgetStatus.setText("You are currently OVER budget \n Check expenses or modify budget!");
                                NotificationScheduler.showNotification(getActivity(), Spending.class,
                                        "Daily Budget Exceeded!", "Tap to view expenses");
                            }
                        }
                    }
                });


        if(localData.get_weekAmount() > localData.get_budget() * 7){
            NotificationScheduler.showNotification(getActivity(), Spending.class,
                    "Week Budget Exceeded!", "Tap to view expenses");
        }

        if(localData.get_monthAmount() > localData.get_budget() * 30){
            NotificationScheduler.showNotification(getActivity(), Spending.class,
                    "Month Budget Exceeded!", "Tap to view expenses");
        }
    }
}
