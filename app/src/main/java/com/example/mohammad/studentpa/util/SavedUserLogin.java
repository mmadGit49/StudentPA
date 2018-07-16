package com.example.mohammad.studentpa.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SavedUserLogin {//for auto login functionality
        private static final String NAME_SAVED= "username";
        private static final String PASSWORD_SAVED = "password";
        private static final String EMAIL_SAVED = "email";


    private static SharedPreferences getSharedPreferences(Context ctx) {
            //context is used to get relevant params in question
            return PreferenceManager.getDefaultSharedPreferences(ctx);
        }//Shared preferences will check if user has logged in and their login  data exists

        public static void setUserName(Context ctx, String userName) {
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.putString(NAME_SAVED, userName);
            editor.apply();

        }//will set the username from the login screen

        public static String getUserName(Context ctx) {
            return getSharedPreferences(ctx).getString(NAME_SAVED, "");
        }

        public static void setPassword(Context ctx, String password) {
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.putString(PASSWORD_SAVED, password);
            editor.apply();

        }
        public static String getPassword(Context ctx) {
            return getSharedPreferences(ctx).getString(PASSWORD_SAVED, "");
        }

        public static void setEmail(Context ctx, String email) {
            SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
            editor.putString(EMAIL_SAVED, email);
            editor.apply();

        }
        public static String getEmail(Context ctx) {
            return getSharedPreferences(ctx).getString(EMAIL_SAVED, "");
        }
}


