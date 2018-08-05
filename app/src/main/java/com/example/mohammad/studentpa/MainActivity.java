package com.example.mohammad.studentpa;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammad.studentpa.milestones.Milestones;
import com.example.mohammad.studentpa.util.LocalData;
import com.example.mohammad.studentpa.reminders.Reminders;
import com.example.mohammad.studentpa.schedule.Schedule;
import com.example.mohammad.studentpa.spending.Budget;
import com.example.mohammad.studentpa.util.SavedUserLogin;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener {
    private static final String TAG = "MainActivity started";
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        stayLoggedIn();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runFirstTime();//When program is run for the first time

        //Saves user to SharedPreferences
        if(getIntent().hasExtra("user")){
            SavedUserLogin.setUserName(MainActivity.this, getIntent().getStringExtra("user"));
        }

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

        Fragment fragment;
        fragment= new Milestones();
        replaceFrag(fragment);

        NavigationView navigationView= findViewById(R.id.navigation_view_main);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        //To display the current user in  navigation bar
        TextView currentUser = headerView.findViewById(R.id.textViewUser);
        LocalData localData = new LocalData(this);
        String user= "Current User: " + localData.get_name();
        currentUser.setText(user);

    }

    //change fragment according to what user selects
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
        int id = item.getItemId();

        if (id == R.id.Info) {
            //TODO: Insert help here and menu
            return true;
        }else if(id == R.id.Logout){
            Intent logoutIntent = new Intent(MainActivity.this, Login.class);
            startActivity(logoutIntent);
            SavedUserLogin.clearUserName(MainActivity.this);
            finish();
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
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(MainActivity.this,
                        android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(MainActivity.this);
            }
            builder.setTitle("Exit")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();
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
        } else if (id == R.id.Budget) {
            fragment= new Budget();
            replaceFrag(fragment);
            Toast.makeText(this, "Budget!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.extra_options, menu);
        return true;
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String stringDate= dayOfMonth + " / " + (month + 1) + " / " + year;
        LocalData localData = new LocalData(this);
        localData.set_date(stringDate);
    }

    public void runFirstTime(){
        //Is supposed to run this for the first time
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {//display this info if run for the first time
            Toast.makeText(this, "Welcome! " +
                    "To begin, open navigation drawer and select a category", Toast.LENGTH_LONG).show();
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();

    }

    public void stayLoggedIn(){
        //checks if user is already logged in
        if(SavedUserLogin.getUserName(MainActivity.this).length() == 0){
            Intent loginIntent = new Intent(MainActivity.this, Login.class);
            startActivity(loginIntent);
            finish();
        }
    }

}
