package com.example.mbtichat.ui.randomChat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mbtichat.data.CommentService;
import com.example.mbtichat.data.RandomChatMainService;
import com.example.mbtichat.data.UserService;
import com.example.mbtichat.data.entity.Comment;
import com.example.mbtichat.data.entity.Post;
import com.example.mbtichat.data.entity.RandomChat;
import com.example.mbtichat.ui.detail.PostDetailCommentItem;
import com.example.mbtichat.ui.detail.PostDetailItem;
import com.example.mbtichat.ui.detail.PostDetailPostItem;
import com.example.mbtichat.ui.detail.PostDetailUserItem;
import com.example.mbtichat.ui.post.PostItem;
import com.example.mbtichat.ui.randomChatMain.RandomChatMainItem;
import com.example.mbtichat.util.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

public class RandomChatViewModel extends AndroidViewModel {

    private final MutableLiveData<List<PostDetailItem>> liveItems = new MutableLiveData<>();
    private final UserService userService;

    @NonNull
    private final SingleLiveEvent<Throwable> errorEvent;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(true);

    private final SingleLiveEvent<Long> userClickEvent = new SingleLiveEvent<>();

    @Inject
    public RandomChatViewModel(@NonNull Application application, UserService userService, CommentService commentService,
                               @Named("errorEvent") SingleLiveEvent<Throwable> errorEvent){
        super(application);
        Timber.d("PostDetailViewModel created");
        this.userService = userService;
        this.commentService = commentService;
        this.errorEvent = errorEvent;
    }

    public void load(RandomChat randomChat){
        compositeDisposable.add(Single.zip(userService.getUser(randomChat.getSenderId()),
                Single.just(randomChat),
                commentService.getComments(post.getId()),
                (user, p, comments) -> {
                    List<PostDetailItem> list = new ArrayList<>();
                    list.add(new PostDetailUserItem(user,this));
                    list.add(new PostDetailPostItem(p));
                    for(Comment comment : comments){
                        list.add(new PostDetailCommentItem(comment));
                    }
                    return list;
                })
                .retry(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(postDetailItems -> loading.postValue(false))
                .subscribe(liveItems::setValue, errorEvent::setValue)
        );
    }

    public MutableLiveData<List<PostDetailItem>> getLiveItmes(){
        return liveItems;
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        Timber.d("onCleared");
        this.compositeDisposable.dispose();
    }

    public MutableLiveData<Boolean> getLoading(){
        return loading;
    }

    public SingleLiveEvent<Long> getUserClickEvent() {
        return userClickEvent;
    }

    @Override
    public void onUserClick(long userId){
        userClickEvent.setValue(userId);
    }

}