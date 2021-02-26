package com.example.mbtichat.ui;

import android.content.Context;

import androidx.databinding.DataBindingUtil;

import com.example.mbtichat.Activity.HomeActivity;
import com.example.mbtichat.R;
import com.example.mbtichat.databinding.ActivityHomeBinding;
import com.example.mbtichat.di.ActivityContext;
import com.example.mbtichat.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

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
}
