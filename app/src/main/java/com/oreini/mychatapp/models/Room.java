package com.oreini.mychatapp.models;

import android.util.Log;

import java.util.ArrayList;

import static com.oreini.mychatapp.utils.MyChatApp.LOG_TAG;

public class Room {

    private static final int MAX_NUM_USERS = 2;

    private int mRoomID;
    private ArrayList mUsers;


    public Room(int room_id) {

        mRoomID = room_id;
        mUsers = new ArrayList<Integer>();
    }

    public int getRoomID() {
        return mRoomID;
    }

    public void setNewUser(int user_id) {

        if (mUsers.size() >= MAX_NUM_USERS) {

            Log.e(LOG_TAG,"The room is full");
        } else {
            mUsers.add(user_id);
        }
    }
}
