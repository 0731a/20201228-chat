package com.example.mbtichat.di;

import android.app.Application;
import android.content.Context;

import com.example.mbtichat.App;
import com.example.mbtichat.util.SingleLiveEvent;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    Application proviceApp(App app){
        return app;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context proviceContext(App app){
        return app;
    }

    // 앱의 오류 이벤트를 처리하는 SingleLiveEvent
    @Singleton
    @Provides
    @Named("errorEvent")
    SingleLiveEvent<Throwable> provideErrorEvent(){
        return new SingleLiveEvent<>();
    }

}
