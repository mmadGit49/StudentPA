package com.example.mohammad.studentpa;

import android.content.Context;
import android.os.Bundle;
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
        initNotes();
        return mileView;
    }

    private void initNotes(){
        titleNames.add("Hello");
        notes.add("Test1");

        titleNames.add("Hello");
        notes.add("Test2");

        titleNames.add("Hello");
        notes.add("Test3");

        titleNames.add("Hello");
        notes.add("Test4");

        titleNames.add("Hello");
        notes.add("Test5");

        titleNames.add("Hello");
        notes.add("Test6");

        titleNames.add("Hello");
        notes.add("Test7");

        titleNames.add("Hello");
        notes.add("Test8");

        titleNames.add("Hello");
        notes.add("Test9");

        initRecyclerView();
    }


    private void initRecyclerView() {//initialises
        RecyclerView recyclerView = mileView.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.getActivity(), titleNames, notes);
        recyclerView.setAdapter(adapter);
    }

    public void setContext(Context context) {
        this.context = context;
    }//not quite necessary

    /*
    //This method is meant to add the data to the DB, use it later
    public void addDataToDB(EditText milestoneTitle, EditText milestoneNote){
        milestoneTitle.getText();
        milestoneNote.getText();

    }
    */
}