package com.example.mohammad.studentpa;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Spending extends Fragment {

    private View spendView;
    private Context context;
    private FloatingActionButton fab;
    private LinearLayoutManager layoutManager;
    private ArrayList<String> spendDates = new ArrayList<>();
    private ArrayList<String> spendTotals = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        spendView = inflater.inflate(R.layout.fragment_reminders, container, false);
        Toolbar toolbar = spendView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        //Floating action Button action
        fab = spendView.findViewById(R.id.fab_spend);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRecyclerView();
            }
        });

        return spendView;
    }

    public void initRecyclerView() {//initialises adapters, views and what have you's
        RecyclerView recyclerView = spendView.findViewById(R.id.spending_recycler_view);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //FIXME: Spending recyclerViewAdapter not recognised
        //SpendingRecyclerViewAdapter adapter = new SpendingRecyclerViewAdapter(this.getActivity(),
               // spendDates, spendTotals);
        //recyclerView.setAdapter(adapter);
    }

}
