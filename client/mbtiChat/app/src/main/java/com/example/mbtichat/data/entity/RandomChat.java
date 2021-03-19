package com.example.mbtichat.data.entity;

import java.text.DateFormat;
import java.util.Date;

public class RandomChat {
    private long id;
    private long senderId;
    private long receiverId;
    private String text;
    private String date;

    public RandomChat(long id, long senderId, long receiverId, String text, String date) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public long getSenderId() {
        return senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}
