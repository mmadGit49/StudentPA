package com.example.mohammad.studentpa.Schedule;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.ScheduleEntity;
import com.example.mohammad.studentpa.db_classes.ScheduleViewModel;

import java.util.ArrayList;
import java.util.List;

public class ThursdayFragment extends Fragment {
    private View thursdayView;
    private FloatingActionButton fab;
    private LinearLayoutManager layoutManager;
    private ScheduleViewModel scheduleViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        thursdayView = inflater.inflate(R.layout.fragment_schedule_thursday, container, false);

        fab = thursdayView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //On item click, start note taker activity
                Intent scheduleIntent = new Intent(getActivity(), TakeSchedule.class);
                startActivity(scheduleIntent);
            }
        });

        initRecyclerView();

        return thursdayView;
    }

    public void initRecyclerView() {
        RecyclerView recyclerView =
                thursdayView.findViewById(R.id.recycler_view_schedule_thursday);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final ScheduleRecyclerViewAdapter adapter =
                new ScheduleRecyclerViewAdapter(getActivity(), new ArrayList<ScheduleEntity>());
        recyclerView.setAdapter(adapter);

        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        scheduleViewModel.getAllSchedules().observe(this, new Observer<List<ScheduleEntity>>() {
            @Override
            public void onChanged(@Nullable List<ScheduleEntity> scheduleEntities) {
                //Update the cached copy of words in the adapter
                adapter.setMilestone(scheduleEntities);
                Log.i("##############", scheduleEntities.size() + "");
            }
        });

    }
}
