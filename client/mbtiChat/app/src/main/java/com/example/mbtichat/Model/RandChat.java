package com.example.mbtichat.Model;

import java.util.Date;

public class RandChat {
    private Date date;
    private String writer;
    private String mbti;
    private String text;
    private String Image;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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