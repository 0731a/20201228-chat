package com.example.mbtichat.ui.detail;


public abstract class PostDetailItem {
    public abstract Type getType();

    public enum Type{
        USER, // 사용자 정보
        POST, // 게시 글
        COMMENT // 댓글
    }
}
