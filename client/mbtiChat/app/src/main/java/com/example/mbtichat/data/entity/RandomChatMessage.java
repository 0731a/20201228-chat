package com.example.mbtichat.data.entity;

import java.util.Date;

public class RandomChatMessage {
    int matching_idx;
    String text;
    Date created_date;

    public RandomChatMessage(int matching_idx, String text, Date created_date){
        this.matching_idx = matching_idx;
        this.text = text;
        this.created_date = created_date;
    }

    public int getMatching_idx() {
        return matching_idx;
    }

    public String getText() {
        return text;
    }

    public Date getCreated_date() {
        return created_date;
    }
}
