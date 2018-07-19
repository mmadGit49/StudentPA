package com.example.mohammad.studentpa.milestones;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.entities.MilestoneEntity;

import java.util.List;

public class MilestoneRecyclerViewAdapter
        extends RecyclerView.Adapter<MilestoneRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MileRecycViewAdapter";

    private List<MilestoneEntity> milestones;
    private Context context;


    public MilestoneRecyclerViewAdapter(Context context, List<MilestoneEntity> milestones) {
        this.milestones = milestones;
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
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder: called.");//log tag

        if(milestones != null) {
            holder.textViewTitle.setText(milestones.get(position).getMilestoneTitle());
            holder.textViewMilestone.setText(milestones.get(position).getMilestoneDetails());
            holder.milestoneLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Open note to edit
                    Intent noteIntent = new Intent(context, TakeMilestoneNote.class);
                    int mileID = milestones.get(holder.getAdapterPosition()).getMilestoneID();

                    noteIntent.putExtra("title", milestones
                            .get(holder.getAdapterPosition())
                            .getMilestoneTitle());
                    noteIntent.putExtra("details", milestones
                            .get(holder.getAdapterPosition())
                            .getMilestoneDetails());
                    noteIntent.putExtra("mileID", mileID);
                    context.startActivity(noteIntent);
                }
            });

        }else{
            //If data is not ready yet
            holder.textViewTitle.setText("No notes");
        }

    }

    void setMilestone(List<MilestoneEntity> milestoneEntities){
        this.milestones = milestoneEntities;
        notifyDataSetChanged();
    }

    public MilestoneEntity getMilestonesAtPosition (int position) {
        return milestones.get(position);
    }


    @Override
    public int getItemCount() {
        // getItemCount() is called many times, and when it is first called,
        // notes has not been updated (means initially, it's null, and we can't return null).
        if (milestones != null) {
            return milestones.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        TextView textViewMilestone;
        LinearLayout milestoneLayout;
        //For the create Milestone activity
        EditText editTextTitle;
        EditText editTextDetails;
        LinearLayout milestoneNoteLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewMilestone = itemView.findViewById(R.id.textViewMilestone);
            milestoneLayout = itemView.findViewById(R.id.recyclerview_note_display);
            //For the TakeMilestone activity:
            editTextTitle = itemView.findViewById(R.id.editTextTitle);
            editTextDetails = itemView.findViewById(R.id.editTextMilestone);
            milestoneNoteLayout = itemView.findViewById(R.id.milestone_note_layout);

        }
    }



}