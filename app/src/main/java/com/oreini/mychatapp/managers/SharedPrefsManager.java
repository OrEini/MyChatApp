package com.oreini.mychatapp.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.oreini.mychatapp.models.User;

public class SharedPrefsManager {

    private static SharedPrefsManager mInstance = null;

    private static String SHARED_NAME = "MyChatApp";
    private static int MODE_PRIVATE = 0;

    static SharedPreferences mSharedPrefs;

    public static SharedPrefsManager getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new SharedPrefsManager(context);
        }

        return mInstance;
    }

    private SharedPrefsManager(Context context) {

        mSharedPrefs = context.getSharedPreferences(SHARED_NAME, MODE_PRIVATE);
    }

    public void setAlreadyRegistered(boolean flag) {

        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.putBoolean("already_registered", flag);
        editor.commit();
    }

    public boolean isAlreadyRegistered() {
        return mSharedPrefs.getBoolean("already_registered",false);
    }


    public void setUser(User user) {

        SharedPreferences.Editor editor = mSharedPrefs.edit();

        editor.putString("user_id", user.getUserID());

        editor.putString("user_name", user.getUserName());

        editor.commit();
    }

    public User getUser() {

        String userName = mSharedPrefs.getString("user_name", null);

        return new User(userName);
    }

    public String getUserID() {

        return mSharedPrefs.getString("user_id", null);
    }

    public String getUserName() {

        return mSharedPrefs.getString("user_name", null);

    }
}
