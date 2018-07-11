package com.example.mohammad.studentpa.Schedule.Adapters;

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
import com.example.mohammad.studentpa.Schedule.TakeSchedule;
import com.example.mohammad.studentpa.db_classes.Entities.ScheduleEntity;

import java.util.List;

public class TuesdayAdapter extends RecyclerView.Adapter<TuesdayAdapter.ViewHolder>   {

    private static final String TAG = "TuesdayAdapterAdapter";

    private Context context;
    private List<ScheduleEntity> schedules;

    public TuesdayAdapter(Context context, List<ScheduleEntity> schedules) {
        this.context = context;
        this.schedules = schedules;
    }

    public TuesdayAdapter() {
    }

    @NonNull
    @Override
    public TuesdayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context.getApplicationContext())
                .inflate(R.layout.recyclerview_schedule_display, parent, false);
        TuesdayAdapter.ViewHolder holder= new TuesdayAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TuesdayAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolderRemind: called.");//log tag

        if (schedules != null) {
            holder.textViewScheduleTitleDisplay.setText(schedules.get(position).getScheduleTitle().toString());
            holder.textViewScheduleDateDisplay.setText(schedules.get(position).getDate().toString());
            holder.textViewScheduleTimeFromDisplay.setText(schedules.get(position).getTimeFrom().toString());
            holder.textViewScheduleDurationDisplay.setText(schedules.get(position).getDuration().toString());
            holder.scheduleLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //On item click, start note taker activity
                    Intent scheduleIntent= new Intent(context, TakeSchedule.class);
                    scheduleIntent.putExtra("schedTitle", schedules.get(position)
                            .getScheduleTitle());
                    scheduleIntent.putExtra("schedDate", schedules.get(position)
                            .getDate());
                    scheduleIntent.putExtra("schedTime", schedules.get(position)
                            .getTimeFrom());
                    scheduleIntent.putExtra("schedDuration", schedules.get(position)
                            .getDuration());
                    scheduleIntent.putExtra("schedID", schedules.get(position)
                            .getScheduleID());

                    context.startActivity(scheduleIntent);
                    Toast.makeText(context, "Edit Class", Toast.LENGTH_SHORT).show();
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
