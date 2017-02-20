package com.xurxodev.moviesandroidkata.di;

import android.app.Application;

import com.xurxodev.moviesandroidkata.domain.boundary.executor.AsyncExecutor;
import com.xurxodev.moviesandroidkata.domain.boundary.executor.MainExecutor;
import com.xurxodev.moviesandroidkata.presentation.executor.ThreadExecutor;
import com.xurxodev.moviesandroidkata.presentation.executor.UIThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public AsyncExecutor provideAsyncExecutor() {
        return new ThreadExecutor();
    }

    @Provides
    @Singleton
    public MainExecutor provideMainExecutor() {
        return new UIThreadExecutor();
    }
}