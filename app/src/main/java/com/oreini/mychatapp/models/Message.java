package com.oreini.mychatapp.models;

import java.util.Date;

public class Message {

    private User mAuthor;
    private String mMessage;
    private Date mTimestamp;


    public Message(User author, String message) {

        mAuthor = author;
        mMessage = message;
        mTimestamp = new Date();
    }

    public User getAuthor() {
        return mAuthor;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public Date getTimestamp() {
        return mTimestamp;
    }
}
