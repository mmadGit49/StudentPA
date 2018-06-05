package com.example.mohammad.studentpa;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SavedUserLogin {//for auto login functionality
        static final String NAME_SAVED= "username";

        static SharedPreferences getSharedPreferences(Context ctx) {//context is the activity in question
            return PreferenceManager.getDefaultSharedPreferences(ctx);
        }//Shared preferences will check if user has logged in and their login  data exists

        public static void setUserName(Context ctx, String userName)
        {
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.putString(NAME_SAVED, userName);
            editor.commit();
        }//will set the username from the login screen

        public static String getUserName(Context ctx)
        {
            return getSharedPreferences(ctx).getString(NAME_SAVED, "");
        }//mutatis mutandis setUserName


}


