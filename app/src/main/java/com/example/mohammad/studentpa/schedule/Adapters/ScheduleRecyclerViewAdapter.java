package com.example.mohammad.studentpa.schedule.Adapters;

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
import com.example.mohammad.studentpa.db_classes.entities.ScheduleEntity;
import com.example.mohammad.studentpa.schedule.TakeSchedule;

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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ScheduleRecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolderRemind: called.");//log tag

        if (schedules != null) {
            holder.textViewScheduleTitleDisplay.setText(schedules.get(position).getScheduleTitle());
            holder.textViewScheduleDateDisplay.setText(schedules.get(position).getDate());
            holder.textViewScheduleTimeFromDisplay.setText(schedules.get(position).getTimeFrom());
            holder.textViewScheduleDurationDisplay.setText(schedules.get(position).getDuration());
            holder.scheduleLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //On item click, start note taker activity
                    Intent scheduleIntent= new Intent(context, TakeSchedule.class);
                    scheduleIntent.putExtra("schedTitle", schedules.get(holder.getAdapterPosition())
                            .getScheduleTitle());
                    scheduleIntent.putExtra("schedDate", schedules.get(holder.getAdapterPosition())
                            .getDate());
                    scheduleIntent.putExtra("schedTime", schedules.get(holder.getAdapterPosition())
                            .getTimeFrom());
                    scheduleIntent.putExtra("schedDuration", schedules.get(holder.getAdapterPosition())
                            .getDuration());
                    scheduleIntent.putExtra("schedID", schedules.get(holder.getAdapterPosition())
                            .getScheduleID());
                    scheduleIntent.putExtra("schedDayOfWeek", schedules.get(holder.getAdapterPosition())
                            .getDayOfWeek());
                    context.startActivity(scheduleIntent);
                    Toast.makeText(context, "Edit Class", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            //If data is not ready yet
            holder.textViewScheduleTitleDisplay.setText(R.string.null_notes);
        }
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public void setClass(List<ScheduleEntity> scheduleEntities){
        this.schedules = scheduleEntities;
        notifyDataSetChanged();
    }

    public ScheduleEntity getScheduleAtPosition(int position){
        return schedules.get(position);
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
