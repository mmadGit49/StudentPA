package com.example.mohammad.studentpa.Schedule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammad.studentpa.R;

import java.util.ArrayList;
import java.util.List;

public class WednesdayFragment extends Fragment {
    private View wednesdayView;
    private FloatingActionButton fab;
    private LinearLayoutManager layoutManager;
    private List<String> titleNames=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        wednesdayView = inflater.inflate(R.layout.fragment_schedule_wednesday, container, false);
        fab= getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleNames.add("Monday");
                initRecyclerView();
            }
        });

        return wednesdayView;
    }

    public void initRecyclerView() {//initialises adapters, views and what have you's
        RecyclerView recyclerView = wednesdayView.findViewById(R.id.recycler_view_schedule_wednesday);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ScheduleRecyclerViewAdapter adapter = new ScheduleRecyclerViewAdapter(this.getActivity(), titleNames);
        recyclerView.setAdapter(adapter);
    }
}
