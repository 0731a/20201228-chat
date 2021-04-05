package com.example.mbtichat.ui.randomChat;

import androidx.annotation.NonNull;

import com.example.mbtichat.data.entity.Post;
import com.example.mbtichat.data.entity.RandomChat;
import com.example.mbtichat.data.entity.User;
import com.example.mbtichat.ui.detail.PostDetailItem;
import com.example.mbtichat.ui.detail.PostDetailUserItem;
import com.example.mbtichat.ui.post.PostItem;

public class RandomChatItem {
    @NonNull
    private RandomChat randomChat;

    public RandomChatItem(@NonNull RandomChat randomChat){
        this.randomChat = randomChat;
    }
    public String getDate(){
        return randomChat.getDate();
    }

    public long getId(){
        return randomChat.getId();
    }

    public String getText(){
        return randomChat.getText();
    }

    public long getReceiverId(){
        return randomChat.getReceiverId();
    }

    public long getSenderId(){
        return randomChat.getSenderId();
    }

}
