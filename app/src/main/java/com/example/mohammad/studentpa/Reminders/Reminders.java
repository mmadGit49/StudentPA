package com.example.mohammad.studentpa.Reminders;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.ReminderEntity;
import com.example.mohammad.studentpa.db_classes.ReminderViewModel;

import java.util.ArrayList;
import java.util.List;


public class Reminders extends Fragment {

    private View remindView;
    private Context context;
    private FloatingActionButton fab;
    private LinearLayoutManager layoutManager;
    private ReminderViewModel reminderViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        remindView = inflater.inflate(R.layout.fragment_reminders, container, false);

        Toolbar toolbar = remindView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        toolbar.setTitle("Reminders");

        fab = remindView.findViewById(R.id.fab_reminders);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reminderIntent = new Intent (getActivity(), TakeReminder.class);
                startActivity(reminderIntent);
                initRecyclerView();
            }
        });

        return remindView;
    }
    public void initRecyclerView() {//initialises adapters, views and what have you's
        RecyclerView recyclerView = remindView.findViewById(R.id.recycler_view_reminders);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final RemindersRecyclerViewAdapter adapter = new RemindersRecyclerViewAdapter(this.getActivity(),
                new ArrayList<ReminderEntity>());
        recyclerView.setAdapter(adapter);

        reminderViewModel = ViewModelProviders.of(this).get(ReminderViewModel.class);
        reminderViewModel.getAllReminders().observe(this, new Observer<List<ReminderEntity>>() {
            @Override
            public void onChanged(@Nullable List<ReminderEntity> reminderEntities) {
                adapter.setReminders(reminderEntities);
            }
        });
    }

    public void setContext(Context context) {
        this.context = context;
    }//not quite necessary


}