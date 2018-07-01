package com.example.mohammad.studentpa.Schedule;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "SectionsPagerAdapter";

    Context context;

    private final List<Fragment> fragmentList = new ArrayList();//Keep track of fragments
    private final List<String> fragmentTitleList = new ArrayList();//Keep track of fragment titles

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    public void addFragment(Fragment fragment, String title){//To add fragment to list
        Log.d(TAG, "addFragment: ");
        fragmentList.add(fragment);
        fragmentTitleList.add(title);

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MondayFragment();
        } else if (position == 1){
            return new TuesdayFragment();
        } else if (position == 3){
            return new WednesdayFragment();
        }  else if (position == 4){
            return new ThursdayFragment();
        } else if (position == 5){
            return new FridayFragment();
        }else if(position == 6){
            return new SaturdayFragment();
        }else{
            return fragmentList.get(position);

        }

    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}