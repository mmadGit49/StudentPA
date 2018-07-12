package com.example.mohammad.studentpa.Schedule.Adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.Schedule.Schedule;
import com.example.mohammad.studentpa.Schedule.TakeSchedule;
import com.example.mohammad.studentpa.db_classes.Entities.ScheduleEntity;
import com.example.mohammad.studentpa.db_classes.ScheduleViewModel;

import java.util.List;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ViewHolder>   {

    private static final String TAG = "ScheduleRecyclerViewAda";

    private Context context;
    private List<ScheduleEntity> schedules;
    private ScheduleViewModel scheduleViewModel;


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
                    context.startActivity(scheduleIntent);
                    Toast.makeText(context, "Edit Class", Toast.LENGTH_SHORT).show();
                }
            });


            holder.scheduleLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
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
                                    final ScheduleEntity scheduleEntity = getScheduleAtPosition(position);
                                    AlertDialog.Builder builder;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                                    } else {
                                        builder = new AlertDialog.Builder(context);
                                    }
                                    scheduleViewModel = ViewModelProviders.of(new Schedule()).get(ScheduleViewModel.class);

                                    builder.setTitle("Delete Item")
                                            .setMessage("Are you sure you want to delete?")
                                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    scheduleViewModel.delete(scheduleEntity);
                                                    // Delete the word
                                                    Toast.makeText(context, "Class deleted!", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // Reinsert the word
                                                    scheduleViewModel.insert(scheduleEntity);
                                                }
                                            }).show();
                                }
                            });
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
