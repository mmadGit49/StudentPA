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

import java.util.List;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ViewHolder>   {

    private static final String TAG = "ScheduleRecyclerViewAda";

    private Context context;
    private List<String> titleDisplay;

    public ScheduleRecyclerViewAdapter(Context context, List<String> titleDisplay) {
        this.context = context;
        this.titleDisplay = titleDisplay;
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

        holder.scheduleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On item click, start note taker activity
                Intent scheduleIntent= new Intent(context, TakeSchedule.class);
                context.startActivity(scheduleIntent);
                Toast.makeText(context, "Edit class", Toast.LENGTH_SHORT).show();
            }
        });

        holder.textViewScheduleTitleDisplay.setText(titleDisplay.get(position));
    }

    @Override
    public int getItemCount() {
        return titleDisplay.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewScheduleTitleDisplay;
        TextView textViewScheduleTimeFromDisplay;
        TextView textViewScheduleTimeToDisplay;
        TextView textViewScheduleDateDisplay;
        LinearLayout scheduleLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewScheduleTitleDisplay = itemView.findViewById(R.id.textViewScheduleTitleDisplay);
            textViewScheduleTimeFromDisplay = itemView.findViewById(R.id.textViewScheduleTimeFromDisplay);
            textViewScheduleTimeToDisplay = itemView.findViewById(R.id.textViewScheduleTimeToDisplay);
            textViewScheduleDateDisplay = itemView.findViewById(R.id.textViewScheduleDateDisplay);
            scheduleLayout = itemView.findViewById(R.id.recyclerview_schedule_display);
        }
    }
}
