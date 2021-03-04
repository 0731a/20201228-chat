package com.example.mbtichat.ui;

import android.content.Context;

import androidx.databinding.DataBindingUtil;

import com.example.mbtichat.Activity.HomeActivity;
import com.example.mbtichat.R;
import com.example.mbtichat.databinding.ActivityHomeBinding;
import com.example.mbtichat.di.ActivityContext;
import com.example.mbtichat.di.ActivityScope;
import com.example.mbtichat.di.FragmentScope;
import com.example.mbtichat.ui.post.PostFragment;
import com.example.mbtichat.ui.post.PostModule;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {

    @Provides
    @ActivityScope
    static ActivityHomeBinding provideBinding(HomeActivity activity){
        return DataBindingUtil.setContentView(activity, R.layout.activity_home);
    }

    @Provides
    @ActivityContext
    static Context provideContext(HomeActivity activity){
        return activity;
    }


    /*
     * 서브 컴포넌트 정의
     */
    @FragmentScope
    @ContributesAndroidInjector(modules = PostModule.class)
    abstract PostFragment getPostFragment();
}
