package com.example.mbtichat.model;

public class mbtiQuestion {
    private int index;
    private int type;
    private String question;
    public static char typeList[] = {'E','I','S','N','T','F','J','P'};

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
