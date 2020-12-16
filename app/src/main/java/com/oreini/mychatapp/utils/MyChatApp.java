package com.oreini.mychatapp.utils;

import android.app.Application;
import android.content.Context;

import com.oreini.mychatapp.managers.FirebaseManager;
import com.oreini.mychatapp.managers.SharedPrefsManager;

public class MyChatApp extends Application {

    public static final String LOG_TAG = "MyChatApp";
    public static Context mContext;
    public static SharedPrefsManager mSharedPrefsManager;
    public static  FirebaseManager mFirebaseManager;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
<<<<<<< Updated upstream

        mSharedPrefsManager = SharedPrefsManager.getInstance(mContext);
      //  mFirebaseManager = FirebaseManager.getInstance(mContext);
=======
        mSharedPrefsManager = SharedPrefsManager.getInstance(mContext);
        mFirebaseManager = FirebaseManager.getInstance(mContext);
>>>>>>> Stashed changes
    }

}
