package com.example.mohammad.studentpa;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mohammad.studentpa.Milestones.Milestones;
import com.example.mohammad.studentpa.Reminders.Reminders;
import com.example.mohammad.studentpa.Schedule.Schedule;
import com.example.mohammad.studentpa.Spending.Spending;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity started";
    private DrawerLayout drawerLayout;
    private ArrayList<String> titleNames= new ArrayList<>();
    private ArrayList<String> notes = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for the toolbar, action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView= findViewById(R.id.navigation_view_main);
        navigationView.setNavigationItemSelectedListener(this);

        /*TODO: Uncomment this once database has been made for login
         *
        if(SavedUserLogin.getUserName(MainActivity.this).length() == 0)//checks if user is logged in
        {//if not logged in this happens
            Intent login= new Intent(this, Login.class);
            startActivity(login);
        }
        else
        {
            // Stay at the current activity.
        }

         */
    }
    public void replaceFrag(Fragment fragment){//to replace the selected fragment
        if (fragment != null) {
            Log.d(TAG, "replaceFrag: started");
            FragmentTransaction fragTrans= getSupportFragmentManager().beginTransaction();
            fragTrans.replace(R.id.frameContainer, fragment);
            fragTrans.commit();
        }
        else{
            Toast.makeText(this, "Something's not right...", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){//method to activate the clicked menu button
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        onOptionsItemSelected(menuItem);//item selector method
        menuItem.setChecked(true);// set item as selected to persist highlight
        drawerLayout.closeDrawers();// close drawer when item is tapped
        Fragment fragment;
        int id = menuItem.getItemId();
        if (id == R.id.Milestones) {
            fragment= new Milestones();
            replaceFrag(fragment);
            Toast.makeText(this, "Milestones!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.Schedule) {
            fragment= new Schedule();
            replaceFrag(fragment);
            Toast.makeText(this, "Schedules!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.Reminders) {
            fragment= new Reminders();
            replaceFrag(fragment);
            Toast.makeText(this, "Reminders!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.Spending) {
            fragment= new Spending();
            replaceFrag(fragment);
            Toast.makeText(this, "Spending!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
