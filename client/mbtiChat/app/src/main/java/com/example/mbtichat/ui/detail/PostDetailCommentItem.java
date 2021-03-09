package com.example.mbtichat.ui.detail;

import com.example.mbtichat.data.entity.Comment;

public class PostDetailCommentItem extends PostDetailItem {
    private Comment comment;

    public PostDetailCommentItem(Comment comment){
        this.comment = comment;
    }

    @Override
    public Type getType(){
        return Type.COMMENT;
    }

    public String getName(){
        return comment.getName();
    }

    public String getBody(){
        return comment.getBody();
    }
}
