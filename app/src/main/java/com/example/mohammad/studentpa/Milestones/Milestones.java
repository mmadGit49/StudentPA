package com.example.mohammad.studentpa.Milestones;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.MilestoneEntity;
import com.example.mohammad.studentpa.db_classes.MilestoneViewModel;

import java.util.ArrayList;
import java.util.List;

public class Milestones extends Fragment {

    private View mileView;
    private LinearLayoutManager layoutManager;
    private List<MilestoneEntity> milestones = new ArrayList<>();
    private MilestoneViewModel milestoneViewModel;
    private FloatingActionButton fab;
    private MilestoneEntity milestoneEntity ;

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
        fab = mileView.findViewById(R.id.fab_add_note);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //On item click, start note taker activity
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
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final MilestoneRecyclerViewAdapter adapter
                = new MilestoneRecyclerViewAdapter(this.getActivity(),
                new ArrayList<MilestoneEntity>());
        recyclerView.setAdapter(adapter);
        milestoneViewModel = ViewModelProviders.of(this).get(MilestoneViewModel.class);

        milestoneViewModel.getAllMilestones().observe(this, new Observer<List<MilestoneEntity>>() {
            @Override
            public void onChanged(@Nullable List<MilestoneEntity> milestoneEntities) {
                //Update the cached copy of words in the adapter
                adapter.setMilestone(milestoneEntities);
                Log.i("##############",milestoneEntities.size()+"");
            }
        });
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode ==RESULT_OK) {
            MilestoneEntity milestoneEntity = new MilestoneEntity(data.getStringExtra(TakeMilestoneNote.));
            milestoneViewModel.insert(milestoneEntity);
        } else {
            Toast.makeText(
                    getContext(),
                    R.string.text_header,
                    Toast.LENGTH_LONG).show();
        }
    }*/

}
