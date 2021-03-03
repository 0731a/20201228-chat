package com.example.mbtichat.ui.post;

import androidx.annotation.NonNull;

import com.example.mbtichat.data.entity.Post;

public class PostItem {
    @NonNull
    private final Post post;

    public PostItem(@NonNull Post post){
        this.post = post;
    }

    @NonNull
    public Post getPost(){
        return post;
    }

    public String getTitle() {
        return post.getTitle();
    }
}
