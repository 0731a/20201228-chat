package com.example.mbtichat.ui.post;

/*
* 게시글 화면 구성하기
* 멤버 인젝션을 위해 Dagger Fragment 상속
*/

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mbtichat.databinding.FragmentPostBinding;
import com.example.mbtichat.di.AppViewModelFactory;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import dagger.Lazy;
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

    @Inject
    PostAdapter adapter;
    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    Lazy<NavController> navController;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // Lifecycle Owner 등록
        binding.setLifecycleOwner(getViewLifecycleOwner());
        // RecyclerView Adapter 지정
        binding.recyclerView.setAdapter(adapter);
        // RecyclerView 레이아웃 매니저 지정
        binding.recyclerView.setLayoutManager(layoutManager);
        // 바인딩 클래스에 ViewModel 연결
        viewModel.getLivePosts()
                .observe(getViewLifecycleOwner(), list -> adapter.setItems(list));
        // 게시 글이 클릭되었을 때 게시 글 상세 화면 목적지로 이동
        viewModel.getPostClickEvent()
                .observe(getViewLifecycleOwner(), postItem -> navController.get().navigate(PostFragmentDirections.actionPostFragmentToPostDetailFragment(postItem.getPost())));
    }
}
