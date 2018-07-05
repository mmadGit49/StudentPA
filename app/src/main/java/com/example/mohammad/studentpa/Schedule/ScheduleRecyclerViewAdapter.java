package com.example.mohammad.studentpa.Schedule;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.ScheduleEntity;

import java.util.List;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ViewHolder>   {

    private static final String TAG = "ScheduleRecyclerViewAda";

    private Context context;
    private List<ScheduleEntity> schedules;

    public ScheduleRecyclerViewAdapter(Context context, List<ScheduleEntity> schedules) {
        this.context = context;
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public ScheduleRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_schedule_display, parent, false);
        ScheduleRecyclerViewAdapter.ViewHolder holder= new ScheduleRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleRecyclerViewAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolderRemind: called.");//log tag

        if (schedules != null) {
            holder.textViewScheduleTitleDisplay.setText(schedules.get(position).getScheduleTitle().toString());
            holder.textViewScheduleDateDisplay.setText(schedules.get(position).getDate().toString());
            holder.textViewScheduleTimeFromDisplay.setText(schedules.get(position).getDate().toString());
            holder.textViewScheduleDurationDisplay.setText(schedules.get(position).getDuration().toString());
            holder.scheduleLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //On item click, start note taker activity
                    Intent scheduleIntent= new Intent(context, TakeSchedule.class);
                    context.startActivity(scheduleIntent);
                    Toast.makeText(context, "Edit Class", Toast.LENGTH_SHORT).show();
                }
            });
            holder.scheduleLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //TODO: SETUP TO PROMPT AND DELETE
                    Toast.makeText(context, "Long click!",
                            Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

        } else {
            //If data is not ready yet
            holder.textViewScheduleTitleDisplay.setText("No notes");
        }
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    void setMilestone(List<ScheduleEntity> scheduleEntities){
        this.schedules = scheduleEntities;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewScheduleTitleDisplay;
        TextView textViewScheduleTimeFromDisplay;
        TextView textViewScheduleDurationDisplay;
        TextView textViewScheduleDateDisplay;
        LinearLayout scheduleLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewScheduleTitleDisplay = itemView.findViewById(R.id.textViewScheduleTitleDisplay);
            textViewScheduleTimeFromDisplay = itemView.findViewById(R.id.textViewScheduleTimeFromDisplay);
            textViewScheduleDurationDisplay = itemView.findViewById(R.id.textViewScheduleDurationDisplay);
            textViewScheduleDateDisplay = itemView.findViewById(R.id.textViewScheduleDateDisplay);
            scheduleLayout = itemView.findViewById(R.id.recyclerview_schedule_display);
        }
    }
}
