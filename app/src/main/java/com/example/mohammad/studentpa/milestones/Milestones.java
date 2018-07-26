package com.example.mohammad.studentpa.milestones;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.entities.MilestoneEntity;
import com.example.mohammad.studentpa.db_classes.MilestoneViewModel;
import com.example.mohammad.studentpa.reminders.LocalData;

import java.util.ArrayList;
import java.util.List;

public class Milestones extends Fragment {

    private View mileView;
    private MilestoneViewModel milestoneViewModel;

    // public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private static final String TAG = "milestone fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mileView= inflater.inflate(R.layout.fragment_milestones, container, false);

        Toolbar toolbar = mileView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        toolbar.setTitle("Milestones");

        //for the floating action button:
        FloatingActionButton fab = mileView.findViewById(R.id.fab_add_note);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //On button click, start note taker activity
                Intent noteIntent = new Intent(getActivity(), TakeMilestoneNote.class);
                startActivity(noteIntent);
            }
        });

        initRecyclerView();//RecyclerView Adapter start

        return mileView;
    }

    public void initRecyclerView() {//initialises adapters, views and what have you's
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = mileView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final MilestoneRecyclerViewAdapter adapter
                = new MilestoneRecyclerViewAdapter(this.getActivity(),
                new ArrayList<MilestoneEntity>());
        recyclerView.setAdapter(adapter);

        milestoneViewModel = ViewModelProviders.of(this).get(MilestoneViewModel.class);
//        milestoneViewModel.getAllMilestones().observe(this, new Observer<List<MilestoneEntity>>() {
//            @Override
//            public void onChanged(@Nullable List<MilestoneEntity> milestoneEntities) {
//                //Update the cached copy of words in the adapter
//                adapter.setMilestone(milestoneEntities);
//                Log.i("##############",milestoneEntities.size()+"");
//            }
//        });

        //FIXME: ONLY SHOW ITEMS FOR CURRENT USER

        LocalData local = new LocalData(getActivity());
        milestoneViewModel.getAllMilestonesPerUser(local.get_user()).observe(this, new Observer<List<MilestoneEntity>>() {
            @Override
            public void onChanged(@Nullable List<MilestoneEntity> milestoneEntities) {
                adapter.setMilestone(milestoneEntities);
            }
        });

        // Add the functionality to swipe items in the recycler view to delete that item
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
                        final MilestoneEntity milestone = adapter.getMilestonesAtPosition(position);

                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(getContext());
                        }
                        builder.setTitle("Delete Item")
                                .setMessage("Are you sure you want to delete?")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        milestoneViewModel.delete(milestone);
                                        // Delete the item
                                        Toast.makeText(getContext(), "Note deleted!", Toast.LENGTH_SHORT).show();
                                        Log.i(TAG, "onSwiped: Note Deleted");
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Reinsert the item
                                        milestoneViewModel.insert(milestone);
                                    }
                                }).show();
                    }
                });
        helper.attachToRecyclerView(recyclerView);
    }
}
