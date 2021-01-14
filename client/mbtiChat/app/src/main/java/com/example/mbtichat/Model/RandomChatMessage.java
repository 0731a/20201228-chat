package com.example.mbtichat.Model;

import java.util.Date;

public class RandomChatMessage {
    int matching_idx;
    String text;
    Date created_Time;

    public int getMatching_idx() {
        return matching_idx;
    }

    public void setMatching_idx(int matching_idx) {
        this.matching_idx = matching_idx;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated_Time() {
        return created_Time;
    }

    public void setCreated_Time(Date created_Time) {
        this.created_Time = created_Time;
    }
}
