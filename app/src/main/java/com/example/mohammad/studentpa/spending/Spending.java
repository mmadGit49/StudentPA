package com.example.mohammad.studentpa.spending;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.entities.SpendingEntity;
import com.example.mohammad.studentpa.db_classes.SpendingViewModel;

import java.util.ArrayList;
import java.util.List;

public class Spending extends Fragment{

    private View spendView;
    private Context context;
    private TextView textViewDate;
    private FloatingActionButton fab;
    private LinearLayoutManager layoutManager;
    private ArrayList<String> spendDates = new ArrayList<>();
    private ArrayList<String> spendTotals = new ArrayList<>();
    private SpendingViewModel spendingViewModel;


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
        toolbar.setTitle("spending");

        //Floating action Button action
        fab = spendView.findViewById(R.id.fab_spend);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent spendIntent = new Intent(getActivity(), TakeSpendingItems.class);
                startActivity(spendIntent);
            }
        });
        initSpendRecyclerView();

        return spendView;
    }

    public void initSpendRecyclerView() {//initialises adapters, views and what have you'
        Log.d(TAG, "initSpendRecyclerView: initRecycStarted.");
        RecyclerView recyclerView = spendView.findViewById(R.id.spending_recycler_view);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final SpendingRecyclerViewAdapter adapter = new SpendingRecyclerViewAdapter(this.getActivity(),
                new ArrayList<SpendingEntity>());
        recyclerView.setAdapter(adapter);

        spendingViewModel = ViewModelProviders.of(this).get(SpendingViewModel.class);
        spendingViewModel.getAllSpendings().observe(this, new Observer<List<SpendingEntity>>() {
            @Override
            public void onChanged(@Nullable List<SpendingEntity> spendingEntities) {
                //Update the cached copy of words in the adapter
                adapter.setItems(spendingEntities);
                Log.i("##############",spendingEntities.size()+"");
            }
        });

        // Add the functionality to swipe items in the recycler view to delete that item
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
                        // Delete the word
                        int position = viewHolder.getAdapterPosition();
                        final SpendingEntity spending = adapter.getSpendingAtPosition(position);


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
                                        spendingViewModel.delete(spending);
                                        Toast.makeText(getContext(), "Item deleted!", Toast.LENGTH_SHORT).show();
                                        Log.i(TAG, "onSwiped: Item Deleted");
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Reinsert the item
                                        spendingViewModel.insert(spending);
                                    }
                                }).show();
                    }

                });
        helper.attachToRecyclerView(recyclerView);
    }

}
