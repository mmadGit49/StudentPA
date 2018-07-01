package com.example.mohammad.studentpa.Schedule;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammad.studentpa.R;

public class Schedule extends Fragment {

    private View schedView;
    private ViewPager viewPager;


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

        //set up viewpager with sections adapter
        viewPager = schedView.findViewById(R.id.viewPagerContainer);
        setupViewPager(viewPager);
        TabLayout tabLayout = schedView.findViewById(R.id.scheduleTabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return schedView;
    }

    public void setupViewPager(ViewPager vPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(this.getContext(), getFragmentManager());
        adapter.addFragment(new MondayFragment(), "Mon");
        adapter.addFragment(new TuesdayFragment(), "Tue");
        adapter.addFragment(new WednesdayFragment(), "Wed");
        adapter.addFragment(new ThursdayFragment(), "Thur");
        adapter.addFragment(new FridayFragment(), "Fri");
        adapter.addFragment(new SaturdayFragment(), "Sat");

        vPager.setAdapter(adapter);
    }


}
