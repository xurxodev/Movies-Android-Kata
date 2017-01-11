package com.xurxodev.moviesandroidkata;

import android.app.Application;

import com.xurxodev.moviesandroidkata.di.AppModule;
import com.xurxodev.moviesandroidkata.di.DaggerMoviesComponent;
import com.xurxodev.moviesandroidkata.di.DataModule;
import com.xurxodev.moviesandroidkata.di.MoviesComponent;

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
