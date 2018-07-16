package com.example.mohammad.studentpa.schedule.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mohammad.studentpa.schedule.DayFragments.FridayFragment;
import com.example.mohammad.studentpa.schedule.DayFragments.MondayFragment;
import com.example.mohammad.studentpa.schedule.DayFragments.SaturdayFragment;
import com.example.mohammad.studentpa.schedule.DayFragments.ThursdayFragment;
import com.example.mohammad.studentpa.schedule.DayFragments.TuesdayFragment;
import com.example.mohammad.studentpa.schedule.DayFragments.WednesdayFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "SectionsPagerAdapter";

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        if (position == 0) {
            fragment = new MondayFragment();
        } else if (position == 1){
            fragment=  new TuesdayFragment();
        } else if (position == 2){
            fragment = new WednesdayFragment();
        } else if (position == 3){
            fragment=  new ThursdayFragment();
        } else if (position == 4){
            fragment = new FridayFragment();
        } else if(position == 5){
            fragment = new SaturdayFragment();
        }

        return fragment;

    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title= null;
        if (position == 0) {
            title = "Mon";
        } else if (position == 1){
            title= "Tue";
        } else if (position == 2){
            title = "Wed";
        }  else if (position == 3){
            title=  "Thur";
        } else if (position == 4){
            title = "Fri";
        }else if(position == 5){
            title = "Sat";
        }
        return title;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}