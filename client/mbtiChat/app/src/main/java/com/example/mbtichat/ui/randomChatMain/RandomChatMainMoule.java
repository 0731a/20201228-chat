package com.example.mbtichat.ui.randomChatMain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mbtichat.databinding.FragmentPostBinding;
import com.example.mbtichat.databinding.FragmentRandomchatMainBinding;
import com.example.mbtichat.di.ApplicationContext;
import com.example.mbtichat.di.FragmentScope;
import com.example.mbtichat.ui.post.PostFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class RandomChatMainMoule {
    // 데이터 바인딩 클래스 제공
    @Provides
    @FragmentScope
    FragmentPostBinding provideBinding(@ApplicationContext Context context){
        return FragmentRandomchatMainBinding.inflate(LayoutInflater.from(context), null, false);
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

    // Navigation 컴포넌트에서 목적지 간 이동을 담당하는 NavController
    @Provides
    @FragmentScope
    NavController provideNavController(PostFragment fragment){
        return NavHostFragment.findNavController(fragment);
    }
}
