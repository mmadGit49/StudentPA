package com.example.mohammad.studentpa.Schedule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.Entities.ScheduleEntity;

import java.util.List;

public class MondayAdapter extends ScheduleRecyclerViewAdapter {

    private List<ScheduleEntity> schedules;
    Context context;

    public MondayAdapter(Context context, List<ScheduleEntity> schedules) {
        super(context, schedules);
        this.schedules = schedules;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_schedule_display, parent, false);
        MondayAdapter.ViewHolder holder= new ScheduleRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }
}
