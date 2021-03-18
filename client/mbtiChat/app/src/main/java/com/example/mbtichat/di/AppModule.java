package com.example.mbtichat.di;

import android.app.Application;
import android.content.Context;

import com.example.mbtichat.App;
import com.example.mbtichat.util.SingleLiveEvent;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {
        ViewModelModule.class,
        RetrofitModule.class
})
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

    // Retrofit 객체 생성
    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

}
