package com.xurxodev.moviesandroidkata.view.di;

import com.xurxodev.moviesandroidkata.view.fragment.MoviesFragment;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules={AppModule.class, DataModule.class})
public interface MoviesComponent {
    void inject(MoviesFragment fragment);
}