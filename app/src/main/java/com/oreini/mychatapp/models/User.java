package com.oreini.mychatapp.models;

import java.util.UUID;

import static com.oreini.mychatapp.utils.MyChatApp.mSharedPrefsManager;

public class User {

    private String mUserID; //Device ID
    private String mUserName;

    public User(String user_name) {

        if (!mSharedPrefsManager.isAlreadyRegistered()) {
            mUserID = UUID.randomUUID().toString().replaceAll("-", "");
        } else {
            mUserID = mSharedPrefsManager.getUserID();
        }

        mUserName = user_name;
    }

    public User(String user_id, String user_name) {

        mUserID = user_id;
        mUserName = user_name;
    }


    public String getUserID() {
        return mUserID;
    }

    public String getUserName() {
        return mUserName;
    }
}
