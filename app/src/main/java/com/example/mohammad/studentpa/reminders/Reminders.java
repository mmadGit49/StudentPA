package com.example.mohammad.studentpa.reminders;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.ReminderViewModel;
import com.example.mohammad.studentpa.db_classes.entities.ReminderEntity;
import com.example.mohammad.studentpa.util.AlarmReceiver;
import com.example.mohammad.studentpa.util.LocalData;
import com.example.mohammad.studentpa.util.NotificationScheduler;

import java.util.ArrayList;
import java.util.List;


public class Reminders extends Fragment {

    private View remindView;
    private FloatingActionButton fab;
    private LinearLayoutManager layoutManager;
    private ReminderViewModel reminderViewModel;
    private static final String TAG = "reminders";

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
            }
        });

        initRecyclerView();
        return remindView;
    }
    public void initRecyclerView() {//initialises adapters, views and what have you's
        RecyclerView recyclerView = remindView.findViewById(R.id.recycler_view_reminders);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final RemindersRecyclerViewAdapter adapter = new RemindersRecyclerViewAdapter(this.getActivity(),
                new ArrayList<ReminderEntity>());
        recyclerView.setAdapter(adapter);
        //where the db gets involved
        LocalData localData = new LocalData(getActivity());
        reminderViewModel = ViewModelProviders.of(this).get(ReminderViewModel.class);
        reminderViewModel.getAllRemindersByID(localData.get_user()).observe(this, new Observer<List<ReminderEntity>>() {
            @Override
            public void onChanged(@Nullable List<ReminderEntity> reminderEntities) {
                adapter.setReminders(reminderEntities);

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
                        final ReminderEntity reminder = adapter.getReminderAtPosition(position);

                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(getContext(),
                                    android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(getContext());
                        }
                        builder.setTitle("Delete Item")
                                .setMessage("Are you sure you want to delete?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        reminderViewModel.delete(reminder);
                                        NotificationScheduler.cancelReminder(getContext(),
                                                AlarmReceiver.class);
                                        Log.d(TAG, "onClick: reminder cancelled");
                                        // Delete the item
                                        Toast.makeText(getContext(), "Reminder deleted!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Reinsert the item
                                        reminderViewModel.insert(reminder);
                                    }
                                }).show();
                    }
                });
        helper.attachToRecyclerView(recyclerView);
    }




}