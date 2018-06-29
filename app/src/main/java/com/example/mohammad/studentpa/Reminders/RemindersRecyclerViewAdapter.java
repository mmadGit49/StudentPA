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

import java.util.ArrayList;


public class RemindersRecyclerViewAdapter extends RecyclerView.Adapter<RemindersRecyclerViewAdapter.ViewHolder>  {

    private static final String TAG = "RemindRecycViewAdapter";

    private ArrayList<String> titleNames;
    private ArrayList<String> remindDetails;
    private Context context;

    public RemindersRecyclerViewAdapter(Context context, ArrayList<String> titleNames, ArrayList<String> remindDetails) {
        this.titleNames = titleNames;
        this.remindDetails = remindDetails;
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

        holder.reminderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On item click, start note taker activity
                Intent remindIntent= new Intent(context, TakeReminder.class);
                context.startActivity(remindIntent);

                Toast.makeText(context, remindDetails.get(position), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return titleNames.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewReminderTitle;
        TextView textViewReminderDetail;
        LinearLayout reminderLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewReminderTitle = itemView.findViewById(R.id.textViewReminderTitle);
            textViewReminderDetail = itemView.findViewById(R.id.textViewReminderDetails);
            reminderLayout = itemView.findViewById(R.id.recyclerview_reminders_display);
        }
    }
}
