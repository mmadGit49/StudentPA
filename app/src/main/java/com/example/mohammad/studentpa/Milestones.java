package com.example.mohammad.studentpa;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class Milestones extends Fragment {

    private EditText milestoneNote;
    private EditText milestoneTitle;
    private View mileView;
    private LinearLayoutManager layoutManager;
    private Context context;
    private ArrayList<String> titleNames= new ArrayList<>();
    private ArrayList<String> notes = new ArrayList<>();
    private FloatingActionButton fab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mileView= inflater.inflate(R.layout.fragment_milestones, container, false);
        milestoneTitle= mileView.findViewById(R.id.editTextTitle);
        milestoneNote= mileView.findViewById(R.id.editTextMilestone);

        Toolbar toolbar = mileView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        fab = mileView.findViewById(R.id.fab_add_note);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleNames.add("New note");
                notes.add("Tap to edit new note...");
                initRecyclerView();
            }
        });
        return mileView;
    }

    /*private void initNotes(){
        titleNames.add("Hello");
        notes.add("Test1");

        initRecyclerView();
    }*/

    public void initRecyclerView() {//initialises adapters, views and what have you's
        RecyclerView recyclerView = mileView.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MilestoneRecyclerViewAdapter adapter = new MilestoneRecyclerViewAdapter(this.getActivity(), titleNames, notes);
        recyclerView.setAdapter(adapter);
    }

    public void setContext(Context context) {
        this.context = context;
    }//not quite necessary

    public ArrayList<String> getTitleNames() {
        return titleNames;
    }

    public void setTitleNames(ArrayList<String> titleNames) {
        this.titleNames = titleNames;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }
/*
    //This method is meant to add the data to the DB, use it later
    public void addDataToDB(EditText milestoneTitle, EditText milestoneNote){
        milestoneTitle.getText();
        milestoneNote.getText();

    }
    */
}
