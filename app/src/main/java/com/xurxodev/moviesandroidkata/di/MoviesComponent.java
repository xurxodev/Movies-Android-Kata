package com.xurxodev.moviesandroidkata.di;

import com.xurxodev.moviesandroidkata.presentation.view.fragment.MoviesFragment;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules={AppModule.class, DataModule.class})
public interface MoviesComponent {
    void inject(MoviesFragment fragment);
}