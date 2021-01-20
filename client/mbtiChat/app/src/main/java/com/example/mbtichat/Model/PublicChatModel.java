package com.example.mbtichat.Model;

import java.util.Date;

public class PublicChatModel {
    private String date;
    private String nickName;
    private String mbti;
    private String text;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}