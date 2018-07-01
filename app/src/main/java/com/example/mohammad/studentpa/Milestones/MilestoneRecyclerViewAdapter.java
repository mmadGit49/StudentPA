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

import java.util.List;

public class MilestoneRecyclerViewAdapter
        extends RecyclerView.Adapter<MilestoneRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MileRecycViewAdapter";

    private List<MilestoneEntity> titleNames;
    private List<MilestoneEntity> notes;
    private List<MilestoneEntity> milestones;
    private Context context;
    private MilestoneEntity milestoneEntity;

    public MilestoneRecyclerViewAdapter(Context context,
                                        List<MilestoneEntity> titleNames,
                                        List<MilestoneEntity> notes) {
        this.titleNames = titleNames;
        this.notes = notes;
        this.context = context;
    }

    public MilestoneRecyclerViewAdapter(Context context, MilestoneEntity milestoneEntity) {
        this.milestoneEntity = milestoneEntity;
        this.context = context;

    }

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");//log tag

        if(titleNames != null  || notes != null) {

            holder.textViewTitle.setText(milestoneEntity.getMilestoneTitle());
            holder.textViewMilestone.setText(milestoneEntity.getMilestoneDetails());
            holder.milestoneLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Open note to edit
                    Intent noteIntent = new Intent(context, TakeMilestoneNote.class);
                    noteIntent.putExtra("note_title", titleNames.get(position).toString());
                    noteIntent.putExtra("note_details", notes.get(position).toString());
                    context.startActivity(noteIntent);

                }
            });
            Toast.makeText(context, notes.get(position).getMilestoneDetails(),
                    Toast.LENGTH_SHORT).show();
        }else{
            //If data is not ready yet
            holder.textViewTitle.setText("No notes");
        }

    }

    //not sure what happens here
    void setWords(List<MilestoneEntity> milestoneEntities){
        titleNames= milestoneEntities;
        notes = milestoneEntities;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // getItemCount() is called many times, and when it is first called,
        // notes has not been updated (means initially, it's null, and we can't return null).
        if (titleNames != null || notes != null) {
            return notes.size();
        } else {
            return 0;
        }
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