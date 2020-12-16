package com.oreini.mychatapp.managers;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseManager {

    private static FirebaseManager mInstance = null;
    private static FirebaseDatabase mDatabase;
    private static DatabaseReference mRoomsRef;

    private static boolean mIsInitialized = false;

    public static FirebaseManager getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new FirebaseManager(context);
        }

        return mInstance;
    }

    private FirebaseManager(Context context) {

        if (mIsInitialized) {

            return;
        }

        mDatabase = FirebaseDatabase.getInstance();

        mRoomsRef = mDatabase.getReference("Rooms");

        mIsInitialized = true;
    }
}
