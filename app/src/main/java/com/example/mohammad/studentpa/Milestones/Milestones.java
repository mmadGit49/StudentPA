package com.example.mohammad.studentpa.Milestones;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
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

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.MilestoneEntity;
import com.example.mohammad.studentpa.db_classes.MilestoneViewModel;

import java.util.List;

public class Milestones extends Fragment {

    private EditText milestoneNote;
    private EditText milestoneTitle;
    private View mileView;
    private LinearLayoutManager layoutManager;
    private Context context;
    private LiveData<List<MilestoneEntity>> titleNames;
    private LiveData<List<MilestoneEntity>> notes;
    private List<MilestoneEntity> milestones;
    private MilestoneViewModel milestoneViewModel;
    private FloatingActionButton fab;
    private MilestoneEntity milestoneEntity = new MilestoneEntity("Title",
            "Placeholder Note");

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;



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
        toolbar.setTitle("Milestones");

        initRecyclerView();//For adapter
        //for the floating action button:

        fab = mileView.findViewById(R.id.fab_add_note);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //On item click, start note taker activity
                Intent noteIntent = new Intent(getActivity(), TakeMilestoneNote.class);
                startActivity(noteIntent);
                //startActivityForResult(noteIntent, NEW_WORD_ACTIVITY_REQUEST_CODE);

            }
        });
        return mileView;
    }

    public void initRecyclerView() {//initialises adapters, views and what have you's
        RecyclerView recyclerView = mileView.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //MilestoneRecyclerViewAdapter adapter
         //       = new MilestoneRecyclerViewAdapter(this.getActivity(), titleNames, notes);
        MilestoneRecyclerViewAdapter adapter
                = new MilestoneRecyclerViewAdapter(this.getActivity(), milestoneEntity);
        recyclerView.setAdapter(adapter);

       /* milestoneViewModel = ViewModelProviders.of(this).get(MilestoneViewModel.class);
        milestoneViewModel.getAllMilestoneNotes().observe(this, new Observer<List<MilestoneEntity>>() {
            @Override
            public void onChanged(@Nullable List<MilestoneEntity> milestoneEntities) {
                //Update the cached copy of words in the adapter
                adapter.setWords(milestoneEntities);
            }
        });*/
    }


    public void setContext(Context context) {
        this.context = context;
    }//not quite necessary

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

    /*
    //This method is meant to add the data to the DB, use it later
    public void addDataToDB(EditText milestoneTitle, EditText milestoneNote){
        milestoneTitle.getText();
        milestoneNote.getText();

    }
    */
}
