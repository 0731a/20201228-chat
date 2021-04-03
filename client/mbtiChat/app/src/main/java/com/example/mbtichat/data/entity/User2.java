package com.example.mbtichat.data.entity;

public class User2 {

    private int idx;
    private String  nickName;
    private String id;
    private String mbti;

    User2(int idx, String nickName, String id, String mbti){
        this.idx = idx;
        this.nickName = nickName;
        this.id = id;
        this.mbti = mbti;
    }

    public int getIdx() {
        return idx;
    }

    public String getNickName() {
        return nickName;
    }

    public String getId() {
        return id;
    }

    public String getMbti(){
        return mbti;
    }


}
