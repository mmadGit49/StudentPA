package com.example.mohammad.studentpa.schedule.DayFragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.ScheduleViewModel;
import com.example.mohammad.studentpa.db_classes.entities.ScheduleEntity;
import com.example.mohammad.studentpa.reminders.LocalData;
import com.example.mohammad.studentpa.schedule.Adapters.ScheduleRecyclerViewAdapter;
import com.example.mohammad.studentpa.schedule.TakeSchedule;

import java.util.ArrayList;
import java.util.List;

public class MondayFragment extends Fragment {

    private View mondayView;
    private FloatingActionButton fab;
    private LinearLayoutManager layoutManager;
    private ScheduleViewModel scheduleViewModel;
    private String dayOfWeek = "Monday";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mondayView = inflater.inflate(R.layout.fragment_schedule_monday,
                container, false);
        fab= mondayView.findViewById(R.id.fab_monday);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//Only for new item, on update use stored value
                //On item click, start note taker activity
                Intent scheduleIntent = new Intent(getActivity(), TakeSchedule.class);
                Bundle bundle = new Bundle();
                bundle.putString("dayOfWeek", dayOfWeek);
                scheduleIntent.putExtras(bundle);
                startActivity(scheduleIntent);
            }
        });

        RecyclerView recyclerView =
                mondayView.findViewById(R.id.recycler_view_schedule_monday);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final ScheduleRecyclerViewAdapter adapter = new ScheduleRecyclerViewAdapter(getActivity(),
                new ArrayList<ScheduleEntity>());
        recyclerView.setAdapter(adapter);

        LocalData localData = new LocalData(getActivity());
        int userID = localData.get_user();

        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        scheduleViewModel.getAllSchedulesByDay(dayOfWeek, userID).observe(this, new Observer<List<ScheduleEntity>>() {
            @Override
            public void onChanged(@Nullable List<ScheduleEntity> scheduleEntities) {
                adapter.setClass(scheduleEntities);
                adapter.notifyDataSetChanged();
            }

        });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
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
                        final ScheduleEntity scheduleEntity = adapter.getScheduleAtPosition(position);
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
                                        scheduleViewModel.delete(scheduleEntity);
                                        Toast.makeText(getContext(), "Class deleted!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        scheduleViewModel.insert(scheduleEntity);
                                    }
                                }).show();
                        // Delete the word
                    }


                });
        helper.attachToRecyclerView(recyclerView);
        return mondayView;
    }


}

