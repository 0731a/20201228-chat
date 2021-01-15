package com.example.mbtichat.Model;

import java.util.Date;

public class RandomChatMatchingModel {
    Date created_date;
    Date updated_date;
    int user_idx1;
    int user_idx2;
    int idx;

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    public int getUser_idx1() {
        return user_idx1;
    }

    public void setUser_idx1(int user_idx1) {
        this.user_idx1 = user_idx1;
    }

    public int getUser_idx2() {
        return user_idx2;
    }

    public void setUser_idx2(int user_idx2) {
        this.user_idx2 = user_idx2;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }


}
