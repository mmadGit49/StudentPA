package com.example.mohammad.studentpa.Milestones;

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
import com.example.mohammad.studentpa.db_classes.MilestoneEntity;

import java.util.ArrayList;

public class MilestoneRecyclerViewAdapter
        extends RecyclerView.Adapter<MilestoneRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MileRecycViewAdapter";

    private ArrayList<MilestoneEntity> titleNames;
    private ArrayList<MilestoneEntity> notes;
    private Context context;

    public MilestoneRecyclerViewAdapter(Context context,
                                        ArrayList<MilestoneEntity> titleNames,
                                        ArrayList<MilestoneEntity> notes) {
        this.titleNames = titleNames;
        this.notes = notes;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_note_display, parent, false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");//log tag

        holder.textViewTitle.setText(titleNames.get(position).getMilestoneTitle());
        holder.textViewMilestone.setText(notes.get(position).getMilestoneDetails());

        holder.milestoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On item click, start note taker activity
                Intent noteIntent= new Intent(context, TakeMilestoneNote.class);
                //TakeMilestoneNote class needs to know WHICH note is being accessed
                //noteIntent.putExtra("note_title", titleNames.get(position));
                //noteIntent.putExtra("note_details", notes.get(position));
                context.startActivity(noteIntent);

                Toast.makeText(context, notes.get(position).getMilestoneDetails(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return titleNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        TextView textViewMilestone;
        LinearLayout milestoneLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewMilestone = itemView.findViewById(R.id.textViewMilestone);
            milestoneLayout = itemView.findViewById(R.id.recyclerview_note_display);
        }
    }

}
