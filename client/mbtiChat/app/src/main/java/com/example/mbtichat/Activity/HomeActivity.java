package com.example.mbtichat.Activity;

import android.os.Bundle;

import com.example.mbtichat.databinding.ActivityHomeBinding;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

// 멤버 인젝션을 하도록 DaggerAppCompatActivity를 상속한다
public class HomeActivity extends DaggerAppCompatActivity {

    // 바인딩 클래스 주입
    @Inject
    Lazy<ActivityHomeBinding> binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // 이 액티비티를 lifeCycleOwner로 설정하여,
        // 생명 주기에 안전하게 데이터 바인딩을 할 수 있도록 한다.
        binding.get().setLifecycleOwner(this);
    }



}
