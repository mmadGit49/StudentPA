package com.example.mohammad.studentpa.spending;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.BudgetViewModel;
import com.example.mohammad.studentpa.db_classes.SpendingViewModel;
import com.example.mohammad.studentpa.db_classes.entities.BudgetEntity;
import com.example.mohammad.studentpa.db_classes.entities.SpendingEntity;
import com.example.mohammad.studentpa.util.DatePickerFragment;
import com.example.mohammad.studentpa.util.LocalData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Spending extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Context context;
    private TextView textViewDate;
    private EditText editTextAmount;
    private EditText editTextAmountDetails;
    private TextView spendingTotal;

    private SpendingViewModel spendingViewModel;
    private BudgetViewModel budgetViewModel;
    private LocalData localData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendings);

        localData = new LocalData(Spending.this);

        editTextAmount = findViewById(R.id.editTextSpendAmount);
        editTextAmountDetails = findViewById(R.id.editTextSpendDetails);
        textViewDate = findViewById(R.id.textViewSpendDate);
        spendingTotal= findViewById(R.id.textViewTotal);
        FloatingActionButton fab = findViewById(R.id.fab_save);
        Button sumItems = findViewById(R.id.buttonSumItems);

        spendingViewModel = ViewModelProviders.of(Spending.this).get(SpendingViewModel.class);
        budgetViewModel = ViewModelProviders.of(Spending.this).get(BudgetViewModel.class);

        textViewDate.setText(getDate());
        spendingTotal.setText(R.string.spend_total);
        textViewDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog(v);
                }
            });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTotals();
                finish();
            }
        });

        Button buttonAddItem = findViewById(R.id.buttonAddItem);
        Button buttonDeleteItem = findViewById(R.id.buttonRemoveItem);

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(editTextAmount.getText().toString()) ||
                            TextUtils.isEmpty(editTextAmountDetails.getText().toString()) ||
                            TextUtils.isEmpty(textViewDate.getText().toString())){
                        Toast.makeText(Spending.this, "Required data missing",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        String amount = editTextAmount.getText().toString();
                        double amountDouble = Double.parseDouble(amount);
                        String details = editTextAmountDetails.getText().toString();
                        String date = textViewDate.getText().toString();
                        int user = localData.get_user();

                        spendingViewModel.insert
                                (new SpendingEntity(date, amountDouble, details, user));

                        editTextAmount.setText("");
                        editTextAmountDetails.setText("");
                    }
                }
            });

        buttonDeleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Spending.this, "Swipe item to delete...",
                            Toast.LENGTH_SHORT).show();
                }
            });

            //Calculate total spent
        sumItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spendingViewModel.getAllSpendingByDate(textViewDate.getText().toString(), localData.get_user())
                            .observe(Spending.this, new Observer<List<SpendingEntity>>() {
                                @Override
                                public void onChanged(@Nullable List<SpendingEntity> spendingEntities) {
                                    double storedAmount;
                                    float total = 0;
                                    if(spendingEntities != null){
                                        for(int i = 0; i < spendingEntities.size(); i++){
                                            storedAmount = spendingEntities.get(i).getSpendAmount();
                                            total += storedAmount;
                                        }
                                        String totalAmount = "Total spent "
                                                +"("
                                                +textViewDate.getText().toString()
                                                +") "
                                                + Float.toString(total);
                                        spendingTotal.setText(totalAmount);
                                    }
                                }
                            });
                }
            });

        initRecyclerView();

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String stringDate= dayOfMonth + " / " + (month + 1) + " / " + year;
        textViewDate.setText(stringDate);
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_spending_items);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        final AmountAdapter adapter =  new AmountAdapter(context, new ArrayList<SpendingEntity>());
        recyclerView.setAdapter(adapter);

        spendingViewModel = ViewModelProviders.of(this).get(SpendingViewModel.class);
        spendingViewModel.getAllSpendingByUser(localData.get_user()).observe(this, new Observer<List<SpendingEntity>>() {
            @Override
            public void onChanged(@Nullable List<SpendingEntity> spendingEntities) {
                //Update the cached copy of words in the adapter
                adapter.setItems(spendingEntities);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        final SpendingEntity spendingEntity = adapter.getSpendingAtPosition(position);

                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(Spending.this,
                                    android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(Spending.this);
                        }
                        builder.setTitle("Delete Item")
                                .setMessage("Are you sure you want to delete?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        textViewDate.setText(spendingEntity.getSpendDate());
                                        spendingViewModel.delete(spendingEntity);
                                        Toast.makeText(Spending.this,
                                                "Item deleted!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        spendingViewModel.insert(spendingEntity);
                                    }
                                }).show();
                    }
                });
        helper.attachToRecyclerView(recyclerView);
    }

    public String getDate(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return day + " / " + (month + 1) + " / " + year;
    }

    public void updateTotals(){
        spendingViewModel.getAllSpendingByDate(textViewDate.getText().toString(), localData.get_user())
                .observe(Spending.this, new Observer<List<SpendingEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<SpendingEntity> spendingEntities) {
                        double storedAmount;
                        float total = 0;
                        if(spendingEntities != null){
                            for(int i = 0; i < spendingEntities.size(); i++){
                                storedAmount = spendingEntities.get(i).getSpendAmount();
                                total += storedAmount;
                            }
                            budgetViewModel.insert(new BudgetEntity(textViewDate.getText().toString(),
                                    total, localData.get_user()));

                        }else{
                            budgetViewModel.insert(new BudgetEntity(textViewDate.getText().toString(),
                                    0, localData.get_user()));
                        }
                    }
                });

    }

}
