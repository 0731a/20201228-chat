package com.example.mbtichat.ui.post;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

public class PostViewModel extends AndroidViewModel {

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(true);

    @Inject
    public PostViewModel(@NonNull Application application){
        super(application);
    }

    public void loadPosts(){

    }

    public MutableLiveData<Boolean> getLoading(){
        return loading;
    }
}
