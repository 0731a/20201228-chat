package com.example.mbtichat.ui.post;

/*
* 게시글 화면 구성하기
* 멤버 인젝션을 위해 Dagger Fragment 상속
*/

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.mbtichat.databinding.FragmentPostBinding;
import com.example.mbtichat.di.AppViewModelFactory;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostFragment extends DaggerFragment {
    /*
    *  오브젝트 그래프로 부터 멤버 인젝션
    */
    @Inject
    FragmentPostBinding binding;
    @Inject
    AppViewModelFactory viewModelFactory;

    PostViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // ViewModel 객체를 요청
        viewModel = new ViewModelProvider(this, viewModelFactory).get(PostViewModel.class);
        if(savedInstanceState == null ){
            // 데이터 요청, 프래그먼트가 재 생성되었을 때는 요청하지 않는다.
            viewModel.loadPosts();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState){
        return binding.getRoot();
    }
}
