package com.example.mohammad.studentpa.Spending;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.Util.DatePickerFragment;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_spending);
        editTextAmount = findViewById(R.id.editTextSpendAmount);
        editTextAmountDetails = findViewById(R.id.editTextSpendDetails);
        textViewDate = findViewById(R.id.textViewSpendDate);
        spendingTotal= findViewById(R.id.textViewTotal);
        sumItems= findViewById(R.id.buttonSumItems);

        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        buttonAddItem = findViewById(R.id.buttonAddItem);
        buttonDeleteItem = findViewById(R.id.buttonRemoveItem);
        spendingTotal = findViewById(R.id.textViewTotal);

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: started add item onClick");
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
                table.addView(row);
            }
        });

        buttonDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: started delete onclick");

                TableLayout table = findViewById(R.id.tableSpending);

                if (table.getChildCount()>0){
                    TableRow row= new TableRow(TakeSpendingItems.this);
                    table.removeView(row);
                }
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
        GridLayout gLayout = new GridLayout(TakeSpendingItems.this);
        TableRow t ;

        for ( int i = 0 ; i<gLayout.getRowCount(); i++){
            String amount = gLayout.getChildAt(i).toString();
            double parsedAmount = Double.parseDouble(amount);
            total += parsedAmount;
        }
        return total;
    }

}
