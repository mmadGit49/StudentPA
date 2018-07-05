package com.example.mohammad.studentpa.Reminders;

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
import com.example.mohammad.studentpa.db_classes.ReminderEntity;

import java.util.List;


public class RemindersRecyclerViewAdapter extends RecyclerView.Adapter<RemindersRecyclerViewAdapter.ViewHolder>  {

    private static final String TAG = "RemindRecycViewAdapter";

    private List<ReminderEntity> reminders;
    private Context context;

    public RemindersRecyclerViewAdapter(Context context, List<ReminderEntity> reminders) {
        this.reminders = reminders;
        this.context = context;
    }

    @NonNull
    @Override
    public RemindersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_reminders_display, parent, false);
        RemindersRecyclerViewAdapter.ViewHolder holder= new RemindersRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolderRemind: called.");//log tag
        if(reminders != null){
            //TODO: Possibly remove details and set text to "Click to view details"
            holder.textViewReminderTitle.setText(reminders.get(position).getReminderTitle());
            holder.textViewReminderDetail.setText("Click to view details...");
            holder.textViewReminderDate.setText(reminders.get(position).getReminderDate());
            holder.textViewReminderTime.setText(reminders.get(position).getReminderTime());
            holder.reminderLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //On item click, start note taker activity
                    Intent remindIntent= new Intent(context, TakeReminder.class);
                    context.startActivity(remindIntent);
                    Toast.makeText(context, reminders.get(position).getReminderTitle(), Toast.LENGTH_SHORT).show();
                }
            });
            holder.reminderLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Delete view and reminder
                    return false;
                }
            });
        }else{
            holder.textViewReminderTitle.setText("No Reminders here!");
        }
    }

    public void setReminders(List<ReminderEntity> reminderEntity){
        this.reminders = reminderEntity;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (reminders != null) {
            return reminders.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewReminderTitle;
        TextView textViewReminderDetail;
        TextView textViewReminderDate;
        TextView textViewReminderTime;
        LinearLayout reminderLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewReminderTitle = itemView.findViewById(R.id.textViewReminderTitle);
            textViewReminderDetail = itemView.findViewById(R.id.textViewReminderDetails);
            textViewReminderDate =  itemView.findViewById(R.id.textViewDisplayDate);
            textViewReminderTime = itemView.findViewById(R.id.textViewDisplayTime);
            reminderLayout = itemView.findViewById(R.id.recyclerview_reminders_display);
        }
    }
}
