package com.example.mbtichat.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mbtichat.databinding.FragmentPostDetailBinding;
import com.example.mbtichat.di.AppViewModelFactory;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerFragment;

/*
*  게시글 상세 화면
* */
public class PostDetailFragment extends DaggerFragment {
    @Inject
    FragmentPostDetailBinding binding;
    @Inject
    PostDetailAdapter adapter;
    @Inject
    LinearLayoutManager layoutManager;
    @Inject
    AppViewModelFactory viewModelFactory;
    @Inject
    Lazy<NavController> navController;

    PostDetailViewModel viewModel;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(PostDetailViewModel.class);
        if(savedInstanceState == null){
            // Post 객체를 전달 받는다.
            PostDetailFragmentArgs args = PostDetailFragmentArgs.fromBundle(getArguments());
            viewModel.load(args.getPost());
        }
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup containter, @Nullable Bundle savedInstanceState){
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.setViewModel(viewModel);
        viewModel.getLiveItmes().observe(getViewLifecycleOwner(), items -> adapter.setItems(items));
        viewModel.getUserClickEvent().observe(getViewLifecycleOwner(),
                userId -> {
                    navController.get().navigate(PostDetailFragmentDirections.actionPostDetailFragmentToUserFragment(userId));
        });
    }


}
