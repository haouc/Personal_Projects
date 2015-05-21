package com.example.hao.reminders;

/**
 * Created by Hao on 4/15/2015.
 */
public class Reminder {
    private int mId;
    private String mContent;
    private int mImportant;

    public Reminder(int mId, String mContent, int mImportant) {
        this.mId = mId;
        this.mContent = mContent;
        this.mImportant = mImportant;
    }

    public int getmImportant() {

        return mImportant;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmId(int mId) {

        this.mId = mId;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public void setmImportant(int mImportant) {
        this.mImportant = mImportant;
    }

    public int getmId() {

        return mId;
    }
}
