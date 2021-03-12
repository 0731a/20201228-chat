package com.example.mbtichat.ui.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mbtichat.databinding.FragmentPostDetailBinding;
import com.example.mbtichat.di.ApplicationContext;
import com.example.mbtichat.di.FragmentScope;

import dagger.Provides;

public class PostDetailModule {
    // View를 표현하는 바인딩 클래스
    @Provides
    @FragmentScope
    public FragmentPostDetailBinding provideBinding(@ApplicationContext Context context){
        return FragmentPostDetailBinding.inflate(LayoutInflater.from(context),null,false);
    }

    // RecyclerView를 위함
    @Provides
    @FragmentScope
    public LinearLayoutManager provideLayoutManager(@ApplicationContext Context context){
        return new LinearLayoutManager(context){
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams(){
                return new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
            }
        };
    }

    // 화면 전환을 위한 NavController 제공
    @Provides
    @FragmentScope
    public NavController provideNavController(PostDetailFragment fragment){
        return NavHostFragment.findNavController(fragment);
    }
}
