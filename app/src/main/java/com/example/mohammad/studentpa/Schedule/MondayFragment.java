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

public class MondayFragment extends Fragment {

    private View mondayView;
    private FloatingActionButton fab;
    private LinearLayoutManager layoutManager;
    private List<String> titleNames=new ArrayList<>();


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
            public void onClick(View view) {
                titleNames.add("Placeholder");
                RecyclerView recyclerView =
                        mondayView.findViewById(R.id.recycler_view_schedule_monday);
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                ScheduleRecyclerViewAdapter adapter =
                        new ScheduleRecyclerViewAdapter(getActivity(), titleNames);
                recyclerView.setAdapter(adapter);
            }
        });

        return mondayView;
    }

}

