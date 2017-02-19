package com.xurxodev.moviesandroidkata.di;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xurxodev.moviesandroidkata.data.DiskMovieRepository;
import com.xurxodev.moviesandroidkata.domain.boundary.Repository.MovieRepository;
import com.xurxodev.moviesandroidkata.domain.boundary.executor.AsyncExecutor;
import com.xurxodev.moviesandroidkata.domain.boundary.executor.MainExecutor;
import com.xurxodev.moviesandroidkata.domain.usecase.GetMoviesUseCase;

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
    MovieRepository provideMovieRepository(Application applicationContext, Gson gson) {
        MovieRepository diskMovieRepository = new DiskMovieRepository(applicationContext, gson);
        return diskMovieRepository;
    }

    @Provides
    @Singleton
    GetMoviesUseCase provideGetMoviesUseCase(MovieRepository movieRepository,
            AsyncExecutor asyncExecutor, MainExecutor mainExecutor) {
        GetMoviesUseCase getMoviesUseCase = new GetMoviesUseCase(movieRepository, asyncExecutor,
                mainExecutor);
        return getMoviesUseCase;
    }
}