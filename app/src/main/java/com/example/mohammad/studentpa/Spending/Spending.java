package com.example.mohammad.studentpa.Spending;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammad.studentpa.R;

import java.util.ArrayList;

public class Spending extends Fragment {

    private View spendView;
    private Context context;
    private FloatingActionButton fab;
    private LinearLayoutManager layoutManager;
    private ArrayList<String> spendDates = new ArrayList<>();
    private ArrayList<String> spendTotals = new ArrayList<>();


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
        fab = spendView.findViewById(R.id.fab_spend);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spendDates.add("Test ME");
                spendTotals.add("3500 spent today");
                initSpendRecyclerView();
            }
        });

        return spendView;
    }
    
    public void initSpendRecyclerView() {//initialises adapters, views and what have you'
        Log.d(TAG, "initSpendRecyclerView: initRecycStarted.");
        RecyclerView recyclerView = spendView.findViewById(R.id.spending_recycler_view);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        SpendingRecyclerViewAdapter adapter = new SpendingRecyclerViewAdapter(this.getActivity(),
               spendDates, spendTotals);
        recyclerView.setAdapter(adapter);
    }

}
