package com.example.mohammad.studentpa.Reminders;

import android.content.Context;
import android.content.Intent;
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

import com.example.mohammad.studentpa.R;

import java.util.ArrayList;


public class Reminders extends Fragment {

    private View remindView;
    private Context context;
    private FloatingActionButton fab;
    private LinearLayoutManager layoutManager;
    private ArrayList<String> titleNames= new ArrayList<>();
    private ArrayList<String> remindDetails = new ArrayList<>();


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
                String title = null;
                String details= null;
                if(getArguments() != null) {
                    details = getArguments().getString("reminder_details");
                    title = getArguments().getString("reminder_title");
                    String date = getArguments().getString("reminder_date");
                    String time = getArguments().getString("reminder_time");
                }
                titleNames.add(title);
                remindDetails.add(details);
                initRecyclerView();
            }
        });

        return remindView;
    }
    public void initRecyclerView() {//initialises adapters, views and what have you's
        RecyclerView recyclerView = remindView.findViewById(R.id.recycler_view_reminders);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RemindersRecyclerViewAdapter adapter = new RemindersRecyclerViewAdapter(this.getActivity(), titleNames, remindDetails);
        recyclerView.setAdapter(adapter);
    }

    public void setContext(Context context) {
        this.context = context;
    }//not quite necessary


}