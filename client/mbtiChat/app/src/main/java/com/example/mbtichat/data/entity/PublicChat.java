package com.example.mbtichat.data.entity;

import java.util.Date;

public class PublicChat {
    private String date;
    private String nickName;
    private String mbti;
    private String text;

    public PublicChat(String date, String nickName, String mbti, String text){
        this.date = date;
        this.nickName = nickName;
        this.mbti = mbti;
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public String getNickName() {
        return nickName;
    }

    public String getMbti() {
        return mbti;
    }

    public String getText() {
        return text;
    }
}