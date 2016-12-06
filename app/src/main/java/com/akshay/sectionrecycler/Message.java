package com.akshay.sectionrecycler;

import android.support.annotation.IntDef;
import android.text.format.DateUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Message {

    public static final int TODAY = 0;
    public static final int YESTERDAY = 1;
    public static final int MONTH = 2;
    public static final int OLDER = 3;

    @IntDef({TODAY, YESTERDAY, MONTH, OLDER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SectionType {}

    private String message;
    private long timestamp;
    private @SectionType int sectionType;

    public Message(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        sectionType = TODAY;
    }

    public Message(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
        sectionType = processType(timestamp);
    }

    private int processType(long timestamp) {
        if (DateUtils.isToday(timestamp)) {
            return TODAY;
        } else if (Utils.isYesterday(timestamp)) {
            return YESTERDAY;
        } else if (Utils.isThisMonth(timestamp)) {
            return MONTH;
        }
        return OLDER;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public @SectionType int getSectionType() {
        return sectionType;
    }

    public void setSectionType(@SectionType int sectionType) {
        this.sectionType = sectionType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", section=" + sectionType +
                '}';
    }
}
