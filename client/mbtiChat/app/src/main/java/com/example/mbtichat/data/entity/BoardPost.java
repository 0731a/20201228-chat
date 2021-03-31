package com.example.mbtichat.data.entity;


public class BoardPost{
    private String createdAt;
    private String writer;
    private int idx;
    private String text;
    private String user_mbti;

    public BoardPost(String createdAt, String writer, int idx, String text, String user_mbti){
        this.createdAt = createdAt;
        this.writer = writer;
        this.idx = idx;
        this.text = text;
        this.user_mbti = user_mbti;
    }

    public String getUser_mbti() {
        return user_mbti;
    }

    public String getCreatedAt() { return createdAt;  }

    public String getWriter() {
        return writer;
    }

    public int getIdx() {
        return idx;
    }

    public String getText() {
        return text;
    }
}
