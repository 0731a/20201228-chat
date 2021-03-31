package com.example.mbtichat.data.entity;

public class MbtiQuestion {
    private int type;
    private int index;
    private String firstQuestion;
    private String secondQuestion;
    private int answer;

    public static char typeList[] = {'E','I','S','N','T','F','J','P'};

    public MbtiQuestion(int type, int index, String firstQuestion, String secondQuestion, int answer){
        this.type = type;
        this.index = index;
        this.firstQuestion = firstQuestion;
        this.secondQuestion = secondQuestion;
    }

    public int getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public String getFirstQuestion() {
        return firstQuestion;
    }

    public String getSecondQuestion() {
        return secondQuestion;
    }

    public int getAnswer() {
        return answer;
    }

}
