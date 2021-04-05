package com.example.mbtichat.data.entity;

public class RandomChatMain {
    private long idx;
    private long otherId;
    private String recentText;
    private String date;

    public RandomChatMain(long idx, long otherId, String recentText, String date) {
        this.idx = idx;
        this.otherId = otherId;
        this.recentText = recentText;
        this.date = date;
    }

    public long getIdx() {
        return idx;
    }

    public long getotherId() {
        return otherId;
    }


    public String getRecentText() {
        return recentText;
    }

    public String getDate() {
        return date;
    }
}
