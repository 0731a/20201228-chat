package com.example.mbtichat;

import com.example.mbtichat.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;

public class App extends DaggerApplication {

    @Override
    public void onCreate(){
        super.onCreate();
        // 로그용 Timber 설정
        Timber.plant(new Timber.DebugTree());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        // AppComponent 설정이 끝난 뒤
        // 컴파일 타임에 DaggerAppComponent가 생성된다.
        // implementation 'com.google.dagger:dagger:2.25.4'를 추가한 이후  DaggerAppComponent가 해결되었다.
        return DaggerAppComponent.factory().create(this);
    }
}
