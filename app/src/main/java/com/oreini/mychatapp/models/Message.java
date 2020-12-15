package com.oreini.mychatapp.models;

public class Message {

    private int mWriterID;
    private String mMessage;
    private long mTimestamp;


    public Message(int writer_id, String message) {

        mWriterID = writer_id;
        mMessage = message;
        mTimestamp = System.currentTimeMillis();
    }

    public int getWriterID() {
        return mWriterID;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public long getTimestamp() {
        return mTimestamp;
    }
}
