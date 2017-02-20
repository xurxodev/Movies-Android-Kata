package com.xurxodev.moviesandroidkata;

import android.app.Application;

import com.xurxodev.moviesandroidkata.di.component.ApplicationComponent;
import com.xurxodev.moviesandroidkata.di.component.DaggerApplicationComponent;
import com.xurxodev.moviesandroidkata.di.module.AppModule;
import com.xurxodev.moviesandroidkata.di.module.DataModule;

public class MoviesApplication extends Application {
    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        appComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .build();

    }

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }
}
