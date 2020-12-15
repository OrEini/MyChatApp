package com.oreini.mychatapp.models;

import java.util.UUID;

public class User {

    private String mUserID; //Device ID
    private String mUserName;
    private int mCurrentRoom;
    private int mChatWIth;

    public User(String user_name) {

        mUserID = UUID.randomUUID().toString().replaceAll("-", "");;
        mUserName = user_name;
    }

    public String getUserID() {
        return mUserID;
    }

    public String getUserName() {
        return mUserName;
    }

    public int getChatWIth() {
        return mChatWIth;
    }

    public int getCurrentRoom() {
        return mCurrentRoom;
    }

    public void setChatWIth(int mChatWIth) {
        this.mChatWIth = mChatWIth;
    }

    public void setCurrentRoom(int mCurrentRoom) {
        this.mCurrentRoom = mCurrentRoom;
    }
}
