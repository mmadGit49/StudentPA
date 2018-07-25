package com.example.mohammad.studentpa.spending;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.SpendingViewModel;
import com.example.mohammad.studentpa.db_classes.entities.SpendingEntity;
import com.example.mohammad.studentpa.reminders.LocalData;
import com.example.mohammad.studentpa.util.DatePickerFragment;

import java.util.ArrayList;
import java.util.List;

public class Spending extends Fragment implements DatePickerDialog.OnDateSetListener{

    private View spendView;
    private Context context;
    private TextView textViewDate;
    private EditText editTextAmount;
    private EditText editTextAmountDetails;
    private TextView spendingTotal;

    private SpendingViewModel spendingViewModel;
    private LocalData localData;


    private static final String TAG = "SpendRecycVAdapt";
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        spendView = inflater.inflate(R.layout.fragment_spending, container, false);

        Toolbar toolbar = spendView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);//sets menu icon to action bar
        toolbar.setTitle("Spending");

        //Floating action Button action
//        fab = spendView.findViewById(R.id.fab_spend);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent spendIntent = new Intent(getActivity(), TakeSpendingItems.class);
//                startActivity(spendIntent);
//            }
//        });
//        initSpendRecyclerView();

        localData = new LocalData(getActivity());

        editTextAmount = spendView.findViewById(R.id.editTextSpendAmount);
        editTextAmountDetails = spendView.findViewById(R.id.editTextSpendDetails);
        textViewDate = spendView.findViewById(R.id.textViewSpendDate);
        spendingTotal= spendView.findViewById(R.id.textViewTotal);
        Button sumItems = spendView.findViewById(R.id.buttonSumItems);

        spendingViewModel = ViewModelProviders.of(this).get(SpendingViewModel.class);

        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
                textViewDate.setText(localData.get_date());
            }
        });

        Button buttonAddItem = spendView.findViewById(R.id.buttonAddItem);
        Button buttonDeleteItem = spendView.findViewById(R.id.buttonRemoveItem);
        spendingTotal = spendView.findViewById(R.id.textViewTotal);

        spendingViewModel = ViewModelProviders.of(this).get(SpendingViewModel.class);


        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(editTextAmount.getText().toString()) ||
                        TextUtils.isEmpty(editTextAmountDetails.getText().toString()) ||
                        TextUtils.isEmpty(textViewDate.getText().toString())){
                    Toast.makeText(getActivity(), "Required data missing",
                            Toast.LENGTH_SHORT).show();
                }else{

                    String amount = editTextAmount.getText().toString();
                    double amountDouble = Double.parseDouble(amount);
                    String details = editTextAmountDetails.getText().toString();
                    double total = 0;
                    String totalSpent = Double.toString(total);
                    String date = localData.get_date();

                    spendingViewModel.insert(new SpendingEntity(date, amount, details, totalSpent));

                    editTextAmount.setText("");
                    editTextAmountDetails.setText("");
                }
            }
        });

        buttonDeleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Swipe item to delete...",
                            Toast.LENGTH_SHORT).show();
                }
        });

        sumItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spendingViewModel.getAllSpendingByDate(localData.get_date())
                            .observe(getActivity(), new Observer<List<SpendingEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<SpendingEntity> spendingEntities) {
                        String storedAmount;
                        float amount;
                        float total = 0;
                        if(spendingEntities != null){
                            for(int i = 0; i < spendingEntities.size(); i++){
                                storedAmount = spendingEntities.get(i).getSpendAmount();
                                amount = Float.parseFloat(storedAmount);
                                total += amount;
                            }

                            localData.set_amount(total);
                        }
                    }
                });
                    String totalAmount = Float.toString(localData.get_amount());
                    spendingTotal.setText(totalAmount);
                }
            });


        initRecyclerView();

        return spendView;
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String stringDate= dayOfMonth + " / " + month + " / " + year;
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = spendView.findViewById(R.id.recycler_view_spending_items);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        final AmountAdapter adapter =  new AmountAdapter(context, new ArrayList<SpendingEntity>());
        recyclerView.setAdapter(adapter);

        spendingViewModel = ViewModelProviders.of(this).get(SpendingViewModel.class);
        spendingViewModel.getAllSpending().observe(this, new Observer<List<SpendingEntity>>() {
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
                            builder = new AlertDialog.Builder(getActivity(),
                                    android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(getActivity());
                        }
                        builder.setTitle("Delete Item")
                                .setMessage("Are you sure you want to delete?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        spendingViewModel.delete(spendingEntity);
                                        Toast.makeText(getActivity(),
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
}


//    public void initSpendRecyclerView() {//initialises adapters, views and what have you'
//        Log.d(TAG, "initSpendRecyclerView: initRecycStarted.");
//        RecyclerView recyclerView = spendView.findViewById(R.id.spending_recycler_view);
//        layoutManager = new LinearLayoutManager(this.getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        final SpendingRecyclerViewAdapter adapter = new SpendingRecyclerViewAdapter(this.getActivity(),
//                new ArrayList<SpendingEntity>());
//        recyclerView.setAdapter(adapter);
//
//        spendingViewModel = ViewModelProviders.of(this).get(SpendingViewModel.class);
//        spendingViewModel.getAllSpending().observe(this, new Observer<List<SpendingEntity>>() {
//            @Override
//            public void onChanged(@Nullable List<SpendingEntity> spendingEntities) {
//                //Update the cached copy of words in the adapter
//                adapter.setItems(spendingEntities);
//                Log.i("##############",spendingEntities.size()+"");
//            }
//        });
//
//        // Add the functionality to swipe items in the recycler view to delete that item
//        ItemTouchHelper helper = new ItemTouchHelper(
//                new ItemTouchHelper.SimpleCallback(0,
//                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//                    @Override
//                    public boolean onMove(RecyclerView recyclerView,
//                                          RecyclerView.ViewHolder viewHolder,
//                                          RecyclerView.ViewHolder target) {
//                        return false;
//                    }
//
//                    @Override
//                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
//                                         int direction) {
//                        // Delete the word
//                        int position = viewHolder.getAdapterPosition();
//                        final SpendingEntity spending = adapter.getSpendingAtPosition(position);
//
//
//                        AlertDialog.Builder builder;
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
//                        } else {
//                            builder = new AlertDialog.Builder(getContext());
//                        }
//                        builder.setTitle("Delete Item")
//                                .setMessage("Are you sure you want to delete?")
//                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        spendingViewModel.delete(spending);
//                                        Toast.makeText(getContext(), "Item deleted!", Toast.LENGTH_SHORT).show();
//                                        Log.i(TAG, "onSwiped: Item Deleted");
//                                    }
//                                })
//                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // Reinsert the item
//                                        spendingViewModel.insert(spending);
//                                    }
//                                }).show();
//                    }
//
//                });
//        helper.attachToRecyclerView(recyclerView);
//    }
