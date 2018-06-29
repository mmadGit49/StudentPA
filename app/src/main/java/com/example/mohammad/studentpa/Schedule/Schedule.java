package com.example.mohammad.studentpa.Schedule;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammad.studentpa.R;

import java.util.ArrayList;

public class Schedule extends Fragment {

    private View schedView;
    private Context context;
    private FloatingActionButton fab;
    private LinearLayoutManager layoutManager;
    private ArrayList<String> titleNames= new ArrayList<>();

    ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        schedView = inflater.inflate(R.layout.fragment_schedule, container, false);
        Toolbar toolbar = schedView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        toolbar.setTitle("Schedule");

        fab = schedView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRecyclerView();
            }
        });

        //set up viewpager with sections adapter
        viewPager = schedView.findViewById(R.id.viewPagerContainer);
        setupViewPager(viewPager);

        TabLayout tabLayout = schedView.findViewById(R.id.scheduleTabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return schedView;
    }

    public void setupViewPager(ViewPager vPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getFragmentManager());
        adapter.addFragment(new MondayFragment(), "Mon");
        adapter.addFragment(new TuesdayFragment(), "Tue");
        adapter.addFragment(new WednesdayFragment(), "Wed");
        adapter.addFragment(new ThursdayFragment(), "Thur");
        adapter.addFragment(new FridayFragment(), "Fri");
        adapter.addFragment(new SaturdayFragment(), "Sat");

        vPager.setAdapter(adapter);
    }
    public void initRecyclerView() {//initialises adapters, views and what have you's
        RecyclerView recyclerView = schedView.findViewById(R.id.recycler_view_schedule);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ScheduleRecyclerViewAdapter adapter = new ScheduleRecyclerViewAdapter(this.getActivity(), titleNames);
        recyclerView.setAdapter(adapter);
    }


}
