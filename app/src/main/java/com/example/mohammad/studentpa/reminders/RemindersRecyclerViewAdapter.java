package com.example.mohammad.studentpa.reminders;

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

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.entities.ReminderEntity;
import com.example.mohammad.studentpa.util.LocalData;

import java.util.List;


public class RemindersRecyclerViewAdapter extends RecyclerView.Adapter<RemindersRecyclerViewAdapter.ViewHolder>  {

    private static final String TAG = "RemindRecycViewAdapter";

    private List<ReminderEntity> reminders;
    private Context context;

    RemindersRecyclerViewAdapter(Context context, List<ReminderEntity> reminders) {
        this.reminders = reminders;
        this.context = context;
    }

    @NonNull
    @Override
    public RemindersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_reminders_display, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolderRemind: called.");//log tag
        if(reminders != null){
            //Dirty quick fix to set reminderID
            LocalData localData = new LocalData(context);
            localData.set_remindID(reminders.get(holder.getAdapterPosition()).getReminderID());

            holder.textViewReminderTitle.setText(reminders.get(position).getReminderTitle());
            holder.textViewReminderDetail.setText(R.string.Alert_details);
            holder.textViewReminderDate.setText(reminders.get(position).getReminderDate());
            holder.textViewReminderTime.setText(reminders.get(position).getReminderTime());
            holder.reminderLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //On item click, start note taker activity
                    Intent remindIntent= new Intent(context, TakeReminder.class);
                    remindIntent.putExtra("remindTitle", reminders.get(holder.getAdapterPosition())
                            .getReminderTitle());
                    remindIntent.putExtra("remindDetails", reminders.get(holder.getAdapterPosition())
                            .getReminderDetails());
                    remindIntent.putExtra("remindDate", reminders.get(holder.getAdapterPosition())
                            .getReminderDate());
                    remindIntent.putExtra("remindTime", reminders.get(holder.getAdapterPosition())
                            .getReminderTime());
                    remindIntent.putExtra("remindID", reminders.get(holder.getAdapterPosition())
                            .getReminderID());
                    context.startActivity(remindIntent);
                }
            });
        }else{
            holder.textViewReminderTitle.setText(R.string.null_notes);
        }
    }

    public void setReminders(List<ReminderEntity> reminderEntity){
        this.reminders = reminderEntity;
        notifyDataSetChanged();
    }

    public ReminderEntity getReminderAtPosition(int position){//pretty self explanatory
        return reminders.get(position);
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
