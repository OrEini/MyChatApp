package com.oreini.mychatapp.models;

import android.util.Log;

import java.util.ArrayList;

import static com.oreini.mychatapp.utils.MyChatApp.LOG_TAG;

public class Room {

    private static final int MAX_NUM_USERS = 2;

    private int mRoomID;
<<<<<<< Updated upstream
    private ArrayList mUsers;

=======
    private ArrayList<User> mUsers;
>>>>>>> Stashed changes

    public Room(int room_id) {

        mRoomID = room_id;
<<<<<<< Updated upstream
        mUsers = new ArrayList<Integer>();
=======
        mUsers = new ArrayList<User>();
>>>>>>> Stashed changes
    }

    public int getRoomID() {
        return mRoomID;
    }

<<<<<<< Updated upstream
    public void setNewUser(int user_id) {
=======
    public void setNewUser(User user) {
>>>>>>> Stashed changes

        if (mUsers.size() >= MAX_NUM_USERS) {

            Log.e(LOG_TAG,"The room is full");
        } else {
<<<<<<< Updated upstream
            mUsers.add(user_id);
=======
            mUsers.add(user);
>>>>>>> Stashed changes
        }
    }
}
