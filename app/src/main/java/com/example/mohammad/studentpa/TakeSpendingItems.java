package com.example.mohammad.studentpa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class TakeSpendingItems extends AppCompatActivity {
    private static final String TAG = "TakeSpendingItems";

    private EditText editTextAmount;
    private EditText editTextAmountDetails;
    private  EditText editTextCurrentDate;
    private FloatingActionButton fab;
    Button buttonAddItem;
    private ArrayList<String> titleNames= new ArrayList<>();
    private ArrayList<String> notes = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_spending);
        editTextAmount = findViewById(R.id.editTextSpendAmount);
        editTextAmountDetails = findViewById(R.id.editTextSpendDetails);
        editTextCurrentDate = findViewById(R.id.editTextSpendDate);
        buttonAddItem = findViewById(R.id.buttonAddItem);

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a new row to add
                TableRow row = new TableRow(TakeSpendingItems.this);
                //add Layouts to your new row
                editTextAmount = new EditText(TakeSpendingItems.this);
                editTextAmountDetails = new EditText(TakeSpendingItems.this);
                row.addView(editTextAmount);
                row.addView(editTextAmountDetails);

                //add your new row to the TableLayout:
                TableLayout table = findViewById(R.id.tableLayout_spending);
                table.addView(row);
            }
        });

    }



}
