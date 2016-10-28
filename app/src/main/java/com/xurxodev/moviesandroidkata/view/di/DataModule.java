package com.xurxodev.moviesandroidkata.view.di;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xurxodev.moviesandroidkata.data.DiskMovieRepository;
import com.xurxodev.moviesandroidkata.view.boundary.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {


    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    MovieRepository provideMovieRepository(Application applicationContext,Gson gson) {
        MovieRepository diskMovieRepository = new DiskMovieRepository(applicationContext, gson);
        return diskMovieRepository;
    }
}