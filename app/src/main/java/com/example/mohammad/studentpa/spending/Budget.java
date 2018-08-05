package com.example.mohammad.studentpa.spending;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import com.example.mohammad.studentpa.db_classes.SpendingViewModel;
import com.example.mohammad.studentpa.db_classes.entities.SpendingEntity;
import com.example.mohammad.studentpa.util.LocalData;

import java.util.Calendar;
import java.util.List;

public class Budget extends Fragment{

    private View spendView;
    private Context context;
    private FloatingActionButton fab;
    private TextView currentBudget;
    private TextView weeklyBudget;
    private TextView monthlyBudget;
    private TextView spentToday;
    private TextView budgetStatus;
    private EditText editTextBudget;
    private Button updateBudget;

    private SpendingViewModel spendingViewModel;
    private LocalData localData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        spendView = inflater.inflate(R.layout.fragment_spending, container, false);

        Toolbar toolbar = spendView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);//sets menu icon to action bar
        toolbar.setTitle("Budget");

//        Floating action Button action
        fab = spendView.findViewById(R.id.fab_spend);
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
        editTextBudget = spendView.findViewById(R.id.editTextDailyBudget);
        updateBudget = spendView.findViewById(R.id.buttonSetBudget);
        budgetStatus = spendView.findViewById(R.id.textViewAlertBudget);

        localData = new LocalData(getActivity());

        spendingViewModel = ViewModelProviders.of(this).get(SpendingViewModel.class);

        final float dayBudget = localData.get_budget();
        String dailyBudget = "Current daily budget:\n\t\t"+ Float.toString(dayBudget);
        currentBudget.setText(dailyBudget);

        float weeklyBudg = dayBudget * 7;
        String weekBudget = "Weekly Budget (assuming 7 days):\n\t\t" + Float.toString(weeklyBudg);
        weeklyBudget.setText(weekBudget);

        float monthlyBudg = dayBudget * 30;
        String monthBudget = "Monthly Budget (assuming 30 days):\n\t\t" + Float.toString(monthlyBudg);
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
                    String dailyBudget = "Current daily budget:\n\t\t"+ Float.toString(budgetValue);
                    currentBudget.setText(dailyBudget);
                }
            }
        });

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
                            }
                        }
                    }
                });

        return spendView;
    }

    public String getDate(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return day + " / " + (month + 1) + " / " + year;
    }
}
