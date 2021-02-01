package com.example.mbtichat.Model;


public class BoardPostModel {
    private String createdAt;
    private String writer;
    private int idx;
    private String text;

    public String getUser_mbti() {
        return user_mbti;
    }

    public void setUser_mbti(String user_mbti) {
        this.user_mbti = user_mbti;
    }

    private String user_mbti;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
