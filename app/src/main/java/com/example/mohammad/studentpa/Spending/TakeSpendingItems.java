package com.example.mohammad.studentpa.Spending;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.mohammad.studentpa.R;

public class TakeSpendingItems extends AppCompatActivity {
    private static final String TAG = "TakeSpendingItems";

    private EditText editTextAmount;
    private EditText editTextAmountDetails;
    private EditText editTextCurrentDate;
    private TextView spendingTotal;
    private Button buttonAddItem;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout_spending);
        editTextAmount = findViewById(R.id.editTextSpendAmount);
        editTextAmountDetails = findViewById(R.id.editTextSpendDetails);
        editTextCurrentDate = findViewById(R.id.editTextSpendDate);
        buttonAddItem = findViewById(R.id.buttonAddItem);
        editTextCurrentDate = findViewById(R.id.editTextSpendDate);
        spendingTotal = findViewById(R.id.textViewTotal);
        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: started onClick item");
                //create a new row to add
                /*TableRow row = new TableRow(TakeSpendingItems.this);
                //add Layouts to your new row
                editTextAmount = new EditText(TakeSpendingItems.this);
                editTextAmountDetails = new EditText(TakeSpendingItems.this);
                //set input type to numerical
                editTextAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
                //Wrap_content layout
                editTextAmount.setLayoutParams
                        (new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                editTextAmount.setLayoutParams
                        (new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                row.addView(editTextAmount);
                row.addView(editTextAmountDetails);
                //add your new row to the TableLayout:
                TableLayout table = findViewById(R.id.tableLayout_spending);
                table.addView(row);*/
                addRow();
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

    public void addRow(){
        GridLayout layout = new GridLayout(this);
        layout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        editTextAmount= new EditText(TakeSpendingItems.this);
        editTextAmountDetails = new EditText (TakeSpendingItems.this);

        GridLayout.LayoutParams param =new GridLayout.LayoutParams();
        param.height = GridLayout.LayoutParams.WRAP_CONTENT;
        param.width = GridLayout.LayoutParams.WRAP_CONTENT;
        param.columnSpec = GridLayout.spec(0);
        param.rowSpec = GridLayout.spec(layout.getRowCount()+1);

        GridLayout.LayoutParams param2 =new GridLayout.LayoutParams();
        param2.height = GridLayout.LayoutParams.WRAP_CONTENT;
        param2.width = GridLayout.LayoutParams.WRAP_CONTENT;
        param2.columnSpec = GridLayout.spec(1);
        param2.rowSpec = GridLayout.spec(layout.getRowCount()+1);

        editTextAmount.setLayoutParams(param);
        editTextAmountDetails.setLayoutParams(param2);

        layout.addView(editTextAmount);
        layout.addView(editTextAmountDetails);
    }
}
