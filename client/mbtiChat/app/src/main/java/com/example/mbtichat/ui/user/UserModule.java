package com.example.mbtichat.ui.user;

import android.content.Context;
import android.view.LayoutInflater;

import com.example.mbtichat.databinding.FragmentUserBinding;
import com.example.mbtichat.di.ActivityContext;
import com.example.mbtichat.di.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    @Provides
    @FragmentScope
    FragmentUserBinding provideBinding(@ActivityContext Context context){
        return FragmentUserBinding.inflate(LayoutInflater.from(context));
    }
}

