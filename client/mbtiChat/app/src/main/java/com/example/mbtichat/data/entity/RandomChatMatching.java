package com.example.mbtichat.data.entity;

import java.util.Date;

public class RandomChatMatching {
    Date created_date;
    Date updated_date;
    int user_idx1;
    int user_idx2;
    int idx;

    public RandomChatMatching(Date created_date, Date update_date, int user_idx1, int user_idx2, int idx){
        this.created_date = created_date;
        this.updated_date = update_date;
        this.user_idx1 = user_idx1;
        this.user_idx2 = user_idx2;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public int getUser_idx1() {
        return user_idx1;
    }

    public int getUser_idx2() {
        return user_idx2;
    }

    public int getIdx() {
        return idx;
    }

}
