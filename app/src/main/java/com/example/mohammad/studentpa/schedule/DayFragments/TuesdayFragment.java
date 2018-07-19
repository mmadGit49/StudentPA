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
import com.example.mohammad.studentpa.schedule.Adapters.TuesdayAdapter;
import com.example.mohammad.studentpa.schedule.TakeSchedule;
import com.example.mohammad.studentpa.db_classes.entities.ScheduleEntity;
import com.example.mohammad.studentpa.db_classes.ScheduleViewModel;

import java.util.ArrayList;
import java.util.List;

public class TuesdayFragment extends Fragment {
    private View tuesdayView;
    private FloatingActionButton fab;
    private LinearLayoutManager layoutManager;
    private ScheduleViewModel scheduleViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        tuesdayView = inflater.inflate(R.layout.fragment_schedule_tuesday, container, false);

        fab = tuesdayView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //On item click, start note taker activity
                Intent scheduleIntent = new Intent(getActivity(), TakeSchedule.class);
                startActivity(scheduleIntent);
            }
        });

        RecyclerView recyclerView = tuesdayView.findViewById(R.id.recycler_view_schedule_tuesday);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final TuesdayAdapter adapter = new TuesdayAdapter(getContext(),
                new ArrayList<ScheduleEntity>());
        recyclerView.setAdapter(adapter);

        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        scheduleViewModel.getAllSchedules().observe(this, new Observer<List<ScheduleEntity>>() {
            @Override
            public void onChanged(@Nullable List<ScheduleEntity> scheduleEntities) {
                //Update the cached copy of words in the adapter
                adapter.setClass(scheduleEntities);
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

                    }
                });
        helper.attachToRecyclerView(recyclerView);
        return tuesdayView;
    }

}