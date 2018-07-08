package com.example.mohammad.studentpa.Spending;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.Util.DatePickerFragment;
import com.example.mohammad.studentpa.db_classes.Entities.SpendingEntity;
import com.example.mohammad.studentpa.db_classes.SpendingViewModel;

import java.util.ArrayList;
import java.util.List;

public class TakeSpendingItems extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener{
    private static final String TAG = "TakeSpendingItems";

    private EditText editTextAmount;
    private EditText editTextAmountDetails;
    private TextView textViewDate;
    private TextView spendingTotal;
    private Button buttonAddItem;
    private Button buttonDeleteItem;
    private Button sumItems;
    private FloatingActionButton fab;

    private SpendingViewModel spendingViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_spending);
        editTextAmount = findViewById(R.id.editTextSpendAmount);
        editTextAmountDetails = findViewById(R.id.editTextSpendDetails);
        textViewDate = findViewById(R.id.textViewSpendDate);
        spendingTotal= findViewById(R.id.textViewTotal);
        sumItems= findViewById(R.id.buttonSumItems);

        spendingViewModel = ViewModelProviders.of(TakeSpendingItems.this).
                get(SpendingViewModel.class);

        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        buttonAddItem = findViewById(R.id.buttonAddItem);
        buttonDeleteItem = findViewById(R.id.buttonRemoveItem);
        spendingTotal = findViewById(R.id.textViewTotal);

        spendingViewModel = ViewModelProviders.of(TakeSpendingItems.this).get(SpendingViewModel.class);

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Log.d(TAG, "onClick: started add item onClick");
                TableRow row = new TableRow(TakeSpendingItems.this);

                //create a new row to add Layouts to your new row
                editTextAmount = new EditText(TakeSpendingItems.this);
                editTextAmountDetails = new EditText(TakeSpendingItems.this);
                //set input type to numerical
                editTextAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
                //Wrap_content layout
                editTextAmount.setLayoutParams
                        (new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.MATCH_PARENT, 50f));
                editTextAmountDetails.setLayoutParams
                        (new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT, 50f));
                row.addView(editTextAmount);
                row.addView(editTextAmountDetails);
                //add your new row to the TableLayout:
                TableLayout table = findViewById(R.id.tableSpending);
                table.addView(row);*/
                if(TextUtils.isEmpty(editTextAmount.getText().toString()) &&
                        TextUtils.isEmpty(editTextAmountDetails.getText().toString()) &&
                        TextUtils.isEmpty(textViewDate.getText().toString())){
                    Toast.makeText(TakeSpendingItems.this, "Required data missing",
                            Toast.LENGTH_SHORT).show();
                }else{
                    String amount = editTextAmount.getText().toString();
                    String details = editTextAmountDetails.getText().toString();
                    String date= textViewDate.getText().toString();
                    double total = getTotalSpent();
                    String totalSpent = Double.toString(total);

                    spendingViewModel.insert(new SpendingEntity(amount, details, date, totalSpent));
                    editTextAmount.setText("");
                    editTextAmountDetails.setText("");
                }
            }
        });

        buttonDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Log.d(TAG, "onClick: started delete onclick");

                TableLayout table = findViewById(R.id.tableSpending);

                if (table.getChildCount()>0){
                    table.removeView(table.getChildAt(table.getChildCount()-1));
                }*/
            }
        });

        sumItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double total= getTotalSpent();
                spendingTotal.setText("Today's total: " + total);
            }
        });

        fab = findViewById(R.id.fab_save_item);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String stringDate= dayOfMonth + " / " + month + " / " + year;
        textViewDate.setText(stringDate);
    }

    public double getTotalSpent(){
        double total = 0;
        return total;
    }
    public void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view_spending_items);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final AmountAdapter adapter =
                new AmountAdapter(TakeSpendingItems.this, new ArrayList<SpendingEntity>());
        recyclerView.setAdapter(adapter);

        spendingViewModel = ViewModelProviders.of(this).get(SpendingViewModel.class);
        spendingViewModel.getAllSpendings().observe(this, new Observer<List<SpendingEntity>>() {
            @Override
            public void onChanged(@Nullable List<SpendingEntity> spendingEntities) {
                //Update the cached copy of words in the adapter
                adapter.setItems(spendingEntities);
                Log.i("##############", spendingEntities.size() + "");
            }
        });

        /*ItemTouchHelper helper = new ItemTouchHelper(
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
                        final ScheduleEntity scheduleEntity = adapter.getScheduleAtPosition(position);
                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(getContext());
                        }
                        builder.setTitle("Delete Item")
                                .setMessage("Are you sure you want to delete?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        scheduleViewModel.delete(scheduleEntity);
                                        Toast.makeText(getContext(), "Class deleted!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        scheduleViewModel.insert(scheduleEntity);
                                    }
                                }).show();
                        // Delete the word

                    }
                });
        helper.attachToRecyclerView(recyclerView);*/
    }

}
