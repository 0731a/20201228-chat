package com.example.mbtichat.ui.post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mbtichat.databinding.FragmentPostBinding;
import com.example.mbtichat.di.ApplicationContext;
import com.example.mbtichat.di.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PostModule {
    // 데이터 바인딩 클래스 제공
    @Provides
    @FragmentScope
    FragmentPostBinding provideBinding(@ApplicationContext Context context){
        return FragmentPostBinding.inflate(LayoutInflater.from(context), null, false);
    }

    // RecyclerView용 레이아웃 매니저
    @Provides
    @FragmentScope
    LinearLayoutManager provideLinearLayoutManager(@ApplicationContext Context context){
        return new LinearLayoutManager(context){
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
    }
}
