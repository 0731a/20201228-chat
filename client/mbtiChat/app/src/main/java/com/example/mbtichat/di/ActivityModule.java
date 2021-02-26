package com.example.mbtichat.di;

import com.example.mbtichat.Activity.HomeActivity;
import com.example.mbtichat.ui.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    /*
     * MainActivity를 위한 서브 컴포넌트를 정의한다.
     */
    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract HomeActivity homeActivity();
}
