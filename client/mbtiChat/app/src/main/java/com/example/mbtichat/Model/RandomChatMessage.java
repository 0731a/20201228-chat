package com.example.mbtichat.Model;

import java.util.Date;

public class RandomChatMessage {
    int matching_idx;
    String text;
    Date created_date;

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

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}
