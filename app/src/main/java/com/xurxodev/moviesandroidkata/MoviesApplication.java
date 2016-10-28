package com.xurxodev.moviesandroidkata;

import android.app.Application;

import com.xurxodev.moviesandroidkata.view.di.AppModule;
import com.xurxodev.moviesandroidkata.view.di.DaggerMoviesComponent;
import com.xurxodev.moviesandroidkata.view.di.DataModule;
import com.xurxodev.moviesandroidkata.view.di.MoviesComponent;

import dagger.Module;

public class MoviesApplication extends Application {
    private MoviesComponent moviesComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        moviesComponent = DaggerMoviesComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .build();

    }

    public MoviesComponent getMoviesComponent() {
        return moviesComponent;
    }
}
