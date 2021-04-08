package com.example.mbtichat.ui.randomChatMain;

import androidx.annotation.NonNull;

import com.example.mbtichat.data.entity.Post;
import com.example.mbtichat.data.entity.RandomChatMain;
import com.example.mbtichat.data.entity.User;
import com.example.mbtichat.ui.detail.PostDetailUserItem;

public class RandomChatMainItem {
    @NonNull
    private final RandomChatMain randomChatMain;
    @NonNull
    private final com.example.mbtichat.ui.post.PostItem.EventListener eventListener;

    private User user;
    private PostDetailUserItem.EventListener eventListener;

    public RandomChatMainUserItem(User user, PostDetailUserItem.EventListener eventListener){
        this.user = user;
        this.eventListener = eventListener;
    }

    public RandomChatMainItem(@NonNull RandomChatMain randomChatMain, com.example.mbtichat.ui.randomChatMain.RandomChatMainItem.EventListener eventListener){
        this.randomChatMain = randomChatMain;
        this.eventListener = eventListener;
    }

    @NonNull
    public RandomChatMain getRandomChatMain(){
        return randomChatMain;
    }

    public String getUserId() {
        return user.getUserId();
    }

    @NonNull
    public com.example.mbtichat.ui.post.PostItem.EventListener getEventListener(){
        return eventListener;
    }

    public interface EventListener{
        void onRandomChatClick(RandomChatMainItem randomChatMainItem);
    }
}
